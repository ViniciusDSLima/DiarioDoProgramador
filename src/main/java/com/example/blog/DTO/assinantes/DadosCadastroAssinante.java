package com.example.blog.DTO.assinantes;

import com.example.blog.domain.assinante.Nivel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAssinante(@NotBlank String nome,@NotBlank String email,@NotBlank String senha,@NotNull Nivel nivel) {

}
