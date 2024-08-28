package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.exception.BidNotFoundException;
import com.intuit.marketplace.exception.JobClosedException;
import com.intuit.marketplace.exception.WrongOwnerException;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.service.AuctionMediator;
import com.intuit.marketplace.service.BiddingService;
import com.intuit.marketplace.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BiddingServiceImpl implements BiddingService {
    private static final Logger logger = LoggerFactory.getLogger(BiddingServiceImpl.class);

    private final BidRepository bidRepository;
    private final JobService jobService;
    private final AuctionMediator auctionMediator;

    public BiddingServiceImpl(BidRepository bidRepository, AuctionMediator auctionMediator
            , JobService jobService) {
        this.bidRepository = bidRepository;
        this.auctionMediator = auctionMediator;
        this.jobService = jobService;
    }

    @Override
    public Bid placeBid(Long jobId, BidRequest bidRequest) {
        return auctionMediator.placeBid(jobId, bidRequest);
    }

    @Override
    public List<Bid> getBidsForJob(Long jobId) {
        logger.info("Fetching bids for jobId: {}", jobId);

        Job job = jobService.getJobById(jobId);

        List<Bid> bids = bidRepository.findAllByJob(job);
        logger.info("Found {} bids for jobId: {}", bids.size(), jobId);
        return bids;
    }

    @Override
    public void deleteBid(Long jobId, Long bidId, Long userId) {
        logger.info("Attempting to delete bidId: {} for jobId: {} by userId: {}", bidId, jobId, userId);

        Bid bidToDelete = getBidById(bidId);
        Job job = jobService.getJobById(jobId);

        validateDeleteBidRequest(job, bidToDelete, userId);
        auctionMediator.deleteBid(jobId, bidId, userId);
    }

    @Override
    public Bid getBidById(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> {
                    logger.error("Bid with id {} not found", bidId);
                    return new BidNotFoundException("Bid with id " + bidId + " not found", "BID_NOT_FOUND");
                });
    }

    private void validateDeleteBidRequest(Job job, Bid bidToDelete, Long userId) {
        if (!job.getJobStatus().equals(JobStatus.OPEN)) {
            logger.error("Cannot delete bid for job with id {} because it is not open", job.getId());
            throw new JobClosedException("Cannot delete bid for a job that is not open", "JOB_ALREADY_CLOSED");
        }
        if (!bidToDelete.getBidder().getId().equals(userId)) {
            logger.error("User with id {} attempted to delete a bid they do not own", userId);
            throw new WrongOwnerException("You can only delete your own bid", "WRONG_BID_OWNER");
        }
    }

}
