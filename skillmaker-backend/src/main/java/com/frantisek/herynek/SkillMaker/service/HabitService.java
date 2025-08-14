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

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitMapper habitMapper;

    @Autowired
    private UserRepository userRepository;

    public HabitResponseDTO create(HabitCreateDTO habitCreateDTO) {
        UserEntity user = userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));

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
        UserEntity user = userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));

        return habitMapper.userHabitsToDto(user.getHabits());
    }
}
