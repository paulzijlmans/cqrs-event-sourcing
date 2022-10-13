package com.springbank.bankacc.core.events;

public record FundsWithdrawnEvent(String id, double amount, double balance) {
}
