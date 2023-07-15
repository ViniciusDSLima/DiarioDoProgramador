package com.example.blog.DTO.comentarios;

import com.example.blog.DTO.assinantes.AssinanteDTO;
import com.example.blog.domain.comentarios.Comentarios;
import com.example.blog.domain.postagens.Descricao;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ComentarioDTO(String texto, Descricao descricao, AssinanteDTO assinanteDTO, LocalDate dataComentario) {
    public ComentarioDTO(Comentarios comentario) {
        this(comentario.getTexto(),comentario.getDescricao(), new AssinanteDTO(comentario.getAssinante()), comentario.getDataComentario());
    }

}
