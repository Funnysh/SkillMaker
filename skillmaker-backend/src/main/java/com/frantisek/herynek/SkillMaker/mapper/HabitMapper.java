package com.frantisek.herynek.SkillMaker.mapper;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitUserDTO;
import com.frantisek.herynek.SkillMaker.entity.HabitEntity;
import org.mapstruct.Mapper;

import java.util.List;

//Mapper na převod dat mezi entitou/DTO a naopak

@Mapper(componentModel = "spring") //Anotace pro mapper
public interface HabitMapper {

    HabitEntity createToEntity(HabitCreateDTO source);

    HabitUserDTO habitToDto(HabitEntity source);

    HabitResponseDTO habitResponseToDto(HabitEntity source);

    List<HabitUserDTO> userHabitsToDto(List<HabitEntity> source);

    HabitCreateDTO updateHabitToDto(HabitEntity source);
}
