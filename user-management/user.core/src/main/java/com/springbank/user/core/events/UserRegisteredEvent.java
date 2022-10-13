package com.springbank.user.core.events;

import com.springbank.user.core.models.User;

public record UserRegisteredEvent(String id, User user) {
}
