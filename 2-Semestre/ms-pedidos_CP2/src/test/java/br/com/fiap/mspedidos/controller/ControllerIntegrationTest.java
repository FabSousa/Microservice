package br.com.fiap.mspedidos.controller;

import br.com.fiap.mspedidos.dto.PedidoDTO;
import br.com.fiap.mspedidos.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private Long idExiste;
    private Long idNaoExiste;
    private PedidoDTO pedidoDTO;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        idExiste = 1L;
        idNaoExiste = 50L;
        pedidoDTO = Factory.createPedidoDTO();
    }

    @Test
    @DisplayName("findAll Deve retornar uma Lista de todos pedidos e itens de pedido e, status 200: Fabio 89291")
    public void findAllDeveListarTodosPedidosFabio89291() throws Exception {
        mockMvc.perform(get("/pedidos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].status").value("CRIADO"))
                .andExpect(jsonPath("$[0].itens[0].id").value(1))
                .andExpect(jsonPath("$[1].itens[0].id").value(2));
    }

    @Test
    @DisplayName("findById Deve retornar pedido e itens de pedido quando Id existe e, status 200; Fabio 89291")
    public void findByIdDeveRetornarPedidoEItensDePedidoQuandoIdExisteEStatus200Fabio89291() throws Exception{
        mockMvc.perform(get("/pedidos/{id}", idExiste))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.itens[0].id").value(1))
                .andExpect(jsonPath("$.status").value("CRIADO"));
    }

    @Test
    @DisplayName("findById Deve retornar NotFound quando Id não existe e, status 404; Fabio 89291")
    public void findByIdDeveRetornarNotFoundQuandoIdNaoExisteEStatus404Fabio89291() throws Exception{
        mockMvc.perform(get("/pedidos/{id}", idNaoExiste))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("insert Deve salvar pedido e itens de pedido, retornar status 201 e Location no Header; Fabio 89291")
    public void insertDeveSalvarPedidoEItensDePedidoRetornarStatus201ELocationNoHeaderFabio89291() throws Exception{
        String corpoJson = objectMapper.writeValueAsString(pedidoDTO);
        mockMvc.perform(post("/pedidos")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(corpoJson))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.itens[0].id").exists());
    }

    @Test
    @DisplayName("update Deve atualizar status do pedido para CONFIRMADO quando id existe e, status 200; Fabio 89291")
    public void updateDeveAtualizarStatusDoPedidoParaCONFIRMADOQuandoIdExisteEStatus200Fabio89291() throws Exception{
        mockMvc.perform(patch("/pedidos/{id}/status", idExiste)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"status\": \"CONFIRMADO\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CONFIRMADO"));
    }

    @Test
    @DisplayName("update Deve retornar NotFound quando Id não existe e, status 404; Fabio 89291")
    public void updateDeveRetornarNotFoundQuandoIdNaoExisteEstatus404Fabio89291() throws Exception{
        String corpoJson = objectMapper.writeValueAsString(pedidoDTO);
        mockMvc.perform(put("/pedidos/{id}", idNaoExiste)
                    .content(corpoJson)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                 .andDo(print())
                 .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("delete Deve retornar NoContent quando Id existe e, status 204; Fabio 89291")
    public void deleteDeveRetornarNoContentQuandoIdExisteEStatus204Fabio89291() throws Exception{
         mockMvc.perform(delete("/pedidos/{id}", idExiste))
                 .andDo(print())
                 .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("delete Deve retornar NotFound quando Id não Existe e, status 404; Fabio 89291")
    public void deleteDeveRetornarNotFoundQuandoIdNaoExisteEStatus404Fabio89291() throws Exception{
        mockMvc.perform(delete("/pedidos/{id}", idNaoExiste))
                .andDo(print())
                .andExpect(status().isNotFound());
    }






}
