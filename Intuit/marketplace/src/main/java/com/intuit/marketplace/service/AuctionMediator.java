package com.intuit.marketplace.service;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;

public interface AuctionMediator {
    Bid placeBid(Long jobId, BidRequest bidRequest);

    void deleteBid(Long jobId, Long bidId, Long userId);

    void closeBiddingForJob(Long jobId);
}
