package com.frantisek.herynek.SkillMaker.repository;

import com.frantisek.herynek.SkillMaker.entity.HabitEntity;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Přístup k databázi

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

    //Najde habit pomocí id a uživatele
    Optional<HabitEntity> findByIdAndUser(Long id, UserEntity user);
}
