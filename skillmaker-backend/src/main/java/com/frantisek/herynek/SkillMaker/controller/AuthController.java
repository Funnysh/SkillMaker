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

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserRegisterResponseDTO register(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        return authService.registerUser(userRegistrationDTO);
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return authService.loginUser(userLoginDTO);
    }
}
