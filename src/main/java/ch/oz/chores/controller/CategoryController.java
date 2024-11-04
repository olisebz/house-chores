package ch.oz.chores.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import ch.oz.chores.model.*;

@Path("/categories")
public class CategoryController {

  private AtomicLong idCounter = new AtomicLong();
  private List<Category> categories = new ArrayList<>(
      List.of(
          new Category(idCounter.incrementAndGet(), "Cleaning"),
          new Category(idCounter.incrementAndGet(), "Cooking")));

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createCategory(Category category) {
    category.setId(idCounter.incrementAndGet());
    categories.add(category);
    return Response.status(Response.Status.CREATED).entity(category).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategories() {
    return Response.ok(categories).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategory(@PathParam("id") Long id) {
    return categories.stream()
        .filter(category -> category.getId().equals(id))
        .findFirst()
        .map(category -> Response.ok(category).build())
        .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @DELETE
  @Path("/{id}")
  public Response deleteCategory(@PathParam("id") Long id) {
    if (categories.removeIf(category -> category.getId().equals(id))) {
      return Response.noContent().build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
