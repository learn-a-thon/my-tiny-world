package com.yunbok.searchapi.v1.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunbok.searchapi.v1.authentication.vo.response.AccessTokenResponse;
import com.yunbok.searchapi.v1.authentication.presentation.AuthenticationController;
import com.yunbok.searchapi.v1.authentication.vo.request.AccessTokenRequest;
import com.yunbok.searchapi.v1.authentication.vo.request.ApiKeyRequest;
import com.yunbok.searchapi.v1.authentication.vo.response.ApiKeyResponse;
import com.yunbok.searchapi.v1.authentication.application.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void testGetApiKey() throws Exception {
        // given
        String account = "testAccount";
        String password = "testPassword";
        String apiKey = "testApiKey";

        ApiKeyRequest apiKeyRequest = new ApiKeyRequest(account, password);
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

        assertEquals(apiKeyResponse.apikey(), responseDto.apikey());
    }

    @Test
    public void testGetAccessToken() throws Exception {
        // given
        AccessTokenRequest request = new AccessTokenRequest("testAccount");
        AccessTokenResponse response = AccessTokenResponse.generateTokenOf(
                "accessToken",
                100000L);

        when(authenticationService.getAccessToken(any(), any())).thenReturn(response);

        // when
        RequestBuilder requestBuilder = post("/v1/authentication/access-token")
                .header("Authorization", "apiKey")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        // then
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}