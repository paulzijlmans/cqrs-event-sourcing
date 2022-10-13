package com.springbank.bankacc.cmd.api.aggregates;

import com.springbank.bankacc.cmd.api.commands.CloseAccountCommand;
import com.springbank.bankacc.cmd.api.commands.DepositFundsCommand;
import com.springbank.bankacc.cmd.api.commands.OpenAccountCommand;
import com.springbank.bankacc.cmd.api.commands.WithdrawFundsCommand;
import com.springbank.bankacc.core.events.AccountClosedEvent;
import com.springbank.bankacc.core.events.AccountOpenedEvent;
import com.springbank.bankacc.core.events.FundsDepositedEvent;
import com.springbank.bankacc.core.events.FundsWithdrawnEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
@NoArgsConstructor
public class AccountAggregate {
    @AggregateIdentifier
    private String id;
    private String accountHolderId;
    private double balance;

    @CommandHandler
    public AccountAggregate(OpenAccountCommand command) {
        apply(new AccountOpenedEvent(command.id(), command.accountHolderId(), command.accountType(), new Date(), command.openingBalance()));
    }

    @EventSourcingHandler
    public void on(AccountOpenedEvent event) {
        this.id = event.id();
        this.accountHolderId = event.accountHolderId();
        this.balance = event.openingBalance();
    }

    @CommandHandler
    public void handle(DepositFundsCommand command) {
        var amount = command.amount();
        var newBalance = this.balance + amount;

        apply(new FundsDepositedEvent(command.id(), amount, newBalance));
    }

    @EventSourcingHandler
    public void on(FundsDepositedEvent event) {
        this.balance += event.amount();
    }

    @CommandHandler
    public void handle(WithdrawFundsCommand command) {
        var amount = command.amount();

        if (this.balance - amount < 0) {
            throw new IllegalStateException("Withdrawal declined, insufficient funds!");
        }
        var newBalance = this.balance - amount;

        apply(new FundsWithdrawnEvent(command.id(), command.amount(), newBalance));
    }

    @EventSourcingHandler
    public void on(FundsWithdrawnEvent event) {
        this.balance -= event.amount();
    }

    @CommandHandler
    public void handle(CloseAccountCommand command) {
        apply(new AccountClosedEvent(command.id()));
    }

    @EventSourcingHandler
    public void on(AccountClosedEvent event) {
        markDeleted();
    }
}
