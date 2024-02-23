package com.example.spring.demospringrest.web.controller.v1;

import com.example.spring.demospringrest.AbstractTestController;
import com.example.spring.demospringrest.StringTestUtils;
import com.example.spring.demospringrest.exception.EntityNotFoundException;
import com.example.spring.demospringrest.mapper.v1.ClientMapper;
import com.example.spring.demospringrest.model.Client;
import com.example.spring.demospringrest.model.Order;
import com.example.spring.demospringrest.service.ClientService;
import com.example.spring.demospringrest.web.model.ClientListResponse;
import com.example.spring.demospringrest.web.model.ClientResponse;
import com.example.spring.demospringrest.web.model.OrderResponse;
import com.example.spring.demospringrest.web.model.UpsertClientRequest;
import net.bytebuddy.utility.RandomString;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientControllerTest extends AbstractTestController {

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientMapper clientMapper;

    @Test
    void whenFindAll_thenReturnAllClient() throws Exception {
        List<Client> clients = new ArrayList<>();
        clients.add(createClient(1L, null));
        Order order = createOrder(1L, 100L, null);
        clients.add(createClient(2L, order));

        List<ClientResponse> clientResponses = new ArrayList<>();
        clientResponses.add(createClientResponse(1L, null));
        OrderResponse orderResponse = createOrderResponse(1L, 100L);

        clientResponses.add(createClientResponse(2L, orderResponse));
        ClientListResponse clientListResponse = new ClientListResponse(clientResponses);

        when(clientService.findAll()).thenReturn(clients);
        when((clientMapper.clientListToClientResponseList(clients))).thenReturn(clientListResponse);

        String actualResponse = mockMvc.perform(get("/api/v1/client"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_all_clients_response.json");
        verify(clientService, times(1)).findAll();
        verify(clientMapper, times(1)).clientListToClientResponseList(clients);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    void whenGetClientById_thenReturnClientById() throws Exception {
        Client client = createClient(1L, null);
        ClientResponse clientResponse = createClientResponse(1L, null);

        when(clientService.findById(1L)).thenReturn(client);
        when(clientMapper.clientToResponse(client)).thenReturn(clientResponse);

        String actualResponse = mockMvc.perform(get("/api/v1/client/1"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/find_client_by_id_response.json");

        verify(clientService, times(1)).findById(1L);
        verify(clientMapper, times(1)).clientToResponse(client);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    void whenCreateClient_thenReturnNewClient() throws Exception {
        Client client = new Client();
        client.setName("Client 1");
        Client createdClient = createClient(1L, null);
        ClientResponse clientResponse = createClientResponse(1L, null);

        UpsertClientRequest request = new UpsertClientRequest("Client 1");

        when(clientService.save(client)).thenReturn(createdClient);
        when(clientMapper.requestToClient(request)).thenReturn(client);
        when(clientMapper.clientToResponse(createdClient)).thenReturn(clientResponse);

        String actualResponse = mockMvc.perform(post("/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/create_client_response.json");

        verify(clientService, times(1)).save(client);
        verify(clientMapper, times(1)).requestToClient(request);
        verify(clientMapper, times(1)).clientToResponse(createdClient);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    void whenUpdateClient_thenReturnUpdatedClient() throws Exception {
        UpsertClientRequest request = new UpsertClientRequest("New Client 1");
        Client updatedClient = new Client(1L, "New Client 1", new ArrayList<>());
        ClientResponse clientResponse = new ClientResponse(1L, "New Client 1", new ArrayList<>());

        when(clientService.update(updatedClient)).thenReturn(updatedClient);
        when(clientMapper.requestToClient(1L, request)).thenReturn(updatedClient);
        when(clientMapper.clientToResponse(updatedClient)).thenReturn(clientResponse);

        String actualResponse = mockMvc.perform(put("/api/v1/client/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String expectedResponse = StringTestUtils.readStringFromResource("response/update_client_response.json");

        verify(clientService, times(1)).update(updatedClient);
        verify(clientMapper, times(1)).requestToClient(1L, request);
        verify(clientMapper, times(1)).clientToResponse(updatedClient);

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @Test
    void whenDeleteClientById_thenReturnStatusNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/client/1"))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteById(1L);
    }

    @Test
    void whenFindByIdNotExistedClient_thenReturnError() throws Exception {
        when(clientService.findById(500L)).thenThrow(new EntityNotFoundException("Клиент с id=500 не найден"));
        var response = mockMvc.perform(get("/api/v1/client/{id}", 500))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse();

        response.setCharacterEncoding("UTF-8");

        String actualResponse = response.getContentAsString();
        String expectedResponse = StringTestUtils.readStringFromResource("response/client_by_id_not_found_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);

        Mockito.verify(clientService, times(1)).findById(500L);
    }

    @Test
    void whenCreateClientWithEmptyName_thenReturnError() throws Exception {
        var response = mockMvc.perform(post("/api/v1/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new UpsertClientRequest())))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        response.setCharacterEncoding("UTF-8");

        String actualResponse = response.getContentAsString();
        String expectedResponse = StringTestUtils.readStringFromResource("response/empty_client_name_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    @ParameterizedTest
    @MethodSource("invalidSizeName")
    void whenCreateClientWithInvalidSizeName_thenReturnError(String name) throws Exception {
        var response = mockMvc.perform(post("/api/v1/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new UpsertClientRequest(name))))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();
        response.setCharacterEncoding("UTF-8");

        String actualResponse = response.getContentAsString();
        String expectedResponse = StringTestUtils.readStringFromResource("response/client_name_size_exception_response.json");

        JsonAssert.assertJsonEquals(expectedResponse, actualResponse);
    }

    private static Stream<Arguments> invalidSizeName() {
        return Stream.of(
                Arguments.of(RandomString.make(2)),
                Arguments.of(RandomString.make(31))
        );
    }

}
