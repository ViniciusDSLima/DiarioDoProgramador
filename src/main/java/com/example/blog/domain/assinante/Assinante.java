package com.example.blog.domain.assinante;

import com.example.blog.DTO.assinantes.DadosAtualizarAssinante;
import com.example.blog.DTO.assinantes.DadosCadastroAssinante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Assinantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assinante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    public Assinante(DadosCadastroAssinante dadosCadastroAssinante) {
        this.nome = dadosCadastroAssinante.nome();
        this.email = dadosCadastroAssinante.email();
        this.senha = dadosCadastroAssinante.senha();
        this.nivel = dadosCadastroAssinante.nivel();
    }

    public void atualizarInformacoes(DadosAtualizarAssinante dadosAtualizarAssinante) {
        if(dadosAtualizarAssinante.email() != null){
            this.email = dadosAtualizarAssinante.email();
        }
        if(dadosAtualizarAssinante.senha() != null){
            this.senha = dadosAtualizarAssinante.senha();
        }
        if(dadosAtualizarAssinante.nome() != null){
            this.nome = dadosAtualizarAssinante.nome();
        }
    }

}
