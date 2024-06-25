package com.imd.universidade.DTO;

import com.imd.universidade.enums.Genero;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record ProfessorDTO(
        Long id,
        @NotBlank(message = "Campo não pode ser vazio!")
        String nome,
        //@CPF(message = "CPF invalido")
        String cpf,
        @NotNull(message = "A matrícula não pode ser nula.")
        int matricula,
        @NotBlank(message = "O departamento não pode estar em branco.")
        String departamento,

        @NotNull(message = "A data de nascimento não pode ser nula.")
        String dataNascimento,
        @Enumerated(EnumType.STRING)
        Genero genero,
        @NotNull(message = "O salário não pode ser nulo.")
        float salario,
        @NotBlank(message = "A disciplina associada não pode estar em branco.")
        String disciplinaAssociada
        ) {


}