package com.frantisek.herynek.SkillMaker.dto.habit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitUserDTO {

    private Long id;

    private String description;

    private String name;

    private int streak;

    private int bestStreak;

    private int xp;

    private int level;
}
