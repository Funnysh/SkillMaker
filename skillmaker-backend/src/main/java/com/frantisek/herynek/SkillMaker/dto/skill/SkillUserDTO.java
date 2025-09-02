package com.frantisek.herynek.SkillMaker.dto.skill;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//DTO na skill uživatele. Vrací id, decription, name, xp, level

@Data //Lombok getter/setter a další metody
@AllArgsConstructor //Parametrický konstruktor
@NoArgsConstructor //Bezparamatrický konstruktor
public class SkillUserDTO {

    private Long id;

    private String description;

    private String name;

    private int xp;

    private int level;
}
