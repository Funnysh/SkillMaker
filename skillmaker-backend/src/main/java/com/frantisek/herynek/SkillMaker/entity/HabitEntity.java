package com.frantisek.herynek.SkillMaker.entity;

import com.frantisek.herynek.SkillMaker.enums.HabitFrequency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "habit")
@Getter
@Setter
public class HabitEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    private HabitFrequency frequency;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    //Gamefikace
    private int streak;

    private int bestStreak;

    private int xp;

    private int level;

    private int completions;

    private LocalDate lastCompleted;
}
