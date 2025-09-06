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

//Service na správu návyků

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitMapper habitMapper;

    @Autowired
    private UserRepository userRepository;

    //Metoda na vytvoření návyku. Přijímá DTO informace z webu a vrací odpověď
    public HabitResponseDTO create(HabitCreateDTO habitCreateDTO) {

        //Najde uživatele pomocí uživatelského jména z JWT
        UserEntity user = fetchUserByUsername();

        //Vytvoření habit entity z DTO
        HabitEntity habit = habitMapper.createToEntity(habitCreateDTO);

        //Nastavení základních hodnot
        habit.setUser(user);
        habit.setStartDate(LocalDate.now());
        habit.setXp(0);
        habit.setActive(true);
        habit.setStreak(0);
        habit.setBestStreak(0);
        habit.setLevel(1);
        habit.setCompletions(0);

        //Uložení do databáze pomocí repository
        habitRepository.save(habit);

        //Vrátí odpověď DTO
        return habitMapper.habitResponseToDto(habit);
    }

    //Metoda na výpis všech habits konkrétního uživatele
    public List<HabitUserDTO> get() {
        //Najde uživatele pomocí uživatelského jména z JWT
        UserEntity user = fetchUserByUsername();

        //Vrátí DTO seznam
        return habitMapper.userHabitsToDto(user.getHabits());
    }

    //Metoda na aktualizaci habit. Přijímá informace DTO o habit z webu a id konkrétního habit k úpravě
    public HabitCreateDTO update(HabitCreateDTO habitCreateDTO, Long id) {
        //Najde uživatele pomocí uživatelského jména z JWT
        UserEntity user = fetchUserByUsername();

        //Najde habit podle id a podle uživatele
        HabitEntity habit = fetchHabitByIdAndUsername(id, user);

        //Aktualizované hodnoty
        habit.setName(habitCreateDTO.getName());
        habit.setDescription(habitCreateDTO.getDescription());
        habit.setFrequency(habitCreateDTO.getFrequency());

        //Uložení do databáze pomocí repository
        habitRepository.save(habit);

        //Vrátí aktualizované DTO
        return habitMapper.updateHabitToDto(habit);
    }

    //Metoda na označení habit jako hotový. Přijímá id habitu. Obsahuje logiku levelování. Vrací aktuaizovaný habit
    public HabitUserDTO complete(Long id) {
        //Najde uživatele pomocí uživatelského jména z JWT
        UserEntity user = fetchUserByUsername();
        //Najde habit podle id a podle uživatele
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

        //Uložení aktualizovaných hodnot do databáze pomocí repository
        habitRepository.save(habit);
        userRepository.save(user);

        return habitMapper.habitToDto(habit);
    }

    //Metoda pro odstranění habit podle id. Vrací habit, který byl odstraněn
    public HabitResponseDTO delete(Long id) {
        //Najde uživatele
        UserEntity user = fetchUserByUsername();

        //Najde návyk podle id a uživatele
        HabitEntity habit = fetchHabitByIdAndUsername(id, user);

        //Odstranění z databáze pomocí repository
        habitRepository.delete(habit);

        //Vrátí co bylo odstraněno
        return habitMapper.habitResponseToDto(habit);
    }

    //Metoda pro nalezení uživatele z JWT
    private UserEntity fetchUserByUsername() {
        return userRepository.findByUsername(SecurityContextHolder.getContext()
                        .getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));
    }

    //Metoda pro nalzení habit podle id a uivatele
    private HabitEntity fetchHabitByIdAndUsername(Long id, UserEntity user) {
        return habitRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new IllegalArgumentException("Návyk nenalezen nebo nepatří tomuto uživateli"));
    }
}
