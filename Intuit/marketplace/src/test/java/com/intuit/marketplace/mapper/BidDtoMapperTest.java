package com.intuit.marketplace.mapper;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BidDtoMapperTest {

    @Test
    void testMapToBid() {
        BidRequest bidRequest = new BidRequest();
        bidRequest.setBidPrice(BigDecimal.valueOf(150.0));

        Job job = Job.builder()
                .id(1L)
                .title("Software Engineer")
                .build();

        User user = User.builder()
                .id(2L)
                .email("john.doe@example.com")
                .build();

        Bid bid = BidDtoMapper.mapToBid(bidRequest, job, user);

        assertEquals(BigDecimal.valueOf(150.0), bid.getBidPrice());
        assertEquals(user, bid.getBidder());
        assertEquals(job, bid.getJob());
    }
}
