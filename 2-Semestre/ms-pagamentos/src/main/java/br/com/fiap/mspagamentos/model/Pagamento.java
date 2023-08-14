package br.com.fiap.mspagamentos.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal valor;
	private String nome;
	private String numeroDoCartao;
	private String validade;
	private String codigo;
	@NotNull(message = "Campo requerido")
	@Enumerated(EnumType.STRING)
	private Status status;
	private Long pedidoId;
	private Long formaDePagamentoId; // 1 - Cartao  2 - Dinheiro
	
	public Pagamento() {
		super();
	}

	public Pagamento(Long id, BigDecimal valor, String nome, String numerocartao, String validade, String codigo,
			Status status, Long pedidoId, Long formaDePagamentoId) {
		super();
		this.id = id;
		this.valor = valor;
		this.nome = nome;
		this.numeroDoCartao = numerocartao;
		this.validade = validade;
		this.codigo = codigo;
		this.status = status;
		this.pedidoId = pedidoId;
		this.formaDePagamentoId = formaDePagamentoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumerocartao() {
		return numeroDoCartao;
	}

	public void setNumerocartao(String numerocartao) {
		this.numeroDoCartao = numerocartao;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Long getFormaDePagamentoId() {
		return formaDePagamentoId;
	}

	public void setFormaDePagamentoId(Long formaDePagamentoId) {
		this.formaDePagamentoId = formaDePagamentoId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
