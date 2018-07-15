package com.transaction.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.model.Transaction;
import com.transaction.service.TimeService;
import com.transaction.util.StoreTransaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mumarm45 on 14/07/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest {


    private MockMvc mockMvc;
    @MockBean
    private StoreTransaction storeTransaction;
    @MockBean
    private TimeService timeService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void acceptShouldAccept() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setAmount(12.4);
        transaction.setTimestamp(1534352204000L);
        when(timeService.checkTimeLessThan60(anyLong())).thenReturn(true);
        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(transaction))
        ).andExpect(status().isCreated());

        verify(timeService,times(1)).checkTimeLessThan60(anyLong());
    }

    @Test
    public void acceptShouldNotAccept() throws Exception {
        when(timeService.checkTimeLessThan60(anyLong())).thenReturn(false);
        mockMvc.perform(post("/api/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\": 12.3,\"timestamp\": 0}"))
                .andExpect(
                        status().isNoContent());

        verify(timeService,times(1)).checkTimeLessThan60(anyLong());
    }

}