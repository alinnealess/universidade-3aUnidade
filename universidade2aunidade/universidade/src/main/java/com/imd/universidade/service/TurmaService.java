package com.imd.universidade.service;


import com.imd.universidade.DTO.TurmaDTO;
import com.imd.universidade.model.AlunoEntity;
import com.imd.universidade.model.ProfessorEntity;
import com.imd.universidade.model.TurmaEntity;
import com.imd.universidade.repository.AlunoRepository;
import com.imd.universidade.repository.ProfessorRepository;
import com.imd.universidade.repository.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<TurmaEntity> getAll() {
        return turmaRepository.findAll();
    }

    public ResponseEntity<Object> getById(Long id) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(id);
        if (turmaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não localizada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(turmaEntity);
    }

    public TurmaEntity save(TurmaDTO turmaDTO) {
        TurmaEntity turmaEntity = new TurmaEntity();
        BeanUtils.copyProperties(turmaDTO, turmaEntity);
        return turmaRepository.save(turmaEntity);
    }

    public ResponseEntity<TurmaEntity> update(TurmaDTO turmaDTO) {
        Optional<TurmaEntity> existingTurma = turmaRepository.findById(turmaDTO.id());
        if (existingTurma.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        TurmaEntity turmaEntity = existingTurma.get();
        BeanUtils.copyProperties(turmaDTO, turmaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(turmaRepository.save(turmaEntity));
    }

    public ResponseEntity<Object> delete(Long id) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(id);
        if (turmaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não localizada");
        }
        turmaRepository.delete(turmaEntity.get());
        return ResponseEntity.status(HttpStatus.OK).body("Turma deletada com sucesso!");
    }

    public ResponseEntity<Object> deleteLogic(Long id) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(id);
        if (turmaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não localizada");
        }
        TurmaEntity turma = turmaEntity.get();
        turma.setAtivo(false);
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body("Turma inativada com sucesso!");
    }

    public ResponseEntity<Object> matricularAluno(Long turmaId, Long alunoId) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(turmaId);
        Optional<AlunoEntity> alunoEntity = alunoRepository.findById(alunoId);
        if (turmaEntity.isEmpty() || alunoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou Aluno não localizado");
        }
        TurmaEntity turma = turmaEntity.get();
        turma.getAlunos().add(alunoEntity.get());
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno matriculado com sucesso!");
    }

    public ResponseEntity<Object> removerAluno(Long turmaId, Long alunoId) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(turmaId);
        Optional<AlunoEntity> alunoEntity = alunoRepository.findById(alunoId);
        if (turmaEntity.isEmpty() || alunoEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou Aluno não localizado");
        }
        TurmaEntity turma = turmaEntity.get();
        turma.getAlunos().remove(alunoEntity.get());
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body("Aluno removido com sucesso!");
    }

    public ResponseEntity<Object> associarProfessor(Long turmaId, Long professorId) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(turmaId);
        Optional<ProfessorEntity> professorEntity = professorRepository.findById(professorId);
        if (turmaEntity.isEmpty() || professorEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma ou Professor não localizado");
        }
        TurmaEntity turma = turmaEntity.get();
        turma.setProfessorDisciplina(professorEntity.get());
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body("Professor associado com sucesso!");
    }

    public ResponseEntity<Object> desassociarProfessor(Long turmaId, Long professorId) {
        Optional<TurmaEntity> turmaEntity = turmaRepository.findById(turmaId);
        if (turmaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turma não localizada");
        }
        TurmaEntity turma = turmaEntity.get();
        turma.setProfessorDisciplina(null);
        turmaRepository.save(turma);
        return ResponseEntity.status(HttpStatus.OK).body("Professor desassociado com sucesso!");
    }

}
