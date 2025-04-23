package com.sus.triagem.domain.repository;

import com.sus.triagem.domain.model.Triagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TriagemRepository extends JpaRepository<Triagem, Long> {
    List<Triagem> findAllByOrderByPrioridadeAscDataHoraAsc();
}
