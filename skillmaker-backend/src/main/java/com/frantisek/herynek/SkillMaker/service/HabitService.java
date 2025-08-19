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

        HabitEntity habit = fetchHabitByIdAndUsername(id, user);

        habit.setName(habitCreateDTO.getName());
        habit.setDescription(habitCreateDTO.getDescription());
        habit.setFrequency(habitCreateDTO.getFrequency());

        habitRepository.save(habit);

        return habitMapper.updateHabitToDto(habit);
    }

    public HabitUserDTO complete(Long id) {
        UserEntity user = fetchUserByUsername();
        HabitEntity habit = fetchHabitByIdAndUsername(id, user);
        int addXp = 5;
        float streakBonus = (float)habit.getStreak() / 2;

        //habit
        habit.setCompletions(habit.getCompletions() + 1);
            habit.setStreak(habit.getStreak() + 1);
            habit.setLastCompleted(LocalDate.now());
        if(habit.getStreak() > habit.getBestStreak()) {
            habit.setBestStreak(habit.getStreak());
        }
        int gainedXp = addXp + (int)(streakBonus);
        habit.setXp(habit.getXp() + gainedXp);

        if (habit.getXp() >= habit.getLevel() * 10) {
            habit.setLevel(habit.getLevel() + 1);
            habit.setXp(0);
        }

        //user
        user.setXp(user.getXp() + gainedXp);
        user.setTotalXp(user.getTotalXp() + gainedXp);

        if (user.getXp() >= user.getLevel() * 10) {
            user.setLevel(user.getLevel() + 1);
            user.setXp(0);
        }

        habitRepository.save(habit);
        userRepository.save(user);

        return habitMapper.habitToDto(habit);
    }


    public HabitResponseDTO delete(Long id) {
        UserEntity user = fetchUserByUsername();

        HabitEntity habit = fetchHabitByIdAndUsername(id, user);

        habitRepository.delete(habit);

        return habitMapper.habitResponseToDto(habit);
    }

    private UserEntity fetchUserByUsername() {
        return userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));
    }

    private HabitEntity fetchHabitByIdAndUsername(Long id, UserEntity user) {
        return habitRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Návyk nenalezen nebo nepatří tomuto uživateli"));
    }
}
