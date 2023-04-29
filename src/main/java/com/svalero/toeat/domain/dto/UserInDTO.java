package com.svalero.toeat.domain.dto;

import com.svalero.toeat.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInDTO {
    private String username;
    private String email;
    private String password;
    private Role role = Role.USER;
}
