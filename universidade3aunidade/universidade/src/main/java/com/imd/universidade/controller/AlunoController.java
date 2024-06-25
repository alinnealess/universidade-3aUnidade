package com.imd.universidade.controller;

import com.imd.universidade.DTO.AlunoDTO;
import com.imd.universidade.model.AlunoEntity;
import com.imd.universidade.model.ProfessorEntity;
import com.imd.universidade.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    public ResponseEntity<AlunoEntity> postAluno(@Valid @RequestBody AlunoDTO aluno) {
        AlunoEntity alunoEntity = new AlunoEntity();
        BeanUtils.copyProperties(aluno, alunoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(alunoEntity));
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id) {
        Optional<AlunoEntity> alunoEntity = repository.findById(id);
        if (alunoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(alunoEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putOne(@PathVariable(value = "id") Long id,
                                         @RequestBody AlunoDTO aluno) {
        Optional<AlunoEntity> existingAluno = repository.findById(id);
        if (existingAluno.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não localizado ou inativo");
        }
        AlunoEntity alunoModel = existingAluno.get();
        alunoModel.putMetodo(aluno);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(alunoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable Long id) {
        Optional<AlunoEntity> alunoEntity = repository.findById(id);
        if (alunoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não localizado");
        }
        AlunoEntity alunoModel = alunoEntity.get();
        repository.delete(alunoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }

    @DeleteMapping("inativar/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable(value = "id") Long id){
        Optional<AlunoEntity> alunoEntity = repository.findById(id);

        if(alunoEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não localizado");
        }
        AlunoEntity alunoModel = alunoEntity.get();

        alunoModel.inativarAluno();

        repository.save(alunoModel);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno deletado com sucesso!");
    }


}