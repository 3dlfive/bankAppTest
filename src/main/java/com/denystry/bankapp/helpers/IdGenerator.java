package com.denystry.bankapp.helpers;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
    private Long customerId = 0L;
    private Long accountId = 0L;

    public synchronized Long generateCustomerId() {
        return ++customerId;
    }

    public synchronized Long generateAccountId() {
        return ++accountId;
    }
}
