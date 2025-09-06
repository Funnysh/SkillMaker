package com.frantisek.herynek.SkillMaker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO na registraci s validací dat

@Data //lombok getter/setter
@AllArgsConstructor //Konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class UserRegistrationDTO {

    @NotBlank(message = "Uživatelské jméno je povinné!") //Nesmí být prázdné
    private String username;

    @Email(message = "Neplatný email!") //Požadovaný email formát
    private String email;

    @NotBlank(message = "Heslo je povinné!") //Nesmí být prázdné
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků!") //Minimálné počet znaků
    private String password;

}
