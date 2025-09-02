package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.skill.SkillCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.skill.SkillUserDTO;
import com.frantisek.herynek.SkillMaker.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Rest controller pro skilly

@RestController
@RequestMapping("/api/skill") //Předdefinovaná cesta
public class SkillController {

    @Autowired //DI
    private SkillService skillService;

    //POST metoda pro vytvoření skillu. Potřebuje obsah stránky (@RequestBody)
    @PostMapping("/create")
    public SkillResponseDTO create(@RequestBody SkillCreateDTO skillCreateDTO) {
        return skillService.create(skillCreateDTO);
    }

    //GET metoda pro získání všech skillů
    @GetMapping("/get")
    public List<SkillUserDTO> get() {
        return skillService.get();
    }

    //PUT metoda pro aktualizaci stávajícího skillu. Potřebuje obsah stránky (@RequestBody) a proměnou z cesty (@PathVariable)
    @PutMapping("/update/{id}")
    public SkillCreateDTO update(@RequestBody SkillCreateDTO skillCreateDTO, @PathVariable Long id) {
        return skillService.update(skillCreateDTO, id);
    }
}
