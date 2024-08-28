package com.intuit.marketplace.controller;


import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.service.BiddingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs/{jobId}/bids")
public class BiddingController {
    private final BiddingService biddingService;

    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @PostMapping
    public ResponseEntity<Bid> placeBid(@PathVariable Long jobId, @Valid @RequestBody BidRequest bidRequest) {
        Bid bid = biddingService.placeBid(jobId, bidRequest);
        return ResponseEntity.ok(bid);
    }

    @GetMapping
    public ResponseEntity<List<Bid>> getBidsForJob(@PathVariable Long jobId) {
        List<Bid> bids = biddingService.getBidsForJob(jobId);
        return new ResponseEntity<>(bids, HttpStatus.OK);
    }

    @DeleteMapping("/{bidId}")
    public ResponseEntity<Object> deleteBid(@PathVariable Long jobId, @PathVariable Long bidId,
                                            @RequestParam(value = "user_id") Long userId) {
        biddingService.deleteBid(jobId, bidId, userId);
        return ResponseEntity.ok().build();
    }
}

