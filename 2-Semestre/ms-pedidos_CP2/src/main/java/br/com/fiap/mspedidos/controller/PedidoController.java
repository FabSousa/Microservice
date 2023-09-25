package br.com.fiap.mspedidos.controller;


import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll(){
        List<PedidoDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id){
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@Valid @RequestBody PedidoDTO dto){
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();

        return  ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable @Valid @Positive Long id,
                                             @Valid @RequestBody PedidoDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable @Valid @Positive Long id,
                                                  @Valid @RequestBody PedidoDTO dto){
        dto = service.updateStatus(id, dto.getStatus());
        return ResponseEntity.ok(dto);
    }


}
