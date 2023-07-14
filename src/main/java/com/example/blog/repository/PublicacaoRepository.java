package com.example.blog.repository;

import com.example.blog.domain.postagens.Descricao;
import com.example.blog.domain.postagens.Publicacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

    Page<Publicacao> findAll(Pageable pageable);

    Page<Publicacao> findAllByDescricao(Descricao descricao, Pageable pageable);
}
