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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO getMe() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));

        return userMapper.userToDTO(user);
    }

    @Transactional
    public UserDTO updateMe(UserUpdateDTO userUpdateDTO) {


        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel nenalezen"));

        if (!user.getUsername().equalsIgnoreCase(userUpdateDTO.getUsername()) &&
                userRepository.existsByUsernameIgnoreCase(userUpdateDTO.getUsername())) {
            throw new IllegalArgumentException("Uživatelské jméno je již obsazené");
        }

        user.setUsername(userUpdateDTO.getUsername().trim());
        user.setEmail(userUpdateDTO.getEmail().trim());
        user.setName(userUpdateDTO.getName().trim());
        user.setSurname(userUpdateDTO.getSurname().trim());
        user.setAbout(userUpdateDTO.getAbout().trim());

        userRepository.save(user);

        return userMapper.userToDTO(user);
    }
}
