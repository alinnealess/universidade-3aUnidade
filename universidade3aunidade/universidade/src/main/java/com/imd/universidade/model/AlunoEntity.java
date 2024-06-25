package com.imd.universidade.model;

import com.imd.universidade.DTO.AlunoDTO;
import com.imd.universidade.enums.Genero;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "alunos")
@Data
@AllArgsConstructor
public class AlunoEntity {

    public AlunoEntity() {
        this.ativo = true;
    }


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String curso;
    private String dataNascimento;
    private boolean ativo;


    public void putMetodo(AlunoDTO aluno) {
        if (aluno.nome() != null){
            this.nome = aluno.nome();
        }
        if (aluno.cpf() != null){
            this.cpf = aluno.cpf();
        }
        if (aluno.dataNascimento() != null){
            this.dataNascimento= aluno.dataNascimento();
        }
        if (aluno.curso() != null){
            this.curso= aluno.curso();
        }
        if (aluno.genero() != null){
            this.genero = aluno.genero();
        }
    }
    public void inativarAluno() {
        this.ativo = false;
    }
}

