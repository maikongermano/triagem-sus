package com.sus.triagem.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Triagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToMany
    @JoinTable(
        name = "triagem_sintomas",
        joinColumns = @JoinColumn(name = "triagem_id"),
        inverseJoinColumns = @JoinColumn(name = "sintoma_id")
    )
    private List<Sintoma> sintomas;

    private String prioridade;
    private LocalDateTime dataHora;
}
