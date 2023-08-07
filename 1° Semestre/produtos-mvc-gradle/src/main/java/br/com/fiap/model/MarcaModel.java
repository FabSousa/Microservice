package br.com.fiap.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_MARCA")
public class MarcaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MARCA")
	private Long idMarca;
	
	@Column(name = "NOME_MARCA")
	private String nomeMarca;
	
	@OneToMany(mappedBy = "marca")
	private List<ProdutoModel> produtos;
	
	public MarcaModel() {
		super();
	}

	public MarcaModel(Long idMarca, String nomeMarca, List<ProdutoModel> produtos) {
		super();
		this.idMarca = idMarca;
		this.nomeMarca = nomeMarca;
		this.produtos = produtos;
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	@NotNull(message = "Nome é obrigatório")
	@Size(min = 2, max = 50, message = "Nome deve ter entre 2 e 50 caracteres")
	public String getNomeMarca() {
		return nomeMarca;
	}

	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	
}
