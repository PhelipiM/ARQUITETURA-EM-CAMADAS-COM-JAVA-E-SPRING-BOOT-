package br.com.sistemas.chamados.controller;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sistemas.chamados.dto.AlterarStatusRequest;
import br.com.sistemas.chamados.dto.AtribuirTecnicoRequest;
import br.com.sistemas.chamados.dto.ChamadoRequest;
import br.com.sistemas.chamados.dto.ChamadoResponse;
import br.com.sistemas.chamados.dto.ComentarioRequest;
import br.com.sistemas.chamados.dto.ComentarioResponse;
import br.com.sistemas.chamados.entity.Prioridade;
import br.com.sistemas.chamados.entity.StatusChamado;
import br.com.sistemas.chamados.service.ChamadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService service;

    public ChamadoController(ChamadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ChamadoResponse> criar(@Valid @RequestBody ChamadoRequest dto) {
        ChamadoResponse criado = service.criar(dto);
        return ResponseEntity.created(URI.create("/chamados/" + criado.id())).body(criado);
    }

    @GetMapping
    public Page<ChamadoResponse> listar(
            @RequestParam(required = false) StatusChamado status,
            @RequestParam(required = false) Prioridade prioridade,
            @RequestParam(required = false) Long clienteId,
            Pageable pageable) {
        return service.listarComFiltros(status, prioridade, clienteId, pageable);
    }

    @GetMapping("/{id}")
    public ChamadoResponse buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public ChamadoResponse atualizar(@PathVariable Long id, @Valid @RequestBody ChamadoRequest dto) {
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}/atribuir")
    public ChamadoResponse atribuirTecnico(@PathVariable Long id, @Valid @RequestBody AtribuirTecnicoRequest dto) {
        return service.atribuirTecnico(id, dto);
    }

    @PatchMapping("/{id}/status")
    public ChamadoResponse alterarStatus(@PathVariable Long id, @Valid @RequestBody AlterarStatusRequest dto) {
        return service.alterarStatus(id, dto);
    }

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<ComentarioResponse> adicionarComentario(
            @PathVariable Long id,
            @Valid @RequestBody ComentarioRequest dto) {
        ComentarioResponse response = service.adicionarComentario(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/comentarios")
    public ResponseEntity<List<ComentarioResponse>> listarComentarios(@PathVariable Long id) {
        List<ComentarioResponse> comentarios = service.listarComentariosDoChamado(id);
        return ResponseEntity.ok(comentarios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}