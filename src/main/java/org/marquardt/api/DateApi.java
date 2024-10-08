package org.marquardt.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.marquardt.model.ingoing.DateRequest;
import org.marquardt.model.outgoing.DateResponse;
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
    public DateResponse getDate(@PathParam("id") String id) {
        return dateService.getDate(id);
    }

    @PUT
    @Path("/")
    public DateResponse createOrUpdateDate(DateRequest request) {
        return dateService.upsertDate(request);
    }

    @DELETE
    @Path("/{id}")
    public void deleteDate(@PathParam("id") String id) {
        dateService.deleteDate(id);
    }
}
