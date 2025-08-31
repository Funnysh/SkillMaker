package com.frantisek.herynek.SkillMaker.mapper;

import com.frantisek.herynek.SkillMaker.dto.skill.SkillCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillUserDTO;
import com.frantisek.herynek.SkillMaker.entity.SkillEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillEntity createToEntity(SkillCreateDTO source);

    SkillResponseDTO skillResponseToDto(SkillEntity source);

    List<SkillUserDTO> userSkillsDto(List<SkillEntity> source);

    SkillCreateDTO updateSkillToDto(SkillEntity source);
}
