package com.frantisek.herynek.SkillMaker.dto.habit;

import com.frantisek.herynek.SkillMaker.enums.HabitFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO pro vytvoření návyku. Vstupy name, description a ENUM frequency

@Data //Anotace Lombok. Vytvoří gettery a settery (a toString(), equals(), hashCode())
@AllArgsConstructor //Konstruktor se všemy atributy
@NoArgsConstructor //Konstruktor bez parametrů
public class HabitCreateDTO {

    private String name;

    private String description;

    private HabitFrequency frequency;
}
