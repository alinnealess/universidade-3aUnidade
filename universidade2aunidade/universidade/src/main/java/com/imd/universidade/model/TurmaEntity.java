package com.imd.universidade.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "turmas")
@Data
@NoArgsConstructor

public class TurmaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String codigo;


    @ManyToMany
    @JoinTable(
            name = "turma_alunos",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<AlunoEntity> alunos;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professorDisciplina;

    private boolean ativo = true;
}
