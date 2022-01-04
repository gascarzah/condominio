package com.gafahtec.condominio.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartamento;

    private Integer numero;
    private Integer flat;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_torre", nullable = false, foreignKey = @ForeignKey(name = "FK_torre"))
    private Torre torre;
}
