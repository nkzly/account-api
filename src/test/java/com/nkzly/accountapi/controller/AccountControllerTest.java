package com.nkzly.accountapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nkzly.accountapi.model.Account;
import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.model.Transaction;
import com.nkzly.accountapi.model.swaggerModel.AccountRequest;
import com.nkzly.accountapi.repository.AccountRepository;
import com.nkzly.accountapi.repository.AccountTransactionRepository;
import com.nkzly.accountapi.repository.CustomerRepository;
import com.nkzly.accountapi.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class AccountControllerTest {
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public AccountTransactionRepository transactionRepository;
    @Autowired
    public AccountService accountService;

    public final ObjectMapper mapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void createAccount() throws Exception {
        Customer customer = customerRepository.save(new Customer("Nihat", "Kızılay"));
        AccountRequest request = new AccountRequest(2, BigDecimal.TEN);

        this.mockMvc.perform(post("/api/v1/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getCustomerByCustomerNumber() throws Exception {
        Customer customer = customerRepository.save(new Customer("Nihat", "Kızılay"));
        Account account = new Account(
                customer,
                BigDecimal.TEN,
                LocalDateTime.now());
        Transaction transaction= new Transaction(
                account,
                BigDecimal.TEN,
                LocalDateTime.now());

            account.getTransactions().add(transaction);

        accountRepository.save(account);
        transactionRepository.save(transaction);
        this.mockMvc.perform(get("/api/v1/account/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(customer.getId())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.customerId", is(customer.getId())))
                .andExpect(jsonPath("$.name", is(customer.getName())))
                .andExpect(jsonPath("$.surname", is(customer.getSurname())))
                .andExpect(jsonPath("$.accounts[0].balance", is(10.0)))
                .andExpect(jsonPath("$.accounts[0].transactions", hasSize(1)));
    }
}