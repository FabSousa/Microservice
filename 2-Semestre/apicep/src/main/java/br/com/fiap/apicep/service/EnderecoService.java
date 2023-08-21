package br.com.fiap.apicep.service;

import br.com.fiap.apicep.dto.EnderecoDTO;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    public EnderecoDTO getCep(String cep){
        String url = "https://viacep.com.br/ws/" + cep + "/json";

        
    }
}
