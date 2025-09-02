package com.frantisek.herynek.SkillMaker.service;

import com.frantisek.herynek.SkillMaker.dto.skill.SkillCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillUserDTO;
import com.frantisek.herynek.SkillMaker.entity.HabitEntity;
import com.frantisek.herynek.SkillMaker.entity.SkillEntity;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import com.frantisek.herynek.SkillMaker.mapper.SkillMapper;
import com.frantisek.herynek.SkillMaker.repository.SkillRepository;
import com.frantisek.herynek.SkillMaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

//Vyloženě stejnej postup jako u habit, jenom je to skill

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private UserRepository userRepository;

    public SkillResponseDTO create(SkillCreateDTO skillCreateDTO) {
        UserEntity user = fetchUserByUsername();

        SkillEntity skill = skillMapper.createToEntity(skillCreateDTO);

        skill.setStartDate(LocalDate.now());
        skill.setUser(user);
        skill.setXp(0);
        skill.setLevel(1);

        skillRepository.save(skill);

        return skillMapper.skillResponseToDto(skill);
    }

    public List<SkillUserDTO> get() {
        UserEntity user = fetchUserByUsername();

        return skillMapper.userSkillsDto(user.getSkills());
    }

    public SkillCreateDTO update(SkillCreateDTO skillCreateDTO, Long id) {
        UserEntity user = fetchUserByUsername();

        SkillEntity skill = fetchSkillByIdAndUsername(id, user);

        skill.setName(skillCreateDTO.getName());
        skill.setDescription(skillCreateDTO.getDescription());

        skillRepository.save(skill);

        return skillMapper.updateSkillToDto(skill);
    }

    public SkillResponseDTO delete(Long id) {
        UserEntity user = fetchUserByUsername();

        SkillEntity skill = fetchSkillByIdAndUsername(id, user);

        skillRepository.delete(skill);

        return skillMapper.skillResponseToDto(skill);
    }

    private UserEntity fetchUserByUsername() {
        return userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));
    }

    private SkillEntity fetchSkillByIdAndUsername(Long id, UserEntity user) {
        return skillRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Skill nenalezen nebo nepatří tomuto uživateli"));
    }
}
