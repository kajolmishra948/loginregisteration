package com.NeoPractice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    private String username;
    @NotNull(message = "firstname is required")
    private String firstname;
    private String lastname;
    @Pattern(regexp = "^(?=.*[0-9])"+"(?=.*[a-z])(?=.*[A-Z])"+"(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$",message = "password should be at least 8 characters and at most 15 characters. password have at least 1 uppercase character, 1 lowercase character, 1 special character and 1 digit")
    private String password;
    @Pattern(regexp = "(0|91)?[7-9]{1}[0-9]{9}",message = "mobile number should be 10 digits and starting with 0 or 91")
    private String mobile;
    @Column(unique = true)
    @Email
    private String email;

}
