package com.example.blog.controller;

import com.example.blog.DTO.publicacoes.DadosAtualizarPublicacao;
import com.example.blog.DTO.publicacoes.DadosCadastroPublicacao;
import com.example.blog.DTO.publicacoes.PublicacaoDTO;
import com.example.blog.domain.postagens.Descricao;
import com.example.blog.domain.postagens.Publicacao;
import com.example.blog.repository.PublicacaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("publicacoes")
public class PublicacaoController {
    @Autowired
    private PublicacaoRepository repository;

    @PostMapping("/publicar")
    @Transactional
    public ResponseEntity publicar(@RequestBody @Valid DadosCadastroPublicacao dadosCadastroPublicacao, UriComponentsBuilder uriComponentsBuilder){
        var publicacao = repository.save(new Publicacao(dadosCadastroPublicacao));

        var uri = uriComponentsBuilder.path("publicacoes/publicar/{id}").buildAndExpand(publicacao).toUri();

        return ResponseEntity.created(uri).body(new PublicacaoDTO(publicacao));
    }
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity atualizarPublicacao(@RequestBody @Valid DadosAtualizarPublicacao dadosAtualizarPublicacao){
        var publicacao = repository.getReferenceById(dadosAtualizarPublicacao.id());
        publicacao.atualizarInformacoes(dadosAtualizarPublicacao);

        return ResponseEntity.ok().body(new PublicacaoDTO(publicacao));
    }

    @GetMapping
    public ResponseEntity buscarPublicacoes(@PageableDefault(sort = "{descricao}") @RequestParam("pagina") int pagina, @RequestParam("itens") int itens){
        var publicacoes = repository.findAll(PageRequest.of(pagina, itens)).getContent();

        return ResponseEntity.ok().body(publicacoes);
    }

    @GetMapping("/descricao")
    public ResponseEntity buscarPublicacoesByDescricao(@PageableDefault(sort = "{descricao}")@RequestParam("descicao") Descricao descricao, Pageable pageable){
        var publicacoes = repository.findAllByDescricao(descricao, pageable);

        return ResponseEntity.ok().body(publicacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarPublicaco(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
