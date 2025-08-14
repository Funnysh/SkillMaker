package com.frantisek.herynek.SkillMaker.service;

import com.frantisek.herynek.SkillMaker.dto.user.UserLoginDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserLoginResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserRegisterResponseDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserRegistrationDTO;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import com.frantisek.herynek.SkillMaker.enums.Role;
import com.frantisek.herynek.SkillMaker.exceptions.PasswordNotMatchingException;
import com.frantisek.herynek.SkillMaker.exceptions.UsernameAlreadyExistsException;
import com.frantisek.herynek.SkillMaker.mapper.UserMapper;
import com.frantisek.herynek.SkillMaker.repository.UserRepository;
import com.frantisek.herynek.SkillMaker.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public UserRegisterResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Toto uživatelské jméno již existuje");
        }

        UserEntity user = userMapper.registerToEntity(userRegistrationDTO);

        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        user.setRole(Role.USER);

        user.setLevel(1);

        user.setXp(0);

        user.setTotalXp(0);

        userRepository.save(user);

        return userMapper.responseToDTO(user);
    }

    public UserLoginResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        UserEntity user = userRepository.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen: " + userLoginDTO.getUsername()));

        if(!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchingException("Špatné heslo");
        }

        return new UserLoginResponseDTO(jwtUtil.generateToken(user.getUsername(), user.getRole().name()), user.getUsername());
    }
}
