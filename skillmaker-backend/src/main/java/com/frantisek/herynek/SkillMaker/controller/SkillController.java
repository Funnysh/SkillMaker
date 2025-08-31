package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillUserDTO;
import com.frantisek.herynek.SkillMaker.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @PostMapping("/create")
    public SkillResponseDTO create(@RequestBody SkillCreateDTO skillCreateDTO) {
        return skillService.create(skillCreateDTO);
    }

    @GetMapping("/get")
    public List<SkillUserDTO> get() {
        return skillService.get();
    }

    @PutMapping("/update/{id}")
    public SkillCreateDTO update(@RequestBody SkillCreateDTO skillCreateDTO, @PathVariable Long id) {
        return skillService.update(skillCreateDTO, id);
    }
}
