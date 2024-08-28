package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.exception.*;
import com.intuit.marketplace.mapper.BidDtoMapper;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.repository.JobRepository;
import com.intuit.marketplace.service.AuctionMediator;
import com.intuit.marketplace.service.UserService;
import com.intuit.marketplace.strategy.winnerstrategies.WinningStrategyNavigator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuctionMediatorImpl implements AuctionMediator {

    private static final Logger logger = LoggerFactory.getLogger(AuctionMediatorImpl.class);

    private final BidRepository bidRepository;
    private final JobRepository jobRepository;
    private final UserService userService;
    private final WinningStrategyNavigator winningStrategyNavigator;

    @Autowired
    public AuctionMediatorImpl(BidRepository bidRepository, JobRepository jobRepository, UserService userService,
                               WinningStrategyNavigator winningStrategyNavigator) {
        this.bidRepository = bidRepository;
        this.jobRepository = jobRepository;
        this.userService = userService;
        this.winningStrategyNavigator = winningStrategyNavigator;
    }

    @Transactional
    public void closeBiddingForJob(Long jobId) {
        logger.info("Closing bidding for job with id: {}", jobId);
        Job job = getJobByJobId(jobId);

        assignWinnerForJob(job);

        job.setJobStatus(JobStatus.CLOSED);
        jobRepository.save(job);
        logger.info("Bidding closed and winner assigned for job with id: {}", jobId);
    }

    private void assignWinnerForJob(Job job) {
        logger.info("Assigning winner for job with id: {}", job.getId());

        Optional<Bid> lowestBidOptional = winningStrategyNavigator.getWinnerStrategy().selectWinner(job);
        if (lowestBidOptional.isPresent()) {
            Bid lowestBid = lowestBidOptional.get();
            job.setLowestBidPrice(lowestBid.getBidPrice());
            job.setWinningBidder(lowestBid.getBidder());
            job.setWinningBidAmount(lowestBid.getBidPrice());
            logger.info("Winner assigned: bidderId: {} with price: {}", lowestBid.getBidder().getId(),
                    lowestBid.getBidPrice());
        } else {
            logger.warn("No bids found for job with id {}", job.getId());
            job.setLowestBidPrice(null);
            job.setWinningBidder(null);
        }
    }

    @Override
    @Transactional
    public Bid placeBid(Long jobId, BidRequest bidRequest) {
        logger.info("Placing bid for jobId: {} by userId: {}", jobId, bidRequest.getUserId());

        Job job = getJobByJobId(jobId);
        User user = userService.getUser(bidRequest.getUserId());

        validateBid(bidRequest, job, user);
        Bid bid = BidDtoMapper.mapToBid(bidRequest, job, user);
        job.setLowestBidPrice(bid.getBidPrice());
        job.setBidCount(job.getBidCount() + 1);

        logger.info("Saving job with updated bid count and lowest bid price: {}", job.getLowestBidPrice());
        jobRepository.save(job);

        logger.info("Saving new bid with id: {}", bid.getId());
        return bidRepository.save(bid);
    }

    private void validateBid(BidRequest bidRequest, Job job, User user) {
        if (job.getLowestBidPrice() != null && bidRequest.getBidPrice().compareTo(job.getLowestBidPrice()) >= 0) {
            logger.error("Bid price {} is not lower than current lowest bid price {}", bidRequest.getBidPrice(),
                    job.getLowestBidPrice());
            throw new IncorrectPriceException("Bid price must be lower than current price", "INCORRECT_BIDDING_PRICE");
        }
        if (job.getStartingPrice() != null && bidRequest.getBidPrice().compareTo(job.getStartingPrice()) >= 0) {
            logger.error("Bid price {} is not lower than starting bid price {}", bidRequest.getBidPrice(),
                    job.getStartingPrice());
            throw new IncorrectPriceException("Bid price must be lower than current price", "INCORRECT_BIDDING_PRICE");
        }
        if (LocalDateTime.now().isAfter(job.getExpirationDate())) {
            logger.error("Bid attempt after auction end date for job id {}", job.getId());
            throw new IncorrectDateException("Job Auction has ended", "INCORRECT_BIDDING_DATE");
        }
        if (user.getId().equals(job.getPoster().getId())) {
            logger.error("Job poster with id {} is trying to place a bid on their own job", user.getId());
            throw new JobPosterBidException("Job poster cannot place a bid on their own job", "SAME_BID_OWNER");
        }
    }

    @Override
    @Transactional
    public void deleteBid(Long jobId, Long bidId, Long userId) {
        logger.info("Attempting to delete bidId: {} for jobId: {} by userId: {}", bidId, jobId, userId);

        Bid bidToDelete = getBidByBidId(bidId, jobId);

        validateDeleteBidRequest(bidToDelete, userId);
        deleteBidAndUpdateJob(bidToDelete);
    }

    private void validateDeleteBidRequest(Bid bidToDelete, Long userId) {
        if (!bidToDelete.getJob().getJobStatus().equals(JobStatus.OPEN)) {
            logger.error("Cannot delete bid for job with id {} because it is not open", bidToDelete.getJob().getId());
            throw new JobClosedException("Cannot delete bid for a job that is not open", "JOB_ALREADY_CLOSED");
        }
        if (!bidToDelete.getBidder().getId().equals(userId)) {
            logger.error("User with id {} attempted to delete a bid they do not own", userId);
            throw new WrongOwnerException("You can only delete your own bid", "WRONG_BID_OWNER");
        }
    }

    private void deleteBidAndUpdateJob(Bid bidToDelete) {
        logger.info("Deleting bid with id: {}", bidToDelete.getId());
        bidRepository.delete(bidToDelete);

        Job job = bidToDelete.getJob();
        job.setBidCount(job.getBidCount() - 1);

        if (job.getLowestBidPrice().equals(bidToDelete.getBidPrice())) {
            Optional<Bid> nextLowestBidOptional =
                    bidRepository.findFirstByJobAndBidPriceGreaterThanOrderByBidPriceAsc(job, job.getLowestBidPrice());

            if (nextLowestBidOptional.isPresent()) {
                Bid nextLowestBid = nextLowestBidOptional.get();
                job.setLowestBidPrice(nextLowestBid.getBidPrice());
                logger.info("Updated job's lowest bid price to {}", nextLowestBid.getBidPrice());
            } else {
                job.setLowestBidPrice(null);
                logger.info("No remaining bids. Set lowest bid price to null.");
            }
        }

        logger.info("Saving updated job with id: {}", job.getId());
        jobRepository.save(job);
    }

    private Job getJobByJobId(Long jobId) {
        return jobRepository.findById(jobId).orElseThrow(() -> {
            logger.error("Job with id {} not found", jobId);
            return new JobNotFoundException("Job with id " + jobId + " not found", "NOT_FOUND");
        });
    }

    private Bid getBidByBidId(Long bidId, Long jobId) {
        return bidRepository.findByIdAndJobId(bidId, jobId).orElseThrow(() -> {
            logger.error("Bid with id {} and jobId {} not found", bidId, jobId);
            return new BidNotFoundException("Bid with id " + bidId + " and jobId " + jobId + " not found",
                    "BID_NOT_FOUND");
        });
    }
}

