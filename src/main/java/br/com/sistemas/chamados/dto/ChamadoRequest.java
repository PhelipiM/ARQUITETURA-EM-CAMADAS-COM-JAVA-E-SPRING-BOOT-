package br.com.sistemas.chamados.dto;

import br.com.sistemas.chamados.entity.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChamadoRequest(
    @NotBlank(message = "Título é obrigatório")
    @Size(max = 100, message = "Título deve ter no máximo 100 caracteres")
    String titulo,

    @NotBlank(message = "Descrição é obrigatória")
    String descricao,

    @NotNull(message = "Prioridade é obrigatória")
    Prioridade prioridade,

    @NotNull(message = "ID do Cliente é obrigatório")
    Long clienteId,

    @NotNull(message = "ID da categoria é obrigatório")
    Long categoriaId,

    @NotNull(message = "ID do técnico é obrigatório")
    Long tecnicoId
) {}