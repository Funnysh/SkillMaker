package com.frantisek.herynek.SkillMaker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO odpověď na login. Vrací JWT uživatele

@Data //Lombok getter/setter
@AllArgsConstructor //Konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class UserLoginResponseDTO {

    private String token;

    private String username;
}
