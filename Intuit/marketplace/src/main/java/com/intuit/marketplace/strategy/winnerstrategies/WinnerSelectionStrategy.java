package com.intuit.marketplace.strategy.winnerstrategies;

import com.intuit.marketplace.entity.Bid;
import com.intuit.marketplace.entity.Job;
import com.intuit.marketplace.enums.WinnerSelectionStrategyEnum;

import java.util.Optional;

public interface WinnerSelectionStrategy {
    Optional<Bid> selectWinner(Job job);

    public WinnerSelectionStrategyEnum getName();
}

