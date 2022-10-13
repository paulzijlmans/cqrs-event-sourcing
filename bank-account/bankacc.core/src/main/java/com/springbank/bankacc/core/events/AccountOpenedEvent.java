package com.springbank.bankacc.core.events;

import com.springbank.bankacc.core.models.AccountType;

import java.util.Date;


public record AccountOpenedEvent(String id, String accountHolderId, AccountType accountType, Date creationDate,
                                 double openingBalance) {
}
