package br.com.fiap.fabio89291.fabio89291.controllerFabio89291;

import br.com.fiap.fabio89291.fabio89291.dto.PedidoDTOFabio89291;
import br.com.fiap.fabio89291.fabio89291.serviceFabio89291.PedidoServiceFabio89291;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoControllerFabio89291 {

    @Autowired
    private PedidoServiceFabio89291 service;

    @GetMapping
    public ResponseEntity<List<PedidoDTOFabio89291>> findAll(){
        List<PedidoDTOFabio89291> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }
}
