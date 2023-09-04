package br.com.fiap.fabio89291.fabio89291.serviceFabio89291;

import br.com.fiap.fabio89291.fabio89291.dto.PedidoDTOFabio89291;
import br.com.fiap.fabio89291.fabio89291.modelFabio89291.PedidoFabio89291;
import br.com.fiap.fabio89291.fabio89291.repositoryFabio89291.PedidoRepositoryFabio8921;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class PedidoServiceFabio89291 {

    @Autowired
    private PedidoRepositoryFabio8921 repository;

    @Transactional(readOnly = true)
    public List<PedidoDTOFabio89291> findAll(){
        List<PedidoFabio89291> list = repository.findAll();

        return list.stream().map(x -> new PedidoDTOFabio89291(x)).collect(Collectors.toList());
    }

}
