package com.gafahtec.condominio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departamentoId;

    private Integer numero;
    private Integer flat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaId", referencedColumnName = "personaId")
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torreId", referencedColumnName = "torreId")
    private Torre torre;
}
