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

//Service pro autorizaci uživatele. Register, login

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository; //Připojení přístupu k databázi

    @Autowired
    private UserMapper userMapper; //Připojení mapperu

    @Autowired
    private PasswordEncoder passwordEncoder; //Připojení šifrování hesla

    @Autowired
    private JwtUtil jwtUtil; //Připojení generace tokenů

    //Metoda na registraci uživatele. Přijímá DTO informace z webu, vrací DTO odpověĎ
    public UserRegisterResponseDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        //Pokud uživatelské jméno již existuje, vyhoď výjimku
        if (userRepository.existsByUsername(userRegistrationDTO.getUsername())) {
            throw new UsernameAlreadyExistsException("Toto uživatelské jméno již existuje");
        }

        //Mapper převede DTO na entity
        UserEntity user = userMapper.registerToEntity(userRegistrationDTO);

        //Do entity se uloží heslo, které se zašifruje
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        //Nastaví se role
        user.setRole(Role.USER);

        //Nastaví se level
        user.setLevel(1);

        //Nastaví se xp
        user.setXp(0);

        //Nastaví se všechny xp
        user.setTotalXp(0);

        //Uložení entity do databáze pomocí repository
        userRepository.save(user);

        //Vrází odpověď
        return userMapper.responseToDTO(user);
    }

    //Metoda na login uživatele. Přijímá DTO informace z webu, vrací DTO odpověď s JWT
    public UserLoginResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        //Najde uživatele v databázi podle uživatelského jména, pokud není, vyhodí výjimku
        UserEntity user = userRepository.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen: " + userLoginDTO.getUsername()));

        //Pokud rozšifrované heslo nesedí s heslem uloženým v databázi, vyhodí výjimku
        if(!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchingException("Špatné heslo");
        }

        //Vrátí odpověď, která obsahuje JWT
        return new UserLoginResponseDTO(jwtUtil.generateToken(user.getUsername(), user.getRole().name()), user.getUsername());
    }
}
