package br.com.sistemas.chamados.dto;

import jakarta.validation.constraints.NotBlank;

public record ComentarioRequest(
    @NotBlank(message = "O texto do comentário é obrigatório")
    String texto,

    @NotBlank(message = "O autor é obrigatório")
    String autor
) {}