package com.fiap.ClientesProdutos.rest.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ClientesProdutos.rest.api.repository.ClientesRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.fiap.ClientesProdutos.rest.api.model.ClientesModel;

@RestController
@Tag(name="API de Clientes", description="API que mostra os clientes de produtos")
@RequestMapping("api/clientes")
public class ClientesController {

	@Autowired
	private ClientesRepository repository;
	
	
	@GetMapping
	@Operation(summary = "Exibe todos os clientes")
	@ResponseStatus(HttpStatus.OK)
	public List<ClientesModel> index() {
		return repository.findAll();
	}
	
	
	@PostMapping
	@Operation(summary = "Cria um novo cliente")
	@ApiResponses(
		value = {
				@ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
				@ApiResponse(responseCode = "400", description = "Parametros insuficientes"),
				@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
		}
	)
	public ResponseEntity<String> create(@RequestBody ClientesModel clientesResponse) {
		try {
			if(clientesResponse.getNome() == null || clientesResponse.getEndereco() == null || clientesResponse.getTelefone() == null) {
				System.out.println("-----ERRO-----");
				System.out.println("TODOS OS CAMPOS SAO OBRIGATÓRIOS");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			
			ClientesModel cliente = new ClientesModel();
			cliente.setNome(clientesResponse.getNome());
			cliente.setEndereco(clientesResponse.getEndereco());
			cliente.setTelefone(clientesResponse.getTelefone());
			
			repository.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("id")
	@Operation(summary = "Exibe cliente por id")
	@ApiResponses(
		value = {
				@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso"),
				@ApiResponse(responseCode = "404", description = "Cliente não encontado")
		}
	)
	public ResponseEntity<ClientesModel> show(@PathVariable("id") Long id) {
		Optional<ClientesModel> cliente = repository.findById(id);
		
		if(cliente.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(cliente.get());
	}
	
	
	@PutMapping("id")
	@Operation(summary = "Atualiza cliente por id")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			}
		)
	public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody ClientesModel clientesRequest){
		try {
			if(repository.findById(id).isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			ClientesModel cliente = new ClientesModel();
			cliente.setNome(clientesRequest.getNome());
			cliente.setEndereco(clientesRequest.getEndereco());
			cliente.setTelefone(clientesRequest.getTelefone());
			
			repository.save(cliente);
			
			return ResponseEntity.ok("Cliente atualizado com sucesso");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@PatchMapping("id")
	@Operation(summary = "Atualiza parcialmente cliente por id")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "200", description = "Cliente atualizado parcialmente com sucesso"),
					@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			}
		)
	public ResponseEntity<String> change(@PathVariable("id") Long id, @RequestBody ClientesModel clientesRequest){
		try {
			if(repository.findById(id).isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			ClientesModel cliente = new ClientesModel();
			cliente.setId(clientesRequest.getId());
			
			if(cliente.getNome() == null)
				cliente.setNome(clientesRequest.getNome());
			
			if(cliente.getEndereco() == null)
				cliente.setEndereco(clientesRequest.getEndereco());
			
			if(cliente.getTelefone() == null)
				cliente.setTelefone(clientesRequest.getTelefone());
			
			repository.save(cliente);
			
			return ResponseEntity.ok("Cliente atualizado parcialmente com sucesso");
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	
	}
	
	@DeleteMapping("id")
	@Operation(summary = "Deleta cliente por id")
	@ApiResponses(
			value = {
					@ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
					@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
					@ApiResponse(responseCode = "500", description = "Erro interno no servidor"),
			}
		)
	public ResponseEntity<String> destroy(@PathVariable("id") Long id) {
		try {
			if(repository.findById(id).isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
