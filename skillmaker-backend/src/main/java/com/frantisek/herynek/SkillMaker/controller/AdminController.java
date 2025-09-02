package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.user.UserDTO;
import com.frantisek.herynek.SkillMaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest controller pro admina

@RestController //Anotace, označuje komponentu controller
@RequestMapping("/api/admin") //Anotace pro Spring, označuje předdefinovanou cestu
public class AdminController {

    @Autowired //Anotace, DI (dependency injekce). Připojení userService pro možnost pracovat s tím v této třídě
    private UserService userService;

    //GET metoda na api .../me. Vrátí profil admina
    @GetMapping("/me")
    public UserDTO getMe() {
        return userService.getMe();
    }
}
