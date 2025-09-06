package com.frantisek.herynek.SkillMaker.repository;

import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Přístup k databázi

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    //Najde uživatele pomocí uživatelského jména
    Optional<UserEntity> findByUsername(String name);

    //Vrátí, zda uživatel existuje podle uživatelského jména
    boolean existsByUsername(String username);

    boolean existsByUsernameIgnoreCase(String username);
}
