package com.frantisek.herynek.SkillMaker.dto.user;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitUserDTO;
import com.frantisek.herynek.SkillMaker.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String email;

    private Role role;

    private LocalDateTime createdAt;

    private String name;

    private String surname;

    private String about;

    private int level;

    private int xp;

    private int totalXp;

    private List<HabitUserDTO> habits;
}
