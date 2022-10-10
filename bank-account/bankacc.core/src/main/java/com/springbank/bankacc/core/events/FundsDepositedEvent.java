package com.springbank.bankacc.core.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FundsDepositedEvent {
    private String id;
    private double amount;
    private double balance;
}
