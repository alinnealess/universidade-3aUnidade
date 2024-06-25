package com.imd.universidade.DTO;

import com.imd.universidade.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}