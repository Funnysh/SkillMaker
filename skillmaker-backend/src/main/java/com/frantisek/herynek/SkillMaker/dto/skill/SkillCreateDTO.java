package com.frantisek.herynek.SkillMaker.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO na vytvoření skillu. Vstupy name, description

@Data //Lombok na getter/setter a další metody
@AllArgsConstructor //Parametrický konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class SkillCreateDTO {

    private String name;

    private String description;
}
