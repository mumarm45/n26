package com.transaction.controller;


import com.transaction.model.Transaction;
import com.transaction.util.StoreTransaction;
import com.transaction.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by mumarm45 on 13/07/2018.
 */

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private StoreTransaction storeTransaction;
    private TimeService timeService;

    @Autowired
    public TransactionController(StoreTransaction storeTransaction,TimeService timeService) {
        this.storeTransaction=storeTransaction;
        this.timeService=timeService;
    }


    @RequestMapping(method =  POST)
    public ResponseEntity create(@RequestBody Transaction transaction){
     if(!timeService.checkTimeLessThan60(transaction.getTimestamp())){
         return new ResponseEntity(HttpStatus.NO_CONTENT);
     }
     storeTransaction.create(transaction);
     return new ResponseEntity(HttpStatus.CREATED);
    }
}
