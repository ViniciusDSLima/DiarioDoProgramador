package com.example.blog.controller;

import com.example.blog.DTO.comentarios.ComentarioDTO;
import com.example.blog.DTO.comentarios.DadosAtualizarComentario;
import com.example.blog.DTO.comentarios.DadosCadastroComentario;
import com.example.blog.DTO.publicacoes.PublicacaoDTO;
import com.example.blog.domain.comentarios.Comentarios;
import com.example.blog.domain.postagens.Descricao;
import com.example.blog.repository.ComentariosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comentarios")
public class ComentariosController {
    @Autowired
    private ComentariosRepository comentariosRepository;

    @PostMapping("/comentar")
    @Transactional
    public ResponseEntity realizarComentario(@RequestBody @Valid DadosCadastroComentario dadosCadastroComentario, UriComponentsBuilder uriComponentsBuilder){
        var comentario = comentariosRepository.save(new Comentarios(dadosCadastroComentario));

        var uri = uriComponentsBuilder.path("comentarios/comentar/{id}").buildAndExpand(comentario).toUri();

        return ResponseEntity.ok(new ComentarioDTO(comentario));
    }

    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity atualizarComentario(@RequestBody @Valid DadosAtualizarComentario dadosAtualizarComentario){
        var comentario = comentariosRepository.getReferenceById(dadosAtualizarComentario.id());
        comentario.atualizarInformacoes(dadosAtualizarComentario);

        return ResponseEntity.ok(new ComentarioDTO(comentario));
    }

    @GetMapping
    private ResponseEntity buscarComentarios(@RequestParam("pagina") int pagina, @RequestParam("itens") int itens){
        var comentarios = comentariosRepository.buscarTodos(PageRequest.of(pagina, itens)).getContent();

        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/{id}")
    private ResponseEntity buscarComentariosById(@PathVariable Long id){
        var comentario = comentariosRepository.getReferenceById(id);

        return ResponseEntity.ok().body(new ComentarioDTO(comentario));
    }

    @GetMapping("/{descricao}")
    public ResponseEntity buscarComentarioByDescricao(@PathVariable Descricao descricao){
        return ResponseEntity.ok(comentariosRepository.findByDescricao(descricao));
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluirComentario(@PathVariable Long id){
        comentariosRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
