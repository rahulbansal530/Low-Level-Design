package com.intuit.marketplace.service.impl;

import com.intuit.marketplace.dto.request.BidRequest;
import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.entity.User;
import com.intuit.marketplace.enums.JobStatus;
import com.intuit.marketplace.enums.JobType;
import com.intuit.marketplace.exception.*;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.repository.JobRepository;
import com.intuit.marketplace.service.UserService;
import com.intuit.marketplace.strategy.winnerstrategies.WinningStrategyNavigator;
import com.intuit.marketplace.strategy.winnerstrategies.impl.LowestBidWinnerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class AuctionMediatorImplTest {

    @Mock
    private BidRepository bidRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private UserService userService;

    @Mock
    private WinningStrategyNavigator winningStrategyNavigator;

    @Mock
    LowestBidWinnerStrategy lowestBidWinnerStrategy;

    @InjectMocks
    private AuctionMediatorImpl auctionMediator;

    @Captor
    private ArgumentCaptor<Bid> bidCaptor;

    @Captor
    private ArgumentCaptor<Job> jobCaptor;

    private Job job;
    private User user;
    private Bid bid;
    private BidRequest bidRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        job = new Job();
        job.setId(1L);
        job.setStartingPrice(new BigDecimal("100.00"));
        job.setLowestBidPrice(new BigDecimal("80.00"));
        job.setExpirationDate(LocalDateTime.now().plusDays(1));
        job.setJobType(JobType.IT);
        job.setJobStatus(JobStatus.OPEN);
        job.setBidCount(1);
        User poster = new User();
        poster.setId(2L);
        job.setPoster(poster);

        user = new User();
        user.setId(3L);

        bid = new Bid();
        bid.setId(1L);
        bid.setBidPrice(new BigDecimal("70.00"));
        bid.setBidder(user);
        bid.setJob(job);

        bidRequest = new BidRequest();
        bidRequest.setBidPrice(new BigDecimal("70.00"));
        bidRequest.setUserId(user.getId());
    }

    @Test
    void testCloseBiddingForJob_Success() {
        Long jobId = 1L;
        Job job = new Job();
        job.setId(jobId);
        Bid bid = new Bid();
        bid.setBidPrice(BigDecimal.valueOf(100.0));
        User bidder = new User();
        bidder.setId(1L);
        bid.setBidder(bidder);

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
        when(bidRepository.findTopByJobOrderByBidPriceAsc(job)).thenReturn(Optional.of(bid));
        when(winningStrategyNavigator.getWinnerStrategy()).thenReturn(lowestBidWinnerStrategy);
        when(lowestBidWinnerStrategy.selectWinner(any())).thenReturn(Optional.of(bid));
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        auctionMediator.closeBiddingForJob(jobId);

        verify(jobRepository).save(job);
        assertEquals(JobStatus.CLOSED, job.getJobStatus());
        assertEquals(BigDecimal.valueOf(100.0), job.getLowestBidPrice());
        assertEquals(bidder, job.getWinningBidder());
    }

    @Test
    void testCloseBiddingForJob_NoBids() {
        Long jobId = 1L;
        Job job = new Job();
        job.setId(jobId);

        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));
        when(winningStrategyNavigator.getWinnerStrategy()).thenReturn(lowestBidWinnerStrategy);
        when(lowestBidWinnerStrategy.selectWinner(any())).thenReturn(Optional.empty());
        when(jobRepository.save(any(Job.class))).thenReturn(job);

        auctionMediator.closeBiddingForJob(jobId);

        verify(jobRepository).save(job);
        assertEquals(JobStatus.CLOSED, job.getJobStatus());
        assertNull(job.getLowestBidPrice());
        assertNull(job.getWinningBidder());
    }

    @Test
    void testCloseBiddingForJob_JobNotFound() {
        Long jobId = 1L;

        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            auctionMediator.closeBiddingForJob(jobId);
        });

        assertEquals("Job with id 1 not found", exception.getMessage());
    }

    @Test
    public void testPlaceBid_Success() {
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));
        when(bidRepository.save(any(Bid.class))).thenAnswer(invocation -> invocation.getArguments()[0]);
        when(userService.getUser(anyLong())).thenReturn(user);

        Bid bid = auctionMediator.placeBid(1L, bidRequest);

        verify(bidRepository).save(bidCaptor.capture());
        Bid capturedBid = bidCaptor.getValue();
        assertNotNull(capturedBid);
        assertEquals(bidRequest.getBidPrice(), capturedBid.getBidPrice());
        assertEquals(user, capturedBid.getBidder());
        assertEquals(job, capturedBid.getJob());
    }

    @Test
    public void testPlaceBid_ThrowsIncorrectPriceException() {
        job.setLowestBidPrice(new BigDecimal("50.00"));
        bidRequest.setBidPrice(new BigDecimal("55.00"));

        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));
        when(userService.getUser(anyLong())).thenReturn(user);

        IncorrectPriceException thrown = assertThrows(IncorrectPriceException.class, () -> {
            auctionMediator.placeBid(1L, bidRequest);
        });

        assertEquals("Bid price must be lower than current price", thrown.getMessage());
    }

    @Test
    public void testPlaceBid_ThrowsIncorrectDateException() {
        job.setExpirationDate(LocalDateTime.now().minusDays(1));

        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));
        when(userService.getUser(anyLong())).thenReturn(user);

        IncorrectDateException thrown = assertThrows(IncorrectDateException.class, () -> {
            auctionMediator.placeBid(1L, bidRequest);
        });

        assertEquals("Job Auction has ended", thrown.getMessage());
    }

    @Test
    public void testPlaceBid_ThrowsJobPosterBidException() {
        job.setPoster(user);

        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));
        when(userService.getUser(anyLong())).thenReturn(user);

        JobPosterBidException thrown = assertThrows(JobPosterBidException.class, () -> {
            auctionMediator.placeBid(1L, bidRequest);
        });

        assertEquals("Job poster cannot place a bid on their own job", thrown.getMessage());
    }

    @Test
    public void testDeleteBid_Success() {
        job.setLowestBidPrice(new BigDecimal("70.00"));
        when(bidRepository.findByIdAndJobId(anyLong(), anyLong())).thenReturn(Optional.of(bid));
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));
        when(bidRepository.findFirstByJobAndBidPriceGreaterThanOrderByBidPriceAsc(any(Job.class), any(BigDecimal.class)))
                .thenReturn(Optional.empty());

        auctionMediator.deleteBid(1L, 1L, 3L);

        verify(bidRepository).delete(bid);
        verify(jobRepository).save(jobCaptor.capture());
        Job updatedJob = jobCaptor.getValue();
        assertNotNull(updatedJob);
        assertNull(updatedJob.getLowestBidPrice());
        assertEquals(0, updatedJob.getBidCount());
    }

    @Test
    public void testDeleteBid_ThrowsJobClosedException() {
        job.setJobStatus(JobStatus.CLOSED);

        when(bidRepository.findByIdAndJobId(anyLong(), anyLong())).thenReturn(Optional.of(bid));
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));

        JobClosedException thrown = assertThrows(JobClosedException.class, () -> {
            auctionMediator.deleteBid(1L, 1L, 3L);
        });

        assertEquals("Cannot delete bid for a job that is not open", thrown.getMessage());
    }

    @Test
    public void testDeleteBid_ThrowsWrongOwnerException() {
        Bid differentBid = new Bid();
        differentBid.setId(1L);
        differentBid.setBidder(user);
        differentBid.setJob(job);

        when(bidRepository.findByIdAndJobId(anyLong(), anyLong())).thenReturn(Optional.of(differentBid));

        WrongOwnerException thrown = assertThrows(WrongOwnerException.class, () -> {
            auctionMediator.deleteBid(1L, 1L, 1L);
        });

        assertEquals("You can only delete your own bid", thrown.getMessage());
    }

    @Test
    public void testDeleteBid_ThrowsBidNotFoundException() {
        when(bidRepository.findByIdAndJobId(anyLong(), anyLong())).thenReturn(Optional.empty());

        BidNotFoundException thrown = assertThrows(BidNotFoundException.class, () -> {
            auctionMediator.deleteBid(1L, 1L, 3L);
        });

        assertEquals("Bid with id 1 and jobId 1 not found", thrown.getMessage());
    }

//    @Test
//    public void testDeleteBid_ThrowsJobNotFoundException() {
//        when(bidRepository.findByIdAndJobId(anyLong(), anyLong())).thenReturn(Optional.of(bid));
//        when(jobRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        JobNotFoundException thrown = assertThrows(JobNotFoundException.class, () -> {
//            auctionMediator.deleteBid(1L, 1L, 3L);
//        });
//
//        assertEquals("Job with id 1 not found", thrown.getMessage());
//    }
}