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
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer telefonoId;
    private String tipo;
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaId", referencedColumnName = "personaId")
    private Persona persona;
}
