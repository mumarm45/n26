package com.transaction.util;


import com.transaction.model.Transaction;
import com.transaction.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;



/**
 * Created by mumarm45 on 13/07/2018.
 */
@Service
public class StoreTransactionImpl implements StoreTransaction {

    private TimeService timeService;
    private final AtomicReferenceArray<Transaction>  transactions;
    @Autowired
    public StoreTransactionImpl(TimeService timeService) {
        this.timeService = timeService;
        this.transactions = new AtomicReferenceArray<Transaction>(60);
    }
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Override
    public synchronized void create(Transaction t){
        int index = timeService.currentRecordMint(t.getTimestamp()) % transactions.length();
        transactions.set(index,t);
    }



    @Override
    public Supplier<Stream<Transaction>> getTransaction() {
       return ()-> IntStream.rangeClosed(timeService.getLastOneMint(),timeService.currentMint())
                .mapToObj(obj-> transactions.get(obj%transactions.length()))
                .filter(Objects::nonNull);

    }
}
