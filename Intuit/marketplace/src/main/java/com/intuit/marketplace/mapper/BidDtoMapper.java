package com.intuit.marketplace.mapper;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;

public class BidDtoMapper {
    public static Bid mapToBid(BidRequest bidRequest, Job job, User user) {

        return Bid.builder()
                .bidPrice(bidRequest.getBidPrice())
                .bidder(user)
                .job(job)
                .build();
    }
}
