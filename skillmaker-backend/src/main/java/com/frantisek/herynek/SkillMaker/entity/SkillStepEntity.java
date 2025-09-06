package com.frantisek.herynek.SkillMaker.entity;

import jakarta.persistence.*;

//Entity v databázi pro skill step

@Entity(name = "skill_step") //Anotace entity s názvem tabulky "skill_step"
public class SkillStepEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ManyToOne //Vztah mnoho kroků k jednomu skillu
    @JoinColumn(name = "skill_id", nullable = false) //Vytvoří sloupec v tabulce s názvem "skill_id", který nese cizý klíč skillu, který nesmí být null
    private SkillEntity skill;
}
