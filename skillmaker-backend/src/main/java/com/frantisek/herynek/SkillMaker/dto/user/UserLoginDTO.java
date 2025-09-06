package com.frantisek.herynek.SkillMaker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO na login

@Data //Lombok getter/setter
@AllArgsConstructor //Konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class UserLoginDTO {

    private String username;

    private String password;
}
