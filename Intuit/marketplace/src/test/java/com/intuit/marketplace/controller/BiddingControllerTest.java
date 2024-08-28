package com.intuit.marketplace.controller;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.service.BiddingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BiddingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BiddingService biddingService;

    @InjectMocks
    private BiddingController biddingController;

    @Captor
    private ArgumentCaptor<BidRequest> bidRequestArgumentCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(biddingController).build();
    }

    @Test
    void testPlaceBid() throws Exception {
        Long jobId = 1L;
        Bid bid = new Bid();

        when(biddingService.placeBid(eq(jobId), any())).thenReturn(bid);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/jobs/{jobId}/bids", jobId)
                        .contentType("application/json")
                        .content("{\"bid_price\":\"100\", \"user_id\":\"1\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(biddingService).placeBid(eq(jobId), bidRequestArgumentCaptor.capture());
        BidRequest bidRequest1 = bidRequestArgumentCaptor.getValue();
        assertEquals(BigDecimal.valueOf(100), bidRequest1.getBidPrice());
        assertEquals(1L, bidRequest1.getUserId());
    }

    @Test
    void testGetBidsForJob() throws Exception {
        Long jobId = 1L;
        List<Bid> bids = List.of(new Bid());

        when(biddingService.getBidsForJob(jobId)).thenReturn(bids);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs/{jobId}/bids", jobId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]").isNotEmpty());

        verify(biddingService).getBidsForJob(jobId);
    }

    @Test
    void testDeleteBid() throws Exception {
        Long jobId = 1L;
        Long bidId = 1L;
        Long userId = 1L;

        doNothing().when(biddingService).deleteBid(jobId, bidId, userId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/jobs/{jobId}/bids/{bidId}?user_id={userId}", jobId,
                        bidId, userId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(biddingService).deleteBid(jobId, bidId, userId);
    }
}
