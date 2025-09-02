package com.frantisek.herynek.SkillMaker.service;

import com.frantisek.herynek.SkillMaker.dto.user.UserDTO;
import com.frantisek.herynek.SkillMaker.dto.user.UserUpdateDTO;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import com.frantisek.herynek.SkillMaker.mapper.UserMapper;
import com.frantisek.herynek.SkillMaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//Service na práci s uživatelem

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    //Metoda, která vrací uživatele
    public UserDTO getMe() {
        //Získání uživatelského jména z JWT
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //Najde uživatele v databázi, pokud nenajde, vyhodí výjimku
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));

        //Vrátí DTO uživatele
        return userMapper.userToDTO(user);
    }

    //Metoda na aktualizaci dat uživatele
    @Transactional //Anotace pro to, aby se zajistilo, že se všechny úpravy provednou. Pokud nějaká selže, automaticky se vrátí do původního stavu
    public UserDTO updateMe(UserUpdateDTO userUpdateDTO) {

        //Získání username z JWT
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        //Najde uživatele pomocí username
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));

        //Pokud nové uživateleské jméno je již v databázi, vyhodí výjimku
        if (!user.getUsername().equalsIgnoreCase(userUpdateDTO.getUsername()) &&
                userRepository.existsByUsernameIgnoreCase(userUpdateDTO.getUsername())) {
            throw new IllegalArgumentException("Uživatelské jméno je již obsazené");
        }

        //Nastaví nové hodnoty a ořízne bílá místa
        user.setUsername(userUpdateDTO.getUsername().trim());
        user.setEmail(userUpdateDTO.getEmail().trim());
        user.setName(userUpdateDTO.getName().trim());
        user.setSurname(userUpdateDTO.getSurname().trim());
        user.setAbout(userUpdateDTO.getAbout().trim());

        //Uložení pomocí repository do databáze
        userRepository.save(user);

        //Vrátí aktualizované DTO
        return userMapper.userToDTO(user);
    }
}
