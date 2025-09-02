package com.frantisek.herynek.SkillMaker.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO odpověď na vytvoření skillu. Vrací name

@Data //Lombok na getter/setter a další metody
@AllArgsConstructor //Parametrický konstruktor
@NoArgsConstructor //Bezparametrický konstruktor
public class SkillResponseDTO {

    private String name;
}
