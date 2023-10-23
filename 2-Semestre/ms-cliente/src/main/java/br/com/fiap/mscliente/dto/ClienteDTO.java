package br.com.fiap.mscliente.dto;

import br.com.fiap.mscliente.model.Cliente;

import javax.validation.constraints.NotNull;

public class ClienteDTO {

    private Long id;
    @NotNull(message = "campo requerido")
    private String nome;
    @NotNull(message = "campo requerido")
    private String email;
    @NotNull(message = "campo requerido")
    private String endereco;
    @NotNull(message = "campo requerido")
    private String telefone;
    @NotNull(message = "campo requerido")
    private String senha;

    public ClienteDTO(Long id, String nome, String email, String endereco, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
    }

    public ClienteDTO(Cliente entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.endereco = entity.getEndereco();
        this.telefone = entity.getTelefone();
        this.senha = entity.getSenha();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
