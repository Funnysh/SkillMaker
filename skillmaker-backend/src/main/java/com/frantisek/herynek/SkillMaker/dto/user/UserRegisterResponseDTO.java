package com.frantisek.herynek.SkillMaker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO odpověď na registraci

@Data //Lombok getter/setter
@AllArgsConstructor //Konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class UserRegisterResponseDTO {

    private Long id;

    private String username;
}
