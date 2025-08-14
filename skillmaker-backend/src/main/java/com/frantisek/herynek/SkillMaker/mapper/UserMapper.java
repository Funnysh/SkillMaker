package com.frantisek.herynek.SkillMaker.mapper;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitUserDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserRegisterResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserRegistrationDTO;
import com.frantisek.herynek.SkillMaker.entity.HabitEntity;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity registerToEntity(UserRegistrationDTO source);

    UserRegisterResponseDTO responseToDTO(UserEntity source);

    UserDTO userToDTO(UserEntity source);

    List<HabitUserDTO> habitEntitiesToUserDTO(List<HabitEntity> source);
}
