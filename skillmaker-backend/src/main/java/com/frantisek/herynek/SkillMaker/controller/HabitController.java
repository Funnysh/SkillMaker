package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.habit.HabitCreateDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.habit.HabitUserDTO;
import com.frantisek.herynek.SkillMaker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Rest controller pro návyky (habits).

@RestController //Anotace na kontroler
@RequestMapping("/api/habit") //API předmapování
public class HabitController {

    @Autowired //Anotace na DI, umožní práci s třídou v této třídě
    private HabitService habitService;

    //POST metoda na vytvoření návyku, vytváří, proto POST. Potřebuje obsah stránky (@RequestBody)
    @PostMapping("/create")
    public HabitResponseDTO create (@RequestBody HabitCreateDTO habitCreateDTO) {
        return habitService.create(habitCreateDTO);
    }

    //GET metoda na získání Listu (všech) návyků. GET, protože vrací
    @GetMapping("/get")
    public List<HabitUserDTO> get(){
        return habitService.get();
    }

    //PUT metoda pro aktualizaci návyku. PUT protože aktualizuje již vytvořený. Potřebuje obsah stránky (@RequestBody) a potřebuje proměnou ({id}) z cesty, která se volá (@PathVariable)
    @PutMapping("/update/{id}")
    public HabitCreateDTO update(@RequestBody HabitCreateDTO habitCreateDTO, @PathVariable Long id) {
        return habitService.update(habitCreateDTO, id);
    }

    //POST metoda pro označení návyku za splněný. POST, protože provádí změny v databázi. Potřebuje proměnou {id} z cesty
    @PostMapping("/complete/{id}")
    public HabitUserDTO complete(@PathVariable Long id) {
        return habitService.complete(id);
    }

    //DELETE metoda pro smazání návyku. Potřebuje proměnou z cesty
    @DeleteMapping("/delete/{id}")
    public HabitResponseDTO delete(@PathVariable Long id) {
        return habitService.delete(id);
    }
}
