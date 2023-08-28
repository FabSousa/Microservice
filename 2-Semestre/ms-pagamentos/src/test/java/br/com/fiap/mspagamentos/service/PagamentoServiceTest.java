package br.com.fiap.mspagamentos.service;

import br.com.fiap.mspagamentos.repository.PagamentoRepository;
import br.com.fiap.mspagamentos.service.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService service;
    @Mock
    private PagamentoRepository repository;

    private Long existingId;
    private Long nonExistingID;

    @BeforeEach
    void setup() throws Exception{
        existingId = 1L;
        nonExistingID = 10L;

        Mockito.doNothing().when(repository).deleteById(existingId);
        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when((repository.existsById(nonExistingID))).thenReturn(false);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });
        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> service.delete(nonExistingID));
    }

}