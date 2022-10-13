package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;

public interface AccountQueryHandler {
    AccountLookupResponse handle(FindAccountByIdQuery query);
    AccountLookupResponse handle(FindAccountByHolderIdQuery query);
    AccountLookupResponse handle(FindAllAccountsQuery query);
    AccountLookupResponse handle(FindAccountsWithBalanceQuery query);
}
