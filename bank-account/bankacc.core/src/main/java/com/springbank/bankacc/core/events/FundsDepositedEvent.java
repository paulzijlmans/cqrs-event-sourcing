package com.springbank.bankacc.core.events;

public record FundsDepositedEvent(String id, double amount, double balance) {
}
