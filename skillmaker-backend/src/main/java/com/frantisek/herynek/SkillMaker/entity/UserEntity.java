package com.frantisek.herynek.SkillMaker.entity;

import com.frantisek.herynek.SkillMaker.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Entita v databázi pro uživatele

@Entity(name = "user") //Název tabulky "user"
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true) //Nesmí být null a musí být unikátní oproti ostatním záznamům
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable  = false)
    private String password;

    @Enumerated(EnumType.STRING) //Převedení vstupu typu string na ENUM
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, updatable = false) //nelze aktualizovat tento sloupec
    @CreationTimestamp //vytvoření časové známky po vytvoření
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private String name;

    @Column(nullable = true)
    private String surname;

    @Column(nullable = true)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //Jeden uživatel má více habits. Vlastník vztahu je user. Při odstranění habit se vymaže z databáze. Když přestane být součást seznamu, odstraní se
    private List<HabitEntity> habits = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //Jeden uživatel má více skills. Vlastník vztahu je user. Při odstranění habit se vymaže z databáze. Když přestane být součást seznamu, odstraní se
    private List<SkillEntity> skills = new ArrayList<>();

    private int level;

    private int xp;

    private int totalXp;
}
