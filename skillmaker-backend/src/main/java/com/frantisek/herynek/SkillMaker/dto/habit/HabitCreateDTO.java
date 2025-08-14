package com.frantisek.herynek.SkillMaker.dto.habit;

import com.frantisek.herynek.SkillMaker.enums.HabitFrequency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitCreateDTO {

    private String name;

    private String description;

    private HabitFrequency frequency;
}
