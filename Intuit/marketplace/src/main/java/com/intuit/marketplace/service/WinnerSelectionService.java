package com.intuit.marketplace.service;

import com.intuit.marketplace.entity.User;

public interface WinnerSelectionService {

    User selectWinner(Long jobId);
}
