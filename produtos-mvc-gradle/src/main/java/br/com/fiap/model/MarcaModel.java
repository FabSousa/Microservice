package br.com.fiap.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MarcaModel {

	private Long idMarca;
	private String nomeMarca;
	
	public MarcaModel() {
		super();
	}
	
	public MarcaModel(Long idMarca, String nomeMarca) {
		super();
		this.idMarca = idMarca;
		this.nomeMarca = nomeMarca;
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
