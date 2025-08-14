package com.frantisek.herynek.SkillMaker.repository;

import com.frantisek.herynek.SkillMaker.entity.HabitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitRepository extends JpaRepository<HabitEntity, Long> {

}
