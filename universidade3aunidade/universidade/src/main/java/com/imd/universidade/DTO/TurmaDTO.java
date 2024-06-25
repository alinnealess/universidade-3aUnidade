package com.imd.universidade.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record TurmaDTO(Long id,
                       @NotBlank(message = "Campo nome não pode ser vazio!")
                       String nome,
                       @NotBlank(message = "Campo código não pode ser vazio!")
                       String codigo,
                       List<Long> alunos,
                       Long professorDisciplina,
                       boolean ativo
)  {
}
