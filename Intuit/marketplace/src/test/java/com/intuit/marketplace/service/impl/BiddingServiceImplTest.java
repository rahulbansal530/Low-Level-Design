package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.exception.BidNotFoundException;
import com.intuit.marketplace.exception.JobClosedException;
import com.intuit.marketplace.exception.WrongOwnerException;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.service.AuctionMediator;
import com.intuit.marketplace.service.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BiddingServiceImplTest {

    @Mock
    private BidRepository bidRepository;

    @Mock
    private JobService jobService;

    @Mock
    private AuctionMediator auctionMediator;

    @InjectMocks
    private BiddingServiceImpl biddingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPlaceBid() {
        Long jobId = 1L;
        BidRequest bidRequest = new BidRequest();

        when(auctionMediator.placeBid(jobId, bidRequest)).thenReturn(new Bid());

        Bid result = biddingService.placeBid(jobId, bidRequest);

        verify(auctionMediator).placeBid(jobId, bidRequest);
        assertThat(result).isNotNull();
    }

    @Test
    void testGetBidsForJob() {
        Long jobId = 1L;
        Job job = new Job();
        List<Bid> bids = List.of(new Bid());

        when(jobService.getJobById(jobId)).thenReturn(job);
        when(bidRepository.findAllByJob(job)).thenReturn(bids);

        List<Bid> result = biddingService.getBidsForJob(jobId);

        verify(jobService).getJobById(jobId);
        verify(bidRepository).findAllByJob(job);
        assertThat(result).isEqualTo(bids);
    }

    @Test
    void testDeleteBidSuccess() {
        Long jobId = 1L;
        Long bidId = 1L;
        Long userId = 1L;
        Bid bid = new Bid();
        Job job = new Job();
        job.setJobStatus(JobStatus.OPEN);
        bid.setBidder(new User());
        bid.getBidder().setId(userId);

        when(bidRepository.findById(bidId)).thenReturn(Optional.of(bid));
        when(jobService.getJobById(jobId)).thenReturn(job);

        biddingService.deleteBid(jobId, bidId, userId);

        verify(bidRepository).findById(bidId);
        verify(jobService).getJobById(jobId);
        verify(auctionMediator).deleteBid(jobId, bidId, userId);
    }

    @Test
    void testDeleteBidJobClosed() {
        Long jobId = 1L;
        Long bidId = 1L;
        Long userId = 1L;
        Bid bid = new Bid();
        Job job = new Job();
        job.setJobStatus(JobStatus.CLOSED);
        bid.setBidder(new User());
        bid.getBidder().setId(userId);

        when(bidRepository.findById(bidId)).thenReturn(Optional.of(bid));
        when(jobService.getJobById(jobId)).thenReturn(job);

        JobClosedException thrown = org.junit.jupiter.api.Assertions.assertThrows(JobClosedException.class, () -> {
            biddingService.deleteBid(jobId, bidId, userId);
        });

        assertThat(thrown.getMessage()).isEqualTo("Cannot delete bid for a job that is not open");
    }

    @Test
    void testDeleteBidWrongOwner() {
        Long jobId = 1L;
        Long bidId = 1L;
        Long userId = 2L;
        Bid bid = new Bid();
        Job job = new Job();
        job.setJobStatus(JobStatus.OPEN);
        bid.setBidder(new User());
        bid.getBidder().setId(1L);

        when(bidRepository.findById(bidId)).thenReturn(Optional.of(bid));
        when(jobService.getJobById(jobId)).thenReturn(job);

        WrongOwnerException thrown = org.junit.jupiter.api.Assertions.assertThrows(WrongOwnerException.class, () -> {
            biddingService.deleteBid(jobId, bidId, userId);
        });

        assertThat(thrown.getMessage()).isEqualTo("You can only delete your own bid");
    }

    @Test
    void testGetBidById() {
        Long bidId = 1L;
        Bid bid = new Bid();

        when(bidRepository.findById(bidId)).thenReturn(Optional.of(bid));

        Bid result = biddingService.getBidById(bidId);

        verify(bidRepository).findById(bidId);
        assertThat(result).isEqualTo(bid);
    }

    @Test
    void testGetBidByIdNotFound() {
        Long bidId = 1L;

        when(bidRepository.findById(bidId)).thenReturn(Optional.empty());

        BidNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(BidNotFoundException.class, () -> {
            biddingService.getBidById(bidId);
        });

        assertThat(thrown.getMessage()).isEqualTo("Bid with id " + bidId + " not found");
    }
}
