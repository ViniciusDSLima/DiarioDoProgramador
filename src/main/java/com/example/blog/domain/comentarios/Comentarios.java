package com.example.blog.domain.comentarios;

import com.example.blog.DTO.comentarios.DadosAtualizarComentario;
import com.example.blog.DTO.comentarios.DadosCadastroComentario;
import com.example.blog.domain.assinante.Assinante;
import com.example.blog.domain.postagens.Descricao;
import com.example.blog.domain.postagens.Publicacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    @ManyToOne
    private Assinante assinante;
    @ManyToMany
    private List<Publicacao> publicacoes;

    private LocalDate dataComentario;
    @Enumerated(EnumType.STRING)
    private Descricao descricao;

    public Comentarios(DadosCadastroComentario dadosCadastroComentario) {
        this.texto = dadosCadastroComentario.texto();
        this.assinante = dadosCadastroComentario.assinante();
        this.dataComentario = LocalDate.now();

    }

    public void atualizarInformacoes(DadosAtualizarComentario dadosAtualizarComentario) {
        if(dadosAtualizarComentario.texto() != null){
            this.texto = dadosAtualizarComentario.texto();
            this.dataComentario = dadosAtualizarComentario.dataAtualizacao();
        }
    }
}
