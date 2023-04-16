package com.yunbok.searchapi.v1.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunbok.searchapi.v1.authentication.dto.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.dto.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void testGetApiKey() throws Exception {
        // given
        String account = "testAccount";
        String password = "testPassword";
        String apiKey = "testApiKey";

        ApiKeyRequest apiKeyRequest = ApiKeyRequest.requestOf(account, password);
        ApiKeyResponse apiKeyResponse = ApiKeyResponse.successOf(apiKey);

        when(authenticationService.getApiKey(refEq(apiKeyRequest))).thenReturn(apiKeyResponse);

        // when
        MvcResult mvcResult = mockMvc.perform(post("/v1/authentication/api-key")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(apiKeyRequest)))
                .andExpect(status().isOk())
                .andReturn();

        // then
        String responseContent = mvcResult.getResponse().getContentAsString();
        ApiKeyResponse responseDto = new ObjectMapper().readValue(responseContent, ApiKeyResponse.class);

        assertEquals(apiKeyResponse.getApiKey(), responseDto.getApiKey());
    }
}