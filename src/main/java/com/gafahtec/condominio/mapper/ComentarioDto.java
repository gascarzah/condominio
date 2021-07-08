package com.gafahtec.condominio.mapper;

import com.gafahtec.condominio.model.Comentario;
import jakarta.validation.constraints.NotBlank;

public record ComentarioDto(@NotBlank String titulo
                            ) {


}
