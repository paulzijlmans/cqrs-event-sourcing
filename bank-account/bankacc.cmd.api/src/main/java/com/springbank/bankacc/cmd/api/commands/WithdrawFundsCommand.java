package com.springbank.bankacc.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;

public record WithdrawFundsCommand(@TargetAggregateIdentifier String id,
                                   @Min(value = 1, message = "the withdrawal amount must be greater than 0.") double amount) {
}
