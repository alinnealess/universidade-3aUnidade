package com.imd.universidade.repository;

import com.imd.universidade.model.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {

    List<ProfessorEntity> findAllByAtivoTrue();
}