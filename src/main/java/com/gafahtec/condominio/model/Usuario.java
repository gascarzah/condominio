package com.gafahtec.condominio.model;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer usuarioId;
    @NotBlank(message = "Campo Login  es requerido")
    private String login;
    @NotBlank(message = "Campo password es requerido")
    private String password;


    @Email
    @NotEmpty(message = "Email es requerido")
    private String email;

    private Instant fechaCreacion;
    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaId", referencedColumnName = "personaId")
    private Persona persona;
}
