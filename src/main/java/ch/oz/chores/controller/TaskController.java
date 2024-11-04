package ch.oz.chores.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import ch.oz.chores.model.*;

@Path("/tasks")
public class TaskController {

  private AtomicLong idCounter = new AtomicLong();
  private List<Task> tasks = new ArrayList<Task>(List.of(
      new Task(idCounter.incrementAndGet(), new Category(1l, "Cleaning"), "Clean toilets"),
      new Task(idCounter.incrementAndGet(), new Category(1l, "Cleaning"), "Clean bathrooms"),
      new Task(idCounter.incrementAndGet(), new Category(2l, "Cooking"), "Make lunch")));

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createTask(Task task) {
    task.setId(idCounter.incrementAndGet());
    tasks.add(task);
    return Response.status(Response.Status.CREATED).entity(task).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Task> getAllTasks() {
    return tasks;
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTask(@PathParam("id") Long id) {
    return tasks.stream()
        .filter(task -> task.getId().equals(id))
        .findFirst()
        .map(task -> Response.ok(task).build())
        .orElse(Response.status(Response.Status.NOT_FOUND).build());
  }

  @DELETE
  @Path("/{id}")
  public Response deleteTask(@PathParam("id") Long id) {
    if (tasks.removeIf(task -> task.getId().equals(id))) {
      return Response.status(Response.Status.OK).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }
}
