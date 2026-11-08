package br.com.sistemas.chamados.dto;

import java.time.LocalDate;

import br.com.sistemas.chamados.entity.Chamado;
import br.com.sistemas.chamados.entity.Prioridade;
import br.com.sistemas.chamados.entity.StatusChamado;

public record ChamadoResponse(
    Long id,
    String titulo,
    String descricao,
    LocalDate dataAbertura,
    LocalDate dataFechamento,
    Prioridade prioridade,
    StatusChamado status,
    String nomeCliente,
    String nomeCategoria,
    String nomeTecnico
) {
    public static ChamadoResponse de(Chamado c) {
        return new ChamadoResponse(
            c.getId(),
            c.getTitulo(),
            c.getDescricao(),
            c.getDataAbertura(),
            c.getDataFechamento(),
            c.getPrioridade(),
            c.getStatus(),
            c.getCliente().getNome(),
            c.getCategoria().getNome(),
            c.getTecnico().getNome()
        );
    }
}