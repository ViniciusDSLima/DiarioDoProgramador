package com.example.blog.DTO.publicacoes;

import com.example.blog.domain.postagens.Descricao;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarPublicacao(@NotNull Long id,String titulo, String texto, Descricao descricao) {
}
