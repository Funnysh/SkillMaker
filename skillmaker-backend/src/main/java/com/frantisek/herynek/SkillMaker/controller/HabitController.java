package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitUserDTO;
import com.frantisek.herynek.SkillMaker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habit")
public class HabitController {

    @Autowired
    private HabitService habitService;

    @PostMapping("/create")
    public HabitResponseDTO create (@RequestBody HabitCreateDTO habitCreateDTO) {
        return habitService.create(habitCreateDTO);
    }

    @GetMapping("/get")
    public List<HabitUserDTO> get(){
        return habitService.get();
    }

    @PutMapping("/update/{id}")
    public HabitCreateDTO update(@RequestBody HabitCreateDTO habitCreateDTO, @PathVariable Long id) {
        return habitService.update(habitCreateDTO, id);
    }

    @PostMapping("/complete/{id}")
    public HabitUserDTO complete(@PathVariable Long id) {
        return habitService.complete(id);
    }

    @DeleteMapping("/delete/{id}")
    public HabitResponseDTO delete(@PathVariable Long id) {
        return habitService.delete(id);
    }
}
