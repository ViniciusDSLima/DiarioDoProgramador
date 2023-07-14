package com.example.blog.domain.postagens;

import com.example.blog.DTO.publicacoes.DadosAtualizarPublicacao;
import com.example.blog.DTO.publicacoes.DadosCadastroPublicacao;
import com.example.blog.domain.assinante.Assinante;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "publicacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Descricao descricao;
    private String texto;
    @OneToOne
    private Assinante assinante;
    private LocalDate dataCadastro;

    public Publicacao(DadosCadastroPublicacao dadosCadastroPublicacao) {
        this.descricao = dadosCadastroPublicacao.descricao();
        this.texto = dadosCadastroPublicacao.texto();
        this.dataCadastro = LocalDate.now();
        this.titulo = dadosCadastroPublicacao.titulo();
    }

    public void atualizarInformacoes(DadosAtualizarPublicacao dadosAtualizarPublicacao) {
        if(dadosAtualizarPublicacao.texto() != null){
            this.texto = dadosAtualizarPublicacao.texto();
        }
        if(dadosAtualizarPublicacao.descricao() != null){
            this.descricao = dadosAtualizarPublicacao.descricao();
        }
        if(dadosAtualizarPublicacao.titulo() != null){
            this.titulo = dadosAtualizarPublicacao.titulo();
        }
        this.dataCadastro = LocalDate.now();
    }
}
