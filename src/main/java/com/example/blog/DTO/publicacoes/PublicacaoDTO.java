package com.example.blog.DTO.publicacoes;

import com.example.blog.domain.assinante.Assinante;
import com.example.blog.domain.postagens.Descricao;
import com.example.blog.domain.postagens.Publicacao;

import java.time.LocalDate;
import java.util.List;

public record PublicacaoDTO(Descricao descricao, String texto, Assinante assinante, LocalDate dataCadastro) {
    public PublicacaoDTO(Publicacao publicacao) {
        this(publicacao.getDescricao(), publicacao.getTexto(), publicacao.getAssinante(), publicacao.getDataCadastro());
    }
}
