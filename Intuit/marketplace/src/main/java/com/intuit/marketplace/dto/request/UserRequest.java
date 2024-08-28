package com.intuit.marketplace.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number is not valid") // Example regex for phone numbers
    private String phoneNumber;
}
