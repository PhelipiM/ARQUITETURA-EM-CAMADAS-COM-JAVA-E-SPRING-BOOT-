package br.com.sistemas.chamados.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.sistemas.chamados.dto.ChamadoRequest;
import br.com.sistemas.chamados.entity.Prioridade;
import br.com.sistemas.chamados.repository.CategoriaRepository;
import br.com.sistemas.chamados.repository.ClienteRepository;
import br.com.sistemas.chamados.repository.TecnicoRepository;
import br.com.sistemas.chamados.service.ChamadoService;

@Controller
@RequestMapping("/chamados")
public class ChamadoWebController {

    private final ChamadoService service;
    private final ClienteRepository clienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final TecnicoRepository tecnicoRepository;

    public ChamadoWebController(ChamadoService service, ClienteRepository clienteRepository, CategoriaRepository categoriaRepository, TecnicoRepository tecnicoRepository) {
        this.service = service;
        this.clienteRepository = clienteRepository;
        this.categoriaRepository = categoriaRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @GetMapping
    public String listar(Model model) {
        Pageable pageable = PageRequest.of(0, 20);
        model.addAttribute("listaChamados", service.listarComFiltros(null, null, null, pageable).getContent());
        return "listar";
    }

    @GetMapping("/novo")
    public String formularioNovoChamado(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("categorias", categoriaRepository.findAll());
        model.addAttribute("tecnicos", tecnicoRepository.findAll());
        return "novo-chamado";
    }

    @PostMapping
    public String salvarChamado( @RequestParam String titulo, @RequestParam String descricao, @RequestParam Prioridade prioridade, @RequestParam Long clienteId, @RequestParam Long categoriaId, @RequestParam Long tecnicoId) {
        
        ChamadoRequest dto = new ChamadoRequest(titulo, descricao, prioridade, clienteId, categoriaId, tecnicoId);
        
        service.criar(dto);
        
        return "redirect:/chamados";
    }
}