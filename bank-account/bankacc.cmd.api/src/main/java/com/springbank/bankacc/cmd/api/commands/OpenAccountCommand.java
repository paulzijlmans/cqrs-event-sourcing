package com.springbank.bankacc.cmd.api.commands;

import com.springbank.bankacc.core.models.AccountType;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public record OpenAccountCommand(@TargetAggregateIdentifier String id,
                                 @NotNull(message = "no account holder ID was supplied.") String accountHolderId,
                                 @NotNull(message = "no account type was supplied.") AccountType accountType,
                                 @Min(value = 50, message = "opening balance must be at least 50.") double openingBalance) {
}
