package com.intuit.marketplace.repository;

import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findAllByJob(Job job);

    Optional<Bid> findByIdAndJobId(Long id, Long jobId);

    Optional<Bid> findTopByJobOrderByBidPriceAsc(Job job);

    Optional<Bid> findFirstByJobAndBidPriceGreaterThanOrderByBidPriceAsc(Job job, BigDecimal price);

    Optional<Bid> findFirstByJobAndBidPriceOrderByCreatedAtAsc(Job job, BigDecimal bidPrice);

}
