package com.gafahtec.condominio.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personaId;
    @NotEmpty(message = "Campo nombres no puede estar vacio")
    private String nombres;
    @NotEmpty(message = "Campo apellido paterno no puede estar vacio")
    private String apellido_paterno;
    @NotEmpty(message = "Campo apellido materno no puede estar vacio")
    private String apellido_materno;
    @NotEmpty(message = "Campo dni no puede estar vacio")
    private String dni;
    

}
