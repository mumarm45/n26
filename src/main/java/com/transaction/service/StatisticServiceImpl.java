package com.transaction.service;

import com.transaction.model.Statistic;
import com.transaction.model.Transaction;
import com.transaction.util.StoreTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by mumarm45 on 13/07/2018.
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private StoreTransaction transactions;

    @Override
    public Statistic getStatistic() {
        Statistic statistic = new Statistic();
        Supplier<Stream<Transaction>> transactionStream = transactions.getTransaction();
        long count = transactionStream.get().count();
        if(count>0){
            statistic.setCount(transactionStream.get().count());
            statistic.setMax(transactionStream.get().mapToDouble(Transaction::getAmount).reduce(Double::max).getAsDouble());
            statistic.setMin(transactionStream.get().mapToDouble(Transaction::getAmount).reduce(Double::min).getAsDouble());
            statistic.setSum(transactionStream.get().mapToDouble(Transaction::getAmount).sum());
            statistic.setAvg(statistic.getSum() / statistic.getAvg());
        }

       return statistic;
    }
}
