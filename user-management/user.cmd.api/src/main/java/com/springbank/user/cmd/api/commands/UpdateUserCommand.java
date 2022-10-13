package com.springbank.user.cmd.api.commands;

import com.springbank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public record UpdateUserCommand(@TargetAggregateIdentifier String id,
                                @NotNull(message = "no user details were supplied") @Valid User user) {
}
