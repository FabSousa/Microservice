package br.com.fiap.msprodutos.dto;

public class DadosTokenJwtDTO {

    private String token;

    public DadosTokenJwtDTO(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }
}
