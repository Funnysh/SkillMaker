package com.frantisek.herynek.SkillMaker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTo na aktualizaci údajů uživatele

@Data //Lombok getter/setter
@AllArgsConstructor //Konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class UserUpdateDTO {

    @NotBlank //Nesmí být prázdné
    @Size(min = 3, max = 32) //Minimální počet znaků
    private String username;

    @Email //Musí být email formát
    @NotBlank //Nesmí být prázdné
    private String email;

    private String name;

    private String surname;

    private String about;


}
