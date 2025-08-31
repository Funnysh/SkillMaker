package com.frantisek.herynek.SkillMaker.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillUserDTO {

    private Long id;

    private String description;

    private String name;

    private int xp;

    private int level;
}
