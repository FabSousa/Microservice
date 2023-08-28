package br.com.fiap.mspagamentos.repository;

import br.com.fiap.mspagamentos.model.Pagamento;
import br.com.fiap.mspagamentos.teste.Factory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class PagamentoRepositoryTests {

    @Autowired
    private PagamentoRepository repository;


    private Long existingId;
    private Long nonExistingID;
    private Long contTotalPagamento;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingID = 10L;
        contTotalPagamento = 2L;
    }

    @Test
    @DisplayName("Deveria deletar o objrto quando o ID existe")
    public void deleteShouldDeleteObjectWhenIdExists(){
        repository.deleteById(existingId);
        Optional<Pagamento> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldTrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingID);
        });
    }

    @Test
    public void saveShouldpersistWithAutoIncrementWhenidIsNull(){
        Pagamento pagamento = Factory.createPagamento();
        pagamento.setId(null);
        pagamento = repository.save(pagamento);
        Assertions.assertNotNull(pagamento.getId());
        Assertions.assertEquals(contTotalPagamento + 1, pagamento.getId());
    }

    @Test
    public void findByIdShouldReturnNotEmptyOptionalWhenIdExists(){
        Optional<Pagamento> pagamento = repository.findById(existingId);
        Assertions.assertTrue(pagamento.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenIdDontExists(){
        Optional<Pagamento> pagamento = repository.findById(nonExistingID);
        Assertions.assertFalse(pagamento.isPresent());
    }
}
