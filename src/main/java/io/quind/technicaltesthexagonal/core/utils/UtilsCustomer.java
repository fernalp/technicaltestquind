package io.quind.technicaltesthexagonal.core.utils;

import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.AlreadyExistException;
import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.YoungerException;
import io.quind.technicaltesthexagonal.modules.customer.domain.ports.out.CustomerRepositoryPort;

import java.time.LocalDate;
import java.time.Period;
public class UtilsCustomer {
    private final CustomerRepositoryPort customerRepositoryPort;

    public UtilsCustomer(CustomerRepositoryPort customerRepositoryPort) {
        this.customerRepositoryPort = customerRepositoryPort;
    }

    public int calculateAge(LocalDate dateOfBirth){
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }
    public void validateAgeByDate(LocalDate date){
        int age = calculateAge(date);
        if (age < 18) {
            throw new YoungerException("The minimun age is 18 years old");
        }
    }
    public void validateIdNumber(String idNumber){
        if (customerRepositoryPort.existIdNumber(idNumber)){
            throw new AlreadyExistException("Identification number " + idNumber +  " already exist!");
        }
    }
    public void validateEmail(String email){
        if (customerRepositoryPort.existEmail(email)){
            throw new AlreadyExistException("Email "+ email + "already exist!");
        }
    }

}
