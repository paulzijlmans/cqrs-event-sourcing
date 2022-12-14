package com.springbank.bankacc.query.api.handlers;

import com.springbank.bankacc.core.models.BankAccount;
import com.springbank.bankacc.query.api.dto.AccountLookupResponse;
import com.springbank.bankacc.query.api.dto.EqualityType;
import com.springbank.bankacc.query.api.queries.FindAccountByHolderIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountByIdQuery;
import com.springbank.bankacc.query.api.queries.FindAccountsWithBalanceQuery;
import com.springbank.bankacc.query.api.queries.FindAllAccountsQuery;
import com.springbank.bankacc.query.api.repositories.AccountRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountQueryHandlerImpl implements AccountQueryHandler {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountQueryHandlerImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler
    @Override
    public AccountLookupResponse handle(FindAccountByIdQuery query) {
        var bankAccount = accountRepository.findById(query.id());
        return bankAccount.map(account -> new AccountLookupResponse("Bank Account Successfully Returned!", account))
                .orElseGet(() -> new AccountLookupResponse("No Bank Account Found for ID - " + query.id()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse handle(FindAccountByHolderIdQuery query) {
        var bankAccount = accountRepository.findByAccountHolderId(query.accountHolderId());
        return bankAccount.map(account -> new AccountLookupResponse("Bank Account Successfully Returned!", account))
                .orElseGet(() -> new AccountLookupResponse("No Bank Account Found for Holder ID - " + query.accountHolderId()));
    }

    @QueryHandler
    @Override
    public AccountLookupResponse handle(FindAllAccountsQuery query) {
        var bankAccountIterator = accountRepository.findAll();

        if (!bankAccountIterator.iterator().hasNext())
            return new AccountLookupResponse("No Bank Accounts were Found!");

        var bankAccounts = new ArrayList<BankAccount>();
        bankAccountIterator.forEach(bankAccounts::add);

        return new AccountLookupResponse("Successfully Returned " + bankAccounts.size() + " Bank Account(s)!", bankAccounts);
    }

    @QueryHandler
    @Override
    public AccountLookupResponse handle(FindAccountsWithBalanceQuery query) {
        var bankAccounts = query.equalityType() == EqualityType.GREATER_THAN
                ? accountRepository.findByBalanceGreaterThan(query.balance())
                : accountRepository.findByBalanceLessThan(query.balance());

        return bankAccounts != null && bankAccounts.size() > 0
                ? new AccountLookupResponse("Successfully Returned " + bankAccounts.size() + " Bank Account(s)!", bankAccounts)
                : new AccountLookupResponse("No Bank Accounts were Found!");
    }
}
