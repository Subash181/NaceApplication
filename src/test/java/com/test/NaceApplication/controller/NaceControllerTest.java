package com.test.NaceApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.NaceApplication.model.Nace;
import com.test.NaceApplication.service.NaceService;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import lombok.*;

@WebMvcTest
public class NaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NaceService naceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenNaceObject_whenPutNACE_thenReturnSavedNaceInfo() throws Exception{

        // given - precondition or setup
        Nace nacew = new Nace(1,2,"code","parent","description","includes","alsoIncludes","ruling","excludes","refrence");
        given(naceService.putNaceDetails(any(Nace.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/putNaceDetails")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nacew)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId",
                        is(nacew.getOrderId())))
                .andExpect(jsonPath("$.level",
                        is(nacew.getLevel())))
                .andExpect(jsonPath("$.code",
                        is(nacew.getCode())));

    }

    @Test
    public void givenOrderId_whenGetByOderId_thenReturnNaceObject() throws Exception{
        // given - precondition or setup
        Integer orderId = 2;
        Nace nacew = new Nace(1,2,"code","parent","description","includes","alsoIncludes","ruling","excludes","refrence");
        given(naceService.getNaceByOrder(orderId)).willReturn(Optional.of(nacew));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/getNaceDetails/{order}", orderId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.orderId",
                        is(nacew.getOrderId())))
                .andExpect(jsonPath("$.level",
                        is(nacew.getLevel())))
                .andExpect(jsonPath("$.code",
                        is(nacew.getCode())));

    }
    @Test
    public void givenOrderId_whenGetByOderId_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        Integer orderId = 2;
        Nace nacew = new Nace(1,2,"code","parent","description","includes","alsoIncludes","ruling","excludes","refrence");
        given(naceService.getNaceByOrder(orderId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/getNaceDetails/{order}", orderId));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }

}
