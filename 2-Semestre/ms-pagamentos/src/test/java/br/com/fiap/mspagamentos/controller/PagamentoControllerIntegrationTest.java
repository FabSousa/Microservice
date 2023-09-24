package br.com.fiap.mspagamentos.controller;

import br.com.fiap.mspagamentos.dto.PagamentoDTO;
import br.com.fiap.mspagamentos.model.Pagamento;
import br.com.fiap.mspagamentos.model.Status;
import br.com.fiap.mspagamentos.teste.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PagamentoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private Long idExiste;
    private Long idNaoExiste;
    private Long totalRegistros;
    private PagamentoDTO pagamentoDTO;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception{
        idExiste = 1l;
        idNaoExiste = 50l;
        totalRegistros = 6L;
        pagamentoDTO = Factory.createPagamentoDTO();
    }

    @Test
    @DisplayName("FindAll deve listar todos os pagamentos e retornar status 200")
    public void findAllDeveListarTododsOsPagamentos() throws Exception{
        mockMvc.perform(get("/pagamentos")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").isString())
                .andExpect(jsonPath("$[0].nome").value("Nicodemus"))
                .andExpect(jsonPath("$[0].status").value("CRIADO"));
    }

    @Test
    @DisplayName("findById deve retornar um pagamento pelo ID e com status 200")
    public void findByIdDeveRetornarUmPagamentoPeloID() throws Exception{
        mockMvc.perform(get("/pagamentos/{id}", idExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Nicodemus"));
    }

    @Test
    @DisplayName("findById deve retornar NotFound quando ID nao existe")
    public void findByIdDeveRetornarNotFoundQaundoIdNaoExiste() throws Exception{
        mockMvc.perform(get("/pagamentos/{id}", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Insert deve salvar um pagamneto, retornar status 201 e location no header")
    public void InseertDeveSalvarPagamento() throws Exception{
        Pagamento entity = Factory.createPagamento();
        entity.setId(null);
        PagamentoDTO dto = new PagamentoDTO(entity);
        String corpoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/pagamentos")
                    .content(corpoJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.nome").value("Beach"));;
    }

    @Test
    @DisplayName("Insert deve salvar um pagamento com os capos obrigatorios, retornar status 201 e location no header")
    public void insertDeveSalvarPagamentoComposObrigatorios() throws Exception{
        Pagamento entity = new Pagamento(null, BigDecimal.valueOf(25.25),null,null,null,
                null, Status.CRIADO, 1L, 1L);
        PagamentoDTO dto = new PagamentoDTO(entity);
        String corpoJson = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/pagamentos")
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("insert deve lancar exception quando dados inválidos e retornar status 422")
    public void insertdeveLancarExceptionQuandoDadosInvalidos() throws Exception{
        Pagamento entity = new Pagamento();
        entity.setValor(BigDecimal.valueOf(25.25));
        entity.setFormaDePagamentoId(1L);

        String corpoJson = objectMapper.writeValueAsString(entity);
        mockMvc.perform(post("/pagamentos")
                    .content(corpoJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("Update deve atualizar pagamento quando Id existe e retornar status 200")
    public void updateDeveAtualizarPagamentoQuandoIdExiste() throws Exception {
        String corpoJson = objectMapper.writeValueAsString(pagamentoDTO);
        mockMvc.perform(put("/pagamentos/{id}", idExiste)
                    .content(corpoJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.valor").exists())
                .andExpect(jsonPath("$.status").exists());
    }

    @Test
    @DisplayName("Update deve retornar NotFound quando ID nao exsite")
    public void updateDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception {
        String corpoJson = objectMapper.writeValueAsString(pagamentoDTO);
        mockMvc.perform(put("/pagamentos/{id}", idNaoExiste)
                        .content(corpoJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("delete deve retornar NoContent quando Id existe, status 204")
    public void deleteDeveRetornarNoContentQuandoIdExiste() throws Exception{
        mockMvc.perform(delete("/pagamentos/{id}", idExiste)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("delete deve retornar NotFound quando o Id nao existe, status 404")
    public void deleteDeveRetornarNotFoundQuandoIdNaoExiste() throws Exception{
        mockMvc.perform(delete("/pagamentos/{id}", idNaoExiste)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("deve alterar status para CONFIRMADO quando Id existe, status 200")
    public void patchDeveAlteraraStatusParaConfirmadoQuandoIdExiste() throws Exception{
        mockMvc.perform(patch("/pagamentos/{id}/confirmar", idExiste)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("deve alterar status para CANCELADO quando Id existe, status 200")
    public void patchDeveAlteraraStatusParaCanceladoQuandoIdExiste() throws Exception{
        mockMvc.perform(patch("/pagamentos/{id}/cancelar", idExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("deve alterar status 404 quando Id nao existe")
    public void patchDeveretornarNotFound404QuandoIdNaoExiste() throws Exception{
        mockMvc.perform(patch("/pagamentos/{id}/confirmar", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("deve alterar status 404 quando Id nao existe")
    public void patchDeveretornarNotFoundQuandoIdNaoExiste() throws Exception{
        mockMvc.perform(patch("/pagamentos/{id}/cancelar", idNaoExiste)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
