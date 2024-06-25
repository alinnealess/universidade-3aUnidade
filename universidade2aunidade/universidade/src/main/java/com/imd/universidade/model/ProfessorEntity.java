package com.imd.universidade.model;

import com.imd.universidade.enums.Genero;
import jakarta.persistence.*;
import com.imd.universidade.DTO.ProfessorDTO;
import lombok.*;




@Entity
@Table(name = "professor")
@Data
@AllArgsConstructor
public class ProfessorEntity {

    public ProfessorEntity(){
        this.ativo = true;
    }


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private int matricula;
    private Genero genero;
    private String departamento;
    private String dataNascimento;
    private float salario;
    private String disciplinaAssociada;
    private boolean ativo;

    public void putMetodo(ProfessorDTO professor) {
        if (professor.nome() != null){
            this.nome = professor.nome();
        }
        if (professor.cpf() != null){
            this.cpf = professor.cpf();
        }
        if (professor.dataNascimento() != null){
           this.dataNascimento= professor.dataNascimento();
        }
        if (professor.disciplinaAssociada() != null){
            this.disciplinaAssociada= professor.disciplinaAssociada();
        }
        if (professor.departamento() != null){
            this.departamento = professor.departamento();
        }
        if (professor.genero() != null){
            this.genero = professor.genero();
        }

    }

    public void inativarProfessor() {
        this.ativo = false;
    }




}

