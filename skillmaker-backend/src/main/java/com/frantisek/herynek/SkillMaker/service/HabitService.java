package com.frantisek.herynek.SkillMaker.service;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitUserDTO;
import com.frantisek.herynek.SkillMaker.entity.HabitEntity;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import com.frantisek.herynek.SkillMaker.mapper.HabitMapper;
import com.frantisek.herynek.SkillMaker.repository.HabitRepository;
import com.frantisek.herynek.SkillMaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitMapper habitMapper;

    @Autowired
    private UserRepository userRepository;

    public HabitResponseDTO create(HabitCreateDTO habitCreateDTO) {
        UserEntity user = fetchUserByUsername();

        HabitEntity habit = habitMapper.createToEntity(habitCreateDTO);

        habit.setUser(user);
        habit.setStartDate(LocalDate.now());
        habit.setXp(0);
        habit.setActive(true);
        habit.setStreak(0);
        habit.setBestStreak(0);
        habit.setLevel(1);
        habit.setCompletions(0);

        habitRepository.save(habit);

        return habitMapper.habitResponseToDto(habit);
    }

    public List<HabitUserDTO> get() {
        UserEntity user = fetchUserByUsername();

        return habitMapper.userHabitsToDto(user.getHabits());
    }

    public HabitCreateDTO update(HabitCreateDTO habitCreateDTO, Long id) {
        UserEntity user = fetchUserByUsername();

        HabitEntity habit = habitRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Návyk nenalezen nebo nepatří tomuto uživateli"));

        habit.setName(habitCreateDTO.getName());
        habit.setDescription(habitCreateDTO.getDescription());
        habit.setFrequency(habitCreateDTO.getFrequency());

        habitRepository.save(habit);

        return habitMapper.updateHabitToDto(habit);
    }


    private UserEntity fetchUserByUsername() {
        return userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));
    }
}
