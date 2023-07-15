package com.example.blog.controller;

import com.example.blog.DTO.assinantes.AssinanteDTO;
import com.example.blog.DTO.assinantes.DadosAtualizarAssinante;
import com.example.blog.DTO.assinantes.DadosCadastroAssinante;
import com.example.blog.domain.assinante.Assinante;
import com.example.blog.repository.AssinanteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("assinantes")
public class AssinanteController {
    @Autowired
    private AssinanteRepository repository;

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarAssinante(@RequestBody @Valid DadosCadastroAssinante dadosCadastroAssinante, UriComponentsBuilder uriComponentsBuilder){
        var assinante = repository.save(new Assinante(dadosCadastroAssinante));

        var uri = uriComponentsBuilder.path("assinantes/cadastro/{id}").buildAndExpand(assinante).toUri();

        return ResponseEntity.created(uri).body(new AssinanteDTO(assinante));
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity atualizarAssinante(@RequestBody @Valid DadosAtualizarAssinante dadosAtualizarAssinante){
        var assinante = repository.getReferenceById(dadosAtualizarAssinante.id());
        assinante.atualizarInformacoes(dadosAtualizarAssinante);

        return ResponseEntity.ok().body(new AssinanteDTO(assinante));
    }

    @GetMapping
    public ResponseEntity buscarAssinantes(@RequestParam("pagina") int pagina, @RequestParam("itens") int itens){
        var assinantes = repository.buscarTodos(PageRequest.of(pagina,itens)).getContent();

        return ResponseEntity.ok(assinantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarAssinantesById(@PathVariable Long id){
        var assinante = repository.getReferenceById(id);

        return ResponseEntity.ok().body(new AssinanteDTO(assinante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluirAssinante(@PathVariable Long id){
         repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
