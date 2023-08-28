package br.com.fiap.mspagamentos.teste;

import br.com.fiap.mspagamentos.model.Pagamento;
import br.com.fiap.mspagamentos.model.Status;

import java.math.BigDecimal;

public class Factory {

    public  static Pagamento createPagamento(){
        Pagamento pagamento = new Pagamento(1L, BigDecimal.valueOf(32.25), "Beach", "322345698", "07/25", "547", Status.CRIADO,
                1L, 2L);
        return pagamento;
    }
}
