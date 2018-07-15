package com.transaction.controller;

import com.transaction.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by mumarm45 on 13/07/2018.
 */
@RestController
@RequestMapping("/api/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @RequestMapping(method = GET)
    public ResponseEntity getStatistic(){
        return new  ResponseEntity(statisticService.getStatistic(),HttpStatus.OK);
    }
}
