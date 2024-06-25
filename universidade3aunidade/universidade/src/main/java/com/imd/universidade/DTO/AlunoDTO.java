package com.imd.universidade.DTO;

import com.imd.universidade.enums.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AlunoDTO(
        Long id,
        @NotBlank(message = "Campo não pode ser vazio!")
        String nome,
        String cpf,
        @NotNull(message = "A matrícula não pode ser nula.")
        int matricula,
        @Enumerated(EnumType.STRING)
        Genero genero,
        @NotBlank(message = "O curso não pode estar em branco.")
        String curso,
        @NotBlank(message = "A data de nascimento não pode estar em branco.")
        String dataNascimento) {
}