package com.intuit.marketplace.strategy.winnerstrategies.impl;

import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.enums.WinnerSelectionStrategyEnum;
import com.intuit.marketplace.repository.BidRepository;
import com.intuit.marketplace.strategy.winnerstrategies.WinnerSelectionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LowestBidWinnerStrategy implements WinnerSelectionStrategy {

    private static final Logger logger = LoggerFactory.getLogger(LowestBidWinnerStrategy.class);

    private final BidRepository bidRepository;

    public LowestBidWinnerStrategy(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public Optional<Bid> selectWinner(Job job) {
        return bidRepository.findTopByJobOrderByBidPriceAsc(job);
    }

    @Override
    public WinnerSelectionStrategyEnum getName() {
        return WinnerSelectionStrategyEnum.LOWEST_BID;
    }
}
