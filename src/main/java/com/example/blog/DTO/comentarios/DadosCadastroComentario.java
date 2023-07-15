package com.example.blog.DTO.comentarios;

import com.example.blog.DTO.publicacoes.PublicacaoDTO;
import com.example.blog.domain.assinante.Assinante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroComentario(@NotBlank String texto, @NotNull Assinante assinante, LocalDate dataComentario, PublicacaoDTO publicacaoDTO) {
}
