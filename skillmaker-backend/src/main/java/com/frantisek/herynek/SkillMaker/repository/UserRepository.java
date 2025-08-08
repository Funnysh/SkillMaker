package com.frantisek.herynek.SkillMaker.repository;

import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByUsername(String name);

    boolean existsByUsername(String username);
}
