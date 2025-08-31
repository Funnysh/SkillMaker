package com.frantisek.herynek.SkillMaker.entity;

import jakarta.persistence.*;

@Entity(name = "skill_step")
public class SkillStepEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;
}
