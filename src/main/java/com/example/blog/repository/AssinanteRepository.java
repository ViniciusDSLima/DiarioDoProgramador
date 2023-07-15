package com.example.blog.repository;

import com.example.blog.domain.assinante.Assinante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinanteRepository extends JpaRepository<Assinante, Long> {
    @Query("SELECT new com.example.blog.DTO.assinantes.AssinanteDTO(assinante.nome, assinante.email, assinante.nivel) FROM Assinante assinante")
    Page<Assinante> buscarTodos(Pageable pageable);


}
