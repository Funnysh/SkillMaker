package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.user.UserDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserUpdateDTO;
import com.frantisek.herynek.SkillMaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Rest controller pro uživatele

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired //DI
    private UserService userService;

    //GET metoda pro získání uživatele
    @GetMapping("/me")
    public UserDTO getMe() {
        return userService.getMe();
    }

    //PUT metoda pro aktualizace stávajícího uživatele
    @PutMapping("/me")
    public UserDTO updateMe(@RequestBody UserUpdateDTO userUpdateDTO) {
        return userService.updateMe(userUpdateDTO);
    }
}
