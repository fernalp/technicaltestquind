package io.quind.technicaltesthexagonal.modules.customer.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quind.technicaltesthexagonal.modules.customer.application.services.CustomerService;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = CustomerController.class)
//@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    List<CustomerResponse> customerResponses;

    @BeforeEach
    void setUp() {
        customerResponses = List.of(
                CustomerResponse.builder().build(),
                CustomerResponse.builder().build()
        );


    }

    @Test
    void createCustomer() {
    }

    @Test
    void customerController_GetAllCustomer_ReturnOk() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(customerResponses);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"));

        result.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.field").value(""))
        ;
        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    void customerController_GetAllCustomer_ReturnNotFound() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(List.of());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"));

        result.andExpect(MockMvcResultMatchers.status().isNotFound())
        ;
        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    void getCustomerById() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}