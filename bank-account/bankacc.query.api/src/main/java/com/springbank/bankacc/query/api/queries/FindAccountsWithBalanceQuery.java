package com.springbank.bankacc.query.api.queries;

import com.springbank.bankacc.query.api.dto.EqualityType;

public record FindAccountsWithBalanceQuery(EqualityType equalityType, double balance) {
}
