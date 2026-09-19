package br.com.necropolis.resource;

import br.com.necropolis.dto.request.QuadraRequest;
import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.service.QuadraService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/quadras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuadraResource {

    @Inject
    QuadraService service;

    @POST
    public Response cadastrar(@Valid QuadraRequest request) {

        QuadraResponse response = service.cadastrar(request);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    public List<QuadraResponse> listar() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    public QuadraResponse buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public QuadraResponse atualizar(
            @PathParam("id") Long id,
            @Valid QuadraRequest request) {

        return service.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {

        service.excluir(id);

        return Response.noContent().build();
    }
}