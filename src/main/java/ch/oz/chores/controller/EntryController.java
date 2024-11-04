package ch.oz.chores.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import ch.oz.chores.model.Entry;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryController {

    private List<Entry> entries = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();

    @POST
    public Response createEntry(Entry entry) {
        entry.setId(idCounter.incrementAndGet());
        entries.add(entry);
        return Response.status(Response.Status.CREATED).entity(entry).build();
    }

    @GET
    public List<Entry> getAllEntries() {
        return entries;
    }

    @GET
    @Path("/{id}")
    public Response getEntry(@PathParam("id") Long id) {
        return entries.stream()
            .filter(entry -> entry.getId().equals(id))
            .findFirst()
            .map(entry -> Response.ok(entry).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteEntry(@PathParam("id") Long id) {
        if (entries.removeIf(entry -> entry.getId().equals(id))) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
