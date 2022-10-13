package com.springbank.user.core.events;

import com.springbank.user.core.models.User;

public record UserUpdatedEvent(String id, User user) {
}
