package com.transaction.util;


import com.transaction.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by mumarm45 on 13/07/2018.
 */

public interface StoreTransaction {

    public  void create(Transaction transaction);
    public Supplier<Stream<Transaction>> getTransaction();
}
