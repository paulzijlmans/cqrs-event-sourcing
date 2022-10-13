package com.springbank.bankacc.cmd.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepositFundsCommand {
    @TargetAggregateIdentifier
    private String id;

    @Min(value = 1, message = "the deposit amount must be greater than 0.")
    private double amount;
}
