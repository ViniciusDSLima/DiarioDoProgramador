package com.example.blog.DTO.comentarios;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosAtualizarComentario(@NotNull Long id, String texto, LocalDate dataAtualizacao) {
}
