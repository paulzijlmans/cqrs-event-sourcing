package com.springbank.bankacc.cmd.api.aggregates;

import com.springbank.bankacc.cmd.api.commands.CloseAccountCommand;
import com.springbank.bankacc.cmd.api.commands.DepositFundsCommand;
import com.springbank.bankacc.cmd.api.commands.OpenAccountCommand;
import com.springbank.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.springbank.bankacc.core.events.AccountClosedEvent;
import com.springbank.bankacc.core.events.AccountOpenedEvent;
import com.springbank.bankacc.core.events.FundsDepositedEvent;
import com.springbank.bankacc.core.events.FundsWithdrawnEvent;
import com.springbank.bankacc.core.models.AccountType;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

class AccountAggregateTest {

    private FixtureConfiguration<AccountAggregate> testFixture;

    private static final String ACCOUNT_ID = UUID.randomUUID().toString();
    private static final String USER_ID = UUID.randomUUID().toString();

    @BeforeEach
    void setup() {
        testFixture = new AggregateTestFixture<>(AccountAggregate.class);
    }

    @Test
    void openAccountCommand() {
        testFixture
                .givenNoPriorActivity()
                .when(new OpenAccountCommand(ACCOUNT_ID, USER_ID, AccountType.CURRENT, 100.0))
                .expectSuccessfulHandlerExecution();
    }

    @Test
    void depositFundsCommand() {
        testFixture
                .given(new AccountOpenedEvent(ACCOUNT_ID, USER_ID, AccountType.CURRENT, new Date(), 100.0))
                .when(new DepositFundsCommand(ACCOUNT_ID, 50.0))
                .expectEvents(new FundsDepositedEvent(ACCOUNT_ID, 50.0, 150.0));
    }

    @Test
    void withdrawFundsCommand() {
        testFixture
                .given(new AccountOpenedEvent(ACCOUNT_ID, USER_ID, AccountType.CURRENT, new Date(), 100.0))
                .when(new WithdrawFundsCommand(ACCOUNT_ID, 25.0))
                .expectEvents(new FundsWithdrawnEvent(ACCOUNT_ID, 25.0, 75.0));
    }

    @Test
    void closeAcountCommand() {
        testFixture
                .given(new AccountOpenedEvent(ACCOUNT_ID, USER_ID, AccountType.CURRENT, new Date(), 100.0))
                .when(new CloseAccountCommand(ACCOUNT_ID))
                .expectEvents(new AccountClosedEvent(ACCOUNT_ID));
    }

}