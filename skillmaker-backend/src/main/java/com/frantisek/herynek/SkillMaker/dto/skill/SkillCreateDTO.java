package com.frantisek.herynek.SkillMaker.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCreateDTO {

    private String name;

    private String description;
}
