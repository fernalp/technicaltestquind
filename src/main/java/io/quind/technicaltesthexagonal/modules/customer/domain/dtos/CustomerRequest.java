package io.quind.technicaltesthexagonal.modules.customer.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @Pattern(regexp = "^(CC|CE|NIT|PA)$", message = "The Identification Type must be CC, CE, NIT or PA")
    private String idType;
    @NotBlank(message = "Identification Number is Required!")
    @Length(min = 6, max = 15, message = "Invalid Identification Number!")
    private String idNumber;
    @NotBlank(message = "Firstname is Required!")
    @Length(min = 2, message = "Firstname is Short!")
    private String firstname;
    @NotBlank(message = "Lastname is Required!")
    @Length(min = 2, message = "Lastname is Short")
    private String lastname;
    @NotBlank(message = "Email is Required!")
    @Pattern(
            regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
            message = "Invalid Email!"
    )
    private String email;
    @Past(message = "Invalid Date of Birth")
    private LocalDate dateOfBirth;

}
