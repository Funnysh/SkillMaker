package com.frantisek.herynek.SkillMaker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//Entita uložená v databázi pro skill

@Entity(name = "skill") //Anotace entity, název tabulky "skill"
@Getter
@Setter
public class SkillEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private LocalDate startDate;

    @ManyToOne //Vztah více instancí pro jednoho uživatele
    @JoinColumn(name = "user_id", nullable = false) //Vytvoří sloupec "user_id" s cizím klíčem uživatele, nesmí být null
    private UserEntity user;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true) //Skill má seznam více steps. Vlatník je skill. Když se odstraní skill, odstraní se i všechny steps. Pokud step přestane být součástí senamu, odstraní se.
    private List<SkillStepEntity> steps = new ArrayList<>();

    //Gamefikace
    private int xp;

    private int level;

    private LocalDate lastCompleted;
}
