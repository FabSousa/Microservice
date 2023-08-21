package br.com.fiap.mspagamentos.dto;

import br.com.fiap.mspagamentos.model.Pagamento;
import br.com.fiap.mspagamentos.model.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class PagamentoDTO {

    private Long id;
    @NotNull(message = "Campo requerido")
    @Positive(message = "o valor deve ser um número positivo")
    private BigDecimal valor;
    private String nome;
    private String numeroDoCartao;
    private String validade;
    private String codigo;
    private Status status;
    @NotNull(message = "Campo requerido")
    @Positive
    private Long pedidoId;
    @NotNull(message = "Campo requerido")
    @Positive
    private Long formaDePagamento;

    public PagamentoDTO(){

    }

    public PagamentoDTO(Pagamento entity) {
        this.id = entity.getId();
        this.valor = entity.getValor();
        this.nome = entity.getNome();
        this.numeroDoCartao = entity.getNumerocartao();
        this.validade = entity.getValidade();
        this.codigo = entity.getCodigo();
        this.status = entity.getStatus();
        this.pedidoId = entity.getPedidoId();
        this.formaDePagamento = entity.getFormaDePagamentoId();
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public String getValidade() {
        return validade;
    }

    public String getCodigo() {
        return codigo;
    }

    public Status getStatus() {
        return status;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public Long getFormaDePagamento() {
        return formaDePagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoDTO that = (PagamentoDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
