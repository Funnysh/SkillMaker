package com.frantisek.herynek.SkillMaker.dto.habit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO odpověď po vytvoření. Vrací name

@Data //Lombok anotace pro vytvoření getterů a setterů a další metody
@AllArgsConstructor //Kontrulktor se všemi parametry
@NoArgsConstructor //Kontruktor bez parametrů
public class HabitResponseDTO {

    private String name;
}
