package com.springbank.user.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record RemoveUserCommand(@TargetAggregateIdentifier String id) {
}
