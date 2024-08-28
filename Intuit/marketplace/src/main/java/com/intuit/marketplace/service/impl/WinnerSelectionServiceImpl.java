package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.exception.BusinessException;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.service.JobService;
import com.intuit.marketplace.service.WinnerSelectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WinnerSelectionServiceImpl implements WinnerSelectionService {

    private static final Logger logger = LoggerFactory.getLogger(WinnerSelectionServiceImpl.class);

    private JobService jobService;
    private BidRepository bidRepository;

    public WinnerSelectionServiceImpl(JobService jobService, BidRepository bidRepository) {
        this.jobService = jobService;
        this.bidRepository = bidRepository;
    }

    @Override
    public User selectWinner(Long jobId) {
        Job job = jobService.getJobById(jobId);
        return selectWinner(job);
    }

    private User selectWinner(Job job) {
        logger.info("Assigning winner for job with id: {}", job.getId());

        Optional<Bid> bidOptional = bidRepository.findFirstByJobAndBidPriceOrderByCreatedAtAsc(job,
                job.getLowestBidPrice());

        if (bidOptional.isPresent()) {
            Bid lowestBid = bidOptional.get();
            return lowestBid.getBidder();
        } else {
            throw new BusinessException("No winner found for this job id", "NO_WINNER FOUND");
        }
    }

}
