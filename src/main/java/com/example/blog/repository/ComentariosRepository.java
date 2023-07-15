package com.example.blog.repository;

import com.example.blog.domain.comentarios.Comentarios;
import com.example.blog.domain.postagens.Descricao;
import org.hibernate.usertype.UserTypeLegacyBridge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {
    @Query("select new com.example.blog.DTO.comentarios.ComentarioDTO(comentarios.texto, comentarios.dataComentario) from Comentarios comentarios")
    Page<Comentarios> buscarTodos(Pageable pageable);

    Page<Comentarios> findByDescricao(Descricao descricao);
}
