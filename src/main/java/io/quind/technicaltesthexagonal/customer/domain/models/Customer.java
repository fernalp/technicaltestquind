package io.quind.technicaltesthexagonal.customer.domain.models;

import io.quind.technicaltesthexagonal.customer.domain.dtos.CustomerRequest;
import io.quind.technicaltesthexagonal.customer.domain.dtos.CustomerResponse;
import lombok.Builder;

import java.util.Date;

@Builder
public class Customer {
    private Long customerId;
    private IdType idType;
    private Integer idNumber;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthdate;
    private Date createdAt;
    private Date updatedAt;

    public Customer(Long customerId, IdType idType, Integer idNumber, String firstname, String lastname, String email, Date birthdate, Date createdAt, Date updatedAt) {
        this.customerId = customerId;
        this.idType = idType;
        this.idNumber = idNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.birthdate = birthdate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Customer fromCustomerRequest(CustomerRequest customerRequest){
        return Customer.builder()
                .birthdate(customerRequest.getBirthdate())
                .email(customerRequest.getEmail())
                .firstname(customerRequest.getFirstname())
                .lastname(customerRequest.getLastname())
                .idType(IdType.valueOf(customerRequest.getIdType()))
                .idNumber(customerRequest.getIdNumber())
                .build();
    }
    
    public CustomerResponse toCustomerResponse(Customer customer){
        return (CustomerResponse) CustomerResponse.builder()
                .birthdate(customer.getBirthdate())
                .email(customer.getEmail())
                .idType(customer.getIdType().getDescription())
                .firstname(customer.getFirstname())
                .idNumber(customer.getIdNumber())
                .lastname(customer.getLastname())
                .build();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public Integer getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Integer idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
