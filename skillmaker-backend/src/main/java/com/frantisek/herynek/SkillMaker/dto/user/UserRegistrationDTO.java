package com.frantisek.herynek.SkillMaker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {

    @NotBlank(message = "Uživatelské jméno je povinné!")
    private String username;

    @Email(message = "Neplatný email!")
    private String email;

    @NotBlank(message = "Heslo je povinné!")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků!")
    private String password;

}
