package com.imd.universidade.controller;

import com.imd.universidade.DTO.ProfessorDTO;
import com.imd.universidade.model.ProfessorEntity;
import com.imd.universidade.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @PostMapping
    public ResponseEntity<ProfessorEntity> postProfessor(@Valid  @RequestBody ProfessorDTO professor) {
        ProfessorEntity professorEntity = new ProfessorEntity();
        BeanUtils.copyProperties(professor, professorEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(professorEntity));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByAtivoTrue());
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id) {
        Optional<ProfessorEntity> professorEntity = repository.findById(id);
        if (professorEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(professorEntity);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> putOne(@Valid @PathVariable(value = "id") Long id,
                                          @RequestBody ProfessorDTO professor) {
        Optional<ProfessorEntity> existingProfessor = repository.findById(id);
        if (existingProfessor.isEmpty() ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não localizado ou inativo");
        }
        ProfessorEntity professorModel = existingProfessor.get();
        professorModel.putMetodo(professor);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(professorModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") Long id) {
        Optional<ProfessorEntity> professorEntity = repository.findById(id);
        if (professorEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não localizado");
        }
        ProfessorEntity professorModel = professorEntity.get();
        repository.delete(professorModel);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso!");
    }

    @DeleteMapping("inativar/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable(value = "id") Long id){
        Optional<ProfessorEntity> professorEntity = repository.findById(id);

        if(professorEntity.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Professor não localizado");
        }
        ProfessorEntity professorModel = professorEntity.get();

        professorModel.inativarProfessor();

        repository.save(professorModel);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deletado com sucesso!");
    }

}