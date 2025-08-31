package com.frantisek.herynek.SkillMaker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "skill")
@Getter
@Setter
public class SkillEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillStepEntity> steps = new ArrayList<>();

    //Gamefikace
    private int xp;

    private int level;

    private LocalDate lastCompleted;
}
