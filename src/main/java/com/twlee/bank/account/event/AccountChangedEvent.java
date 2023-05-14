package com.twlee.bank.account.event;

import com.twlee.bank.account.domain.Cash;
import com.twlee.bank.account.domain.TransactionType;
import com.twlee.bank.common.event.Event;

public class AccountChangedEvent extends Event {
    private final Cash before;
    private final Cash after;
    private final TransactionType transactionType;

    public AccountChangedEvent(Cash before, Cash after, TransactionType transactionType) {
        this.before = before;
        this.after = after;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return String.format(
                "[%d] Account Changed! %s Cash %d -> %d",
                getTimestamp(),
                transactionType,
                before.getAmount().intValue(),
                after.getAmount().intValue());
    }
}
