package com.imd.universidade.controller;

import com.imd.universidade.DTO.TurmaDTO;
import com.imd.universidade.model.TurmaEntity;
import com.imd.universidade.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")

public class TurmaController {
    @Autowired
    private TurmaService turmaService;

    @GetMapping
    public ResponseEntity<List<TurmaEntity>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(turmaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return turmaService.getById(id);
    }

    @PostMapping
    public ResponseEntity<TurmaEntity> postTurma(@Valid @RequestBody TurmaDTO turma) {
        return ResponseEntity.status(HttpStatus.CREATED).body(turmaService.save(turma));
    }

    @PutMapping
    public ResponseEntity<TurmaEntity> putTurma(@Valid @RequestBody TurmaDTO turma) {
        return turmaService.update(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTurma(@PathVariable Long id) {
        return turmaService.delete(id);
    }

    @DeleteMapping("/inativar/{id}")
    public ResponseEntity<Object> deleteLogic(@PathVariable Long id) {
        return turmaService.deleteLogic(id);
    }

    @PostMapping("/matricular")
    public ResponseEntity<Object> matricularAluno(@RequestParam Long turmaId, @RequestParam Long alunoId) {
        return turmaService.matricularAluno(turmaId, alunoId);
    }

    @PostMapping("/desmatricular")
    public ResponseEntity<Object> removerAluno(@RequestParam Long turmaId, @RequestParam Long alunoId) {
        return turmaService.removerAluno(turmaId, alunoId);
    }

    @PostMapping("/associarProfessor")
    public ResponseEntity<Object> associarProfessor(@RequestParam Long turmaId, @RequestParam Long professorId) {
        return turmaService.associarProfessor(turmaId, professorId);
    }

    @PostMapping("/desassociarProfessor")
    public ResponseEntity<Object> desassociarProfessor(@RequestParam Long turmaId, @RequestParam Long professorId) {
        return turmaService.desassociarProfessor(turmaId, professorId);
    }
}
