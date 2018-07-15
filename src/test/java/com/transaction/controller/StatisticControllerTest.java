package com.transaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.model.Statistic;
import com.transaction.service.StatisticService;
import com.transaction.util.StoreTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by mumarm45 on 14/07/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticControllerTest {
    private MockMvc mockMvc;
    @MockBean
    private StatisticService statisticService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void check_no_statistic_available() throws Exception {
        when(statisticService.getStatistic()).thenReturn(new Statistic());
        mockMvc.perform(get("/api/statistics")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(statisticService,atLeastOnce()).getStatistic();
        assertThat(statisticService.getStatistic().getCount()).isEqualTo(0);
    }
}