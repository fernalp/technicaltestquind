package io.quind.technicaltesthexagonal.modules.customer.application.utils;

import io.quind.technicaltesthexagonal.modules.customer.domain.dtos.CustomerRequest;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsCustomer {

    private static final Pattern REGEX_EMAIL = Pattern.compile("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");

    private static int calculateAge(LocalDate dateOfBirth){
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }

    private static boolean compareEmail(String email){
        Matcher matcher = REGEX_EMAIL.matcher(email);
        return matcher.matches();
    }

    public static void validateCustomerRequest(CustomerRequest customerRequest){
        int age = calculateAge(customerRequest.getDateOfBirth());
        boolean emailIsCorrect = compareEmail(customerRequest.getEmail());

        if (age < 18) {
            throw new IllegalArgumentException("The minimun age is 18 years old");
        }
        if (!emailIsCorrect){
            throw new IllegalArgumentException("The email isn't correct");
        }
        if (customerRequest.getFirstname().length() < 2 || customerRequest.getLastname().length() < 2){
            throw new IllegalArgumentException("The firstname and lastname required minimum 2 character");
        }
    }

}
