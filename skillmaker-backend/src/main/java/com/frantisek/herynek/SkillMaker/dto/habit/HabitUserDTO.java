package com.frantisek.herynek.SkillMaker.dto.habit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO na návyk uživatele. Vrací id, description, name, streak, bestStreak, xp, level

@Data //Lombok na vytvoření getterů a setterů a na další metody
@AllArgsConstructor //Parametrický kontrukotr
@NoArgsConstructor //Bezparamatrický konstruktor
public class HabitUserDTO {

    private Long id;

    private String description;

    private String name;

    private int streak;

    private int bestStreak;

    private int xp;

    private int level;
}
