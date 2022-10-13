package com.springbank.bankacc.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CloseAccountCommand(@TargetAggregateIdentifier String id) {
}
