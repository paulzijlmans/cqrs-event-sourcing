package com.springbank.user.cmd.api.aggregates;

import com.springbank.user.cmd.api.commands.RegisterUserCommand;
import com.springbank.user.cmd.api.commands.RemoveUserCommand;
import com.springbank.user.cmd.api.commands.UpdateUserCommand;
import com.springbank.user.core.events.UserRegisteredEvent;
import com.springbank.user.core.events.UserRemovedEvent;
import com.springbank.user.core.events.UserUpdatedEvent;
import com.springbank.user.core.models.Account;
import com.springbank.user.core.models.User;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class UserAggregateTest {

    private FixtureConfiguration<UserAggregate> testFixture;
    private User user;
    private User updatedUser;

    private static final String USER_ID = UUID.randomUUID().toString();

    @BeforeEach
    void setup() {
        testFixture = new AggregateTestFixture<>(UserAggregate.class);
        user = User.builder()
                .account(Account.builder().password("password").build())
                .build();
        updatedUser = User.builder()
                .account(Account.builder().password("password2").build())
                .build();
    }


    @Test
    void registerUserTest() {
        testFixture
                .givenNoPriorActivity()
                .when(new RegisterUserCommand(USER_ID, user))
                .expectEvents(new UserRegisteredEvent(USER_ID, user));
    }

    @Test
    void updateUserTest() {
        testFixture
                .given(new UserRegisteredEvent(USER_ID, user))
                .when(new UpdateUserCommand(USER_ID, updatedUser))
                .expectEvents(new UserUpdatedEvent(USER_ID, updatedUser));

    }

    @Test
    void removeUserTest() {
        testFixture
                .given(new UserRegisteredEvent(USER_ID, user), new UserUpdatedEvent(USER_ID, updatedUser))
                .when(new RemoveUserCommand(USER_ID))
                .expectEvents(new UserRemovedEvent(USER_ID));
    }
}
