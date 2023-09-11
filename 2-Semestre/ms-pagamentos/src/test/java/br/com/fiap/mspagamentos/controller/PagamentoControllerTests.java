package br.com.fiap.mspagamentos.controller;

import br.com.fiap.mspagamentos.dto.PagamentoDTO;
import br.com.fiap.mspagamentos.model.Status;
import br.com.fiap.mspagamentos.service.PagamentoService;
import br.com.fiap.mspagamentos.service.exception.ResourceNotFoundException;
import br.com.fiap.mspagamentos.teste.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(PagamentoController.class)
public class PagamentoControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PagamentoService service;
    private PagamentoDTO pagamentoDTO;
    private Long idExiste;
    private Long idNaoExiste;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception{
        pagamentoDTO = Factory.createPagamentoDTO();
        List<PagamentoDTO> list = List.of(pagamentoDTO);
        when(service.findAll()).thenReturn(list);

        idExiste = 1L;
        idNaoExiste = 10L;

        when(service.findById(idExiste)).thenReturn(pagamentoDTO);
        when(service.findById(idNaoExiste)).thenThrow(ResourceNotFoundException.class);
        when(service.insert(any())).thenReturn(pagamentoDTO);
        when(service.update(eq(idExiste), any())).thenReturn(pagamentoDTO);
        when(service.update(eq(idNaoExiste), any())).thenThrow(ResourceNotFoundException.class);
        doNothing().when(service).delete(idExiste);
        doThrow(ResourceNotFoundException.class).when(service).delete(idNaoExiste);
    }

    @Test
    @DisplayName("finfAll deveria retornar uma lista do tipo PagamentoDTO")
    public void finfAllDeveriaRetornarUmaListaDoTipoPagamentoDTO() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/pagamentos"));
        resultado.andExpect(status().isOk());
        resultado.andDo(print());
    }

    @Test
    @DisplayName("findById deve retornar PagamentoDTO quando id existe")
    public void findByIdDeveRetornarPagamentoDTOQuandoIExiste() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/pagamentos/{id}", idExiste));
        resultado.andExpect(status().isOk());
        resultado.andDo(print());
        resultado.andExpect(jsonPath("$.id").exists());
        resultado.andExpect(jsonPath("$.valor").exists());
        resultado.andExpect(jsonPath("$.status").exists());
    }

    @Test
    @DisplayName("findById deve retornar NofFoundExceptio quando id nao existe")
    public void findByIdDeveRetornarNofFoundExceptioQuandoIdnaoExiste() throws Exception{
        ResultActions resultado = mockMvc.perform(get("/pagamentos/{id}", idNaoExiste));
        resultado.andExpect(status().isNotFound());
        resultado.andDo(print());
    }

    @Test
    @DisplayName("insert deve retornar created PagamentoDTO com id")
    public void insertDeveRetornanrCreatedPagamnetoDTOComId() throws Exception{
        PagamentoDTO dto = new PagamentoDTO(null, BigDecimal.valueOf(32.25), "Bach", "322345698",
               "07/25", "547", Status.CRIADO, 1L, 2L);
        String corpoJson = objectMapper.writeValueAsString(dto);

        ResultActions resultado = mockMvc.perform(post("/pagamentos").content(corpoJson)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isCreated());
        resultado.andDo(print());
        resultado.andExpect(header().exists("location"));
        resultado.andExpect(jsonPath("$.id").exists());
        resultado.andExpect(jsonPath("$.valor").exists());
        resultado.andExpect(jsonPath("$.status").exists());
    }

    @Test
    @DisplayName("update deve retornar PagamentoDTO quando id existe")
    public void updateDeveRetornarPagamentoDTOQuandoIdExiste() throws Exception{
        String corpoJson = objectMapper.writeValueAsString(pagamentoDTO);

        ResultActions resultado = mockMvc.perform(put("/pagamentos/{id}", idExiste).content(corpoJson)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isOk());
        resultado.andDo(print());
        resultado.andExpect(jsonPath("$.id").exists());
        resultado.andExpect(jsonPath("$.valor").exists());
        resultado.andExpect(jsonPath("$.status").exists());
    }

    @Test
    @DisplayName("update deve retornar NotFound quando id nao existe")
    public void updateDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception{
        String corpoJson = objectMapper.writeValueAsString(pagamentoDTO);

        ResultActions resultado = mockMvc.perform(put("/pagamentos/{id}", idNaoExiste).content(corpoJson)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        resultado.andExpect(status().isNotFound());
        resultado.andDo(print());
    }

    @Test
    @DisplayName("delete deve retornar NoContent quando o id existe")
    public void deleteDeveRetornarNoContentQuandoOIdExiste() throws Exception{
        ResultActions resultado = mockMvc.perform(delete("/pagamentos/{id}", idExiste));
        resultado.andExpect(status().isNoContent());
        resultado.andDo(print());
    }

    @Test
    @DisplayName("delete deve retornar NotFound quando o id nao existe")
    public void deleteDeveRetornarNotFoundQuandoOIdNaoExiste() throws Exception{
        ResultActions resultado = mockMvc.perform(delete("/pagamentos/{id}", idNaoExiste));
        resultado.andExpect(status().isNotFound());
        resultado.andDo(print());
    }


}
