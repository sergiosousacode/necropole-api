package br.com.necropolis.resource;

import br.com.necropolis.dto.request.GavetaRequest;
import br.com.necropolis.dto.response.GavetaResponse;
import br.com.necropolis.service.GavetaService;

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

@Path("/api/gavetas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GavetaResource {

    @Inject
    GavetaService service;

    @POST
    public Response cadastrar(@Valid GavetaRequest request) {

        GavetaResponse response = service.cadastrar(request);

        return Response
                .status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    public List<GavetaResponse> listar() {
        return service.listar();
    }

    @GET
    @Path("/{id}")
    public GavetaResponse buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @PUT
    @Path("/{id}")
    public GavetaResponse atualizar(
            @PathParam("id") Long id,
            @Valid GavetaRequest request) {

        return service.atualizar(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {

        service.excluir(id);

        return Response.noContent().build();
    }
}