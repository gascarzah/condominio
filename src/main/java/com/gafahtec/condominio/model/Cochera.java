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
public class Cochera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocheraId;
    private boolean nivel;
    private boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaId", referencedColumnName = "personaId")
    private Persona persona;
}
