package br.com.fiap.mspagamentos.service;

import br.com.fiap.mspagamentos.repository.PagamentoRepository;
import br.com.fiap.mspagamentos.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PagamentoServiceIntegrationTest {
    @Autowired
    private PagamentoService service;
    @Autowired
    private PagamentoRepository repository;

    private Long idExiste;
    private Long idNaoExiste;
    private Long totalRegistros;


    @BeforeEach
    void setup() throws Exception{
        idExiste = 1l;
        idNaoExiste = 10l;
        totalRegistros = 6L;
    }

    @Test
    @DisplayName("Deve deletar um pagamento quando o ID existe")
    public void deleteDeveDeletarPagamentoQuandoIdExiste(){
        service.delete(idExiste);
        Assertions.assertEquals(totalRegistros - 1, repository.count());
    }

    @Test
    @DisplayName("Deve retornar um ResourceNotFound qunado o ID nao existe")
    public void deleteDeveretornarUmResourceNotFoundQuandoIdNaoExiste(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(idNaoExiste));
    }

    @Test
    @DisplayName("FindAll deve listar todos os pagamentos")
    public void findAllDeveListarTododsOsPagamentos(){
        var resultado = service.findAll();
        Assertions.assertFalse(resultado.isEmpty());
        Assertions.assertEquals(totalRegistros, resultado.size());
        Assertions.assertEquals(Double.valueOf(1200.00), resultado.get(0).getValor().doubleValue());
        Assertions.assertEquals("Amadeus", resultado.get(1).getNome());
        Assertions.assertEquals(null, resultado.get(5).getNome());
    }

}
