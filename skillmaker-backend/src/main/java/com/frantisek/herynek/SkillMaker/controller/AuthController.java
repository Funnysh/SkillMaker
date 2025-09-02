package com.frantisek.herynek.SkillMaker.controller;

import com.frantisek.herynek.SkillMaker.dto.user.UserLoginDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserLoginResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserRegistrationDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserRegisterResponseDTO;
import com.frantisek.herynek.SkillMaker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest controller pro autentikaci (login + registrace)

@RestController //Anotace, označuje rest controller
@RequestMapping("/api/auth") //api předmapování
public class AuthController {

    @Autowired //Anotace, DI, možné pracovat s touto třídou zde
    private AuthService authService;

    //POST na .../register. Vytvoří uživatele. Potřebuje ověřená (@Valid) data a obsah stránky (@RequestBody)
    @PostMapping("/register")
    public UserRegisterResponseDTO register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return authService.registerUser(userRegistrationDTO);
    }

    //Post na .../login. Přihlásí uživatele a uloží JWT token, proto POST. Potřebuje obsahy stránky (@RequestBody)
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.loginUser(userLoginDTO);
    }
}
