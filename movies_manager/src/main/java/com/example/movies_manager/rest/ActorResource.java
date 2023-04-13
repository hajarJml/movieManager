package com.example.movies_manager.rest;

import com.example.movies_manager.dao.ActorDao;
import com.example.movies_manager.entity.Actor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The ActorResource class represents a RESTful resource for managing actors.
 * It provides methods for retrieving actors by name and saving an actor.
 */
@Path("actors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActorResource {

    private final ActorDao actorDao = new ActorDao();
    /**
     * Retrieves a list of actors by actor name.
     *
     * @param actorName The name of the actors to be retrieved.
     * @return A list of actors with matching name.
     */
    @GET
    public List<Actor> getActorsByActorName(@QueryParam("actor_name") String actorName) {
        return actorDao.getActorsByName(actorName);
    }

    /**
     * Saves an actor.
     *
     * @param actor The actor to be saved.
     * @return The response indicating the success or failure of the operation.
     */
    @POST
    public Response saveMovie(Actor actor) {
        return Response.ok(actorDao.saveActor(actor)).build();
    }
}
