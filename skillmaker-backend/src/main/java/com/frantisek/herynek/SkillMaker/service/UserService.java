package com.frantisek.herynek.SkillMaker.service;

import com.frantisek.herynek.SkillMaker.dto.user.UserDTO;
import com.frantisek.herynek.SkillMaker.entity.UserEntity;
import com.frantisek.herynek.SkillMaker.mapper.UserMapper;
import com.frantisek.herynek.SkillMaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
