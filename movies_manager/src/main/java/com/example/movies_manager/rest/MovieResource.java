package com.example.movies_manager.rest;

import com.example.movies_manager.dao.LinkMovieActorDao;
import com.example.movies_manager.dao.MovieDao;
import com.example.movies_manager.dto.MovieDto;
import com.example.movies_manager.entity.Actor;
import com.example.movies_manager.entity.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * The MovieResource class represents a RESTful resource for managing movies.
 * It provides methods for retrieving movies by title.
 */
@Path("movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {
	
	private final MovieDao movieDao = new MovieDao();

    private final LinkMovieActorDao linkMovieActorDao = new LinkMovieActorDao();

    /**
     * Retrieves a list of movies by title.
     *
     * @param title The title of the movies to be retrieved.
     * @return A list of movies with matching title.
     */
    @GET
    public List<Movie> getMoviesByTitle(@QueryParam("title") String title) {
        return movieDao.getMoviesByTitle(title);
    }

    /**
     * Saves a movie.
     *
     * @param movie The movie to be saved.
     * @return The response indicating the success or failure of the operation.
     */
    @POST
    public Response saveMovie(MovieDto movie) {
        System.out.println( movie.getTitle());
        movie = movieDao.saveMovie(movie);
        List<Long> actorIds = movie.getActorIds();
        if(actorIds != null) {
            for(Long actorId : actorIds) {
                linkMovieActorDao.linkMovieAndActor(movie.getId(), actorId);
            }
        }
        return Response.ok(movie).build();
    }

}
