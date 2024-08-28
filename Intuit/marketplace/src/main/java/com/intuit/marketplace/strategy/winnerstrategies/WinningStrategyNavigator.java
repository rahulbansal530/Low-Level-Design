package com.intuit.marketplace.strategy.winnerstrategies;

import com.intuit.marketplace.enums.WinnerSelectionStrategyEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class WinningStrategyNavigator {

    private static final Map<WinnerSelectionStrategyEnum, WinnerSelectionStrategy> winnerSelectionStrategyMap =
            new EnumMap<>(WinnerSelectionStrategyEnum.class);

    @Value("${winner.selection.strategy.name:LowestBidStrategy}")
    private String winnerSelectionStrategyName;

    public WinningStrategyNavigator(List<WinnerSelectionStrategy> winnerSelectionStrategies) {
        winnerSelectionStrategies.forEach(winnerSelectionStrategy ->
                winnerSelectionStrategyMap.put(winnerSelectionStrategy.getName(), winnerSelectionStrategy));
    }

    public WinnerSelectionStrategy getWinnerStrategy() {
        WinnerSelectionStrategyEnum winnerSelectionStrategy =
                WinnerSelectionStrategyEnum.getWinnerStrategy(winnerSelectionStrategyName);
        return winnerSelectionStrategyMap.get(winnerSelectionStrategy);
    }
}
