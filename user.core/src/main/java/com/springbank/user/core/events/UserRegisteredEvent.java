package com.springbank.user.core.events;

import com.springbank.user.core.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {

    private String id;
    private User user;
}
