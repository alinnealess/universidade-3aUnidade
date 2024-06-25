package com.imd.universidade.repository;

import com.imd.universidade.model.TurmaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<TurmaEntity, Long> {
}
