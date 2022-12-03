package com.example.exchangeRates;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeRatesApplicationTests {

    final String[] expectedCurrencies = {"EUR", "GBP", "NGN", "USD", "YEN", "CFA", "PES"};
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void assert_correct_number_of_rates_are_returned() throws Exception {

        mvc.perform(get("/rates/NGN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.NGN").isArray())
                .andExpect(jsonPath("$.NGN", hasSize(expectedCurrencies.length - 1)));
    }

    @Test
    public void assert_400_is_returned_for_unsupported_currency() throws Exception {
        mvc.perform(get("/rates/TES"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void assert_supported_currencies_are_returned_for_currency_index_route() throws Exception {

        MvcResult mvcResult = mvc.perform(get("/currency"))
                .andExpect(status().isOk()).andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();

        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedCurrencies));
    }
}
