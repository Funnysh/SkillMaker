package com.frantisek.herynek.SkillMaker.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    @NotBlank
    @Size(min = 3, max = 32)
    private String username;

    @Email
    @NotBlank
    private String email;

    private String name;

    private String surname;

    private String about;


}
