package io.quind.technicaltesthexagonal.modules.customer.infrastructure.controllers;

import io.quind.technicaltesthexagonal.modules.customer.application.services.CustomerService;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerResponse;
import io.quind.technicaltesthexagonal.modules.customer.domain.models.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        List<CustomerResponse> customers = customerService.getAllCustomer();
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customerId") Long id){
        return customerService.getCustomer(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("customerId") Long id, @RequestBody Customer customer){
        if ((!id.equals(customer.getCustomerId())) || customerService.getCustomer(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else {
            CustomerResponse updatedCustomer= customerService.updateCustomer(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long id){
        if (customerService.getCustomer(id).isPresent()){
            customerService.deleteCustomer(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
