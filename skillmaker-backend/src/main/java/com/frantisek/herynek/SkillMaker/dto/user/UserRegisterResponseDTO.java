package com.frantisek.herynek.SkillMaker.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDTO {

    private Long id;

    private String username;
}
