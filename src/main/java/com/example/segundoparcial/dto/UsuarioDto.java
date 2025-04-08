package com.example.segundoparcial.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDto {
    @Schema(description = "ID autogenerado", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(example = "Juan Pérez")
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Schema(example = "juan.perez@example.com")
    private String correo;
}
