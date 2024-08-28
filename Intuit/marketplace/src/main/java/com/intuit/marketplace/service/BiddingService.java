package com.intuit.marketplace.service;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;

import java.util.List;

public interface BiddingService {
    Bid placeBid(Long jobId, BidRequest bidRequest);

    List<Bid> getBidsForJob(Long jobId);

    void deleteBid(Long jobId, Long bidId, Long userId);

    Bid getBidById(Long bidId);
}
