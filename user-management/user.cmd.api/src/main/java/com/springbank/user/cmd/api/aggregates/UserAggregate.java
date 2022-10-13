package com.springbank.user.cmd.api.aggregates;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.springbank.user.cmd.api.security.PasswordEncoder;
import com.springbank.user.cmd.api.security.PasswordEncoderImpl;
import com.springbank.user.core.events.UserRegisteredEvent;
import com.springbank.user.core.events.UserRemovedEvent;
import com.springbank.user.core.events.UserUpdatedEvent;
import com.springbank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import static org.axonframework.modelling.command.AggregateLifecycle.markDeleted;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.user();
        newUser.setId(command.id());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        apply(new UserRegisteredEvent(command.id(), newUser));
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var updatedUser = command.user();
        updatedUser.setId(command.id());
        var password = updatedUser.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        updatedUser.getAccount().setPassword(hashedPassword);

        apply(new UserUpdatedEvent(command.id(), updatedUser));
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        apply(new UserRemovedEvent(command.id()));
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.id();
        this.user = event.user();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.user();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        markDeleted();
    }
}
