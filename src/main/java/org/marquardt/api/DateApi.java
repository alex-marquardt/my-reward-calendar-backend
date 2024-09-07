package org.marquardt.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.marquardt.api.model.CreateDateRequest;
import org.marquardt.api.model.DateResponse;
import org.marquardt.api.model.UpdateDateRequest;
import org.marquardt.service.DateService;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
@Path("/date")
@Produces(MediaType.APPLICATION_JSON)
public class DateApi {

    @Inject
    DateService dateService;

    @GET
    @Path("/all")
    public List<DateResponse> getDates() {
        return dateService.getAllDates();
    }

    @GET
    @Path("/{id}")
    public DateResponse getDateById(String id) {
        return dateService.getDate(id);
    }

    @POST
    @Path("/")
    public DateResponse createDate(CreateDateRequest request) {
        return dateService.createDate(request);
    }

    @PUT
    @Path("/{id}")
    public DateResponse updateDate(String id, UpdateDateRequest request) {
        return dateService.updateDate(id, request);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDate(String id) {
        dateService.deleteDate(id);
    }
}
