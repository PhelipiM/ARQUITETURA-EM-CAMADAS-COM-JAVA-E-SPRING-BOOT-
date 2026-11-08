package br.com.sistemas.chamados.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.sistemas.chamados.entity.Cliente;

@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void deveRetornarTrueQuandoEmailJaExiste() {
        Cliente cliente = new Cliente("Ana Souza", "ana@exemplo.com", "47999999999");
        clienteRepository.save(cliente);

        boolean existe = clienteRepository.existsByEmail("ana@exemplo.com");

        assertThat(existe).isTrue();
    }

    @Test
    public void deveRetornarFalseQuandoEmailNaoExiste() {
        boolean existe = clienteRepository.existsByEmail("naoexiste@exemplo.com");
        assertThat(existe).isFalse();
    }
}