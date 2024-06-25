package com.imd.universidade.repository;

import com.imd.universidade.model.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {

    List<AlunoEntity> findAllByAtivoTrue();
}