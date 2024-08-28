package com.intuit.marketplace.enums;

import com.intuit.marketplace.exception.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum WinnerSelectionStrategyEnum {
    LOWEST_BID("LowestBidStrategy"), HIGHEST_BID("HighestBid");

    final String name;

    WinnerSelectionStrategyEnum(String name) {
        this.name = name;
    }

    private static final Map<String, WinnerSelectionStrategyEnum> winnerSelectionStrategyEnumMap = new HashMap<>();

    static {
        Arrays.stream(WinnerSelectionStrategyEnum.values()).forEach(winnerSelectionStrategyEnum ->
                winnerSelectionStrategyEnumMap.put(winnerSelectionStrategyEnum.getName(), winnerSelectionStrategyEnum));
    }

    public static WinnerSelectionStrategyEnum getWinnerStrategy(final String winnerStrategyValue) {
        if (winnerSelectionStrategyEnumMap.containsKey(winnerStrategyValue)) {
            return winnerSelectionStrategyEnumMap.get(winnerStrategyValue);
        }
        throw new BusinessException("Encoding Algorithm does not exists", HttpStatus.BAD_REQUEST.name());
    }

}
