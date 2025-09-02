package com.frantisek.herynek.SkillMaker.entity;

import com.frantisek.herynek.SkillMaker.enums.HabitFrequency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//Entita uložená v databázi pro habit

@Entity(name = "habit") //Anotace entity,v tabulce pojmenovaná habit
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

    @ManyToOne //Více instancí patří jednomu uživateli
    @JoinColumn(name = "user_id", nullable = false) //Sloupec s cizím klíčem uživatele, který se jmenuje "user_id" a nesmí být null
    private UserEntity user;

    //Gamefikace
    private int streak;

    private int bestStreak;

    private int xp;

    private int level;

    private int completions;

    private LocalDate lastCompleted;
}
