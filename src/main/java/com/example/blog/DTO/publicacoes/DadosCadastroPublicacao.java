package com.example.blog.DTO.publicacoes;

import com.example.blog.domain.assinante.Assinante;
import com.example.blog.domain.postagens.Descricao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroPublicacao(@NotNull Descricao descricao, @NotBlank String titulo,@NotBlank String texto, LocalDate dataCadastro) {
}
