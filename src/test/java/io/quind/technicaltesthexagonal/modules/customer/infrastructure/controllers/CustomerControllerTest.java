package io.quind.technicaltesthexagonal.modules.customer.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.AlreadyExistException;
import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.YoungerException;
import io.quind.technicaltesthexagonal.modules.customer.application.services.CustomerService;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.IdType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    CustomerResponse customerResponse1;
    CustomerResponse customerResponse2;
    CustomerRequest customerRequest;

    @BeforeEach
    void setUp() {

        customerResponse1 = CustomerResponse.builder()
                .id(1L)
                .idType(IdType.CC)
                .idNumber("123123123")
                .firstname("stephen")
                .lastname("curry")
                .email("stephen@gmail.com")
                .dateOfBirth(LocalDate.of(2000, 10, 10))
                .build();

        customerResponse2 = CustomerResponse.builder()
                .id(2L)
                .idType(IdType.CC)
                .idNumber("456456456")
                .firstname("tom")
                .lastname("brady")
                .email("tombrady@gmail.com")
                .dateOfBirth(LocalDate.of(2004, 12, 23))
                .build();

        customerRequest = CustomerRequest.builder()
                .idType("CC")
                .idNumber("123123123")
                .firstname("stephen")
                .lastname("Curry")
                .email("stephen@gmail.com")
                .dateOfBirth(LocalDate.of(2000, 10, 10))
                .build();
    }

    @Test
    void customerController_CreateCustomer_ReturnOk() throws Exception {
        when(customerService.createCustomer(any(CustomerRequest.class))).thenReturn(customerResponse1);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customerRequest)));

        result.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.idNumber").value(customerResponse1.getIdNumber()));
    }
    @Test
    void customerController_CreateCustomer_ReturnConflict() throws Exception {
        when(customerService.createCustomer(any(CustomerRequest.class))).thenThrow(AlreadyExistException.class);
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customerRequest)));

        result.andExpect(status().isConflict())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.status").value(409));
    }

    @Test
    void customerController_CreateCustomer_ReturnBadRequest() throws Exception {
        when(customerService.createCustomer(any(CustomerRequest.class))).thenThrow(YoungerException.class);
        ResultActions resultYoungerException = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customerRequest)));

        resultYoungerException
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void customerController_CreateCustomerWithEmailInvalid_ReturnBadRequest() throws Exception{

        customerRequest.setEmail("isa@champs.f");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customerRequest)));

        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void customerController_CreateCustomerWithFirstnameInvalid_ReturnBadRequest() throws Exception{

        customerRequest.setFirstname("f");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(customerRequest)));

        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.date").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").isNotEmpty());
    }

    @Test
    void customerController_GetAllCustomer_ReturnOk() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(List.of(customerResponse1, customerResponse2));
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(customerResponse1.getId()))
                .andExpect(jsonPath("$[0].idType").value(customerResponse1.getIdType().name()))
                .andExpect(jsonPath("$[0].idNumber").value(customerResponse1.getIdNumber()))
                .andExpect(jsonPath("$[0].firstname").value(customerResponse1.getFirstname()))
                .andExpect(jsonPath("$[1].lastname").value(customerResponse2.getLastname()))
                .andExpect(jsonPath("$[1].email").value(customerResponse2.getEmail()))
                .andExpect(jsonPath("$[1].dateOfBirth").value(customerResponse2.getDateOfBirth().toString()));
        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    void customerController_GetAllCustomer_ReturnNotFound() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(List.of());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers"));
        result.andExpect(status().isNotFound());
        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    void customerController_GetCustomerById_ReturnOk() throws Exception {
        when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(Optional.of(customerResponse1));
        Long id = 1L;
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/" + id));
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.id").value(customerResponse1.getId()))
                .andExpect(jsonPath("$.idType").value(customerResponse1.getIdType().name()))
                .andExpect(jsonPath("$.idNumber").value(customerResponse1.getIdNumber()))
                .andExpect(jsonPath("$.firstname").value(customerResponse1.getFirstname()))
                .andExpect(jsonPath("$.lastname").value(customerResponse1.getLastname()))
                .andExpect(jsonPath("$.email").value(customerResponse1.getEmail()))
                .andExpect(jsonPath("$.dateOfBirth").value(customerResponse1.getDateOfBirth().toString()));
        verify(customerService, times(1)).getCustomerById(id);
    }

    @Test
    void customerController_GetCustomerById_ReturnThrow() throws Exception {
        Long id = 2L;
        when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(Optional.empty());
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/"+id));
        result.andExpect(status().isNotFound());
        verify(customerService, times(1)).getCustomerById(id);
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}