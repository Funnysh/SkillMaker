package com.frantisek.herynek.SkillMaker.repository;

import com.frantisek.herynek.SkillMaker.entity.SkillEntity;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Přístup k databázi

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    //Najde skill pomocí id a uživatele
    Optional<SkillEntity> findByIdAndUser(Long id, UserEntity user);
}
