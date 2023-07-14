package com.example.blog.DTO.assinantes;

import com.example.blog.domain.assinante.Assinante;
import com.example.blog.domain.assinante.Nivel;

public record AssinanteDTO(String nome,String email, Nivel nivel) {
    public AssinanteDTO(Assinante assinante){
        this(assinante.getNome(),assinante.getEmail(), assinante.getNivel());
    }
}
