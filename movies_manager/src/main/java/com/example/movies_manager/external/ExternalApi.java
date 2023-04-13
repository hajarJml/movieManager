package com.example.movies_manager.external;


import com.mysql.cj.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The ExternAPI class provides a method for calling an external API to retrieve movies by title.
 * The API is accessed via the following endpoint: https://moviesdatabase.p.rapidapi.com/titles?
 * This class uses the RapidAPI platform to make HTTP requests and handle responses.
 */
@Path("external")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExternalApi {

    /**
     * Retrieves a list of movies from the external API.
     *
     * @return A Response object representing the HTTP response from the external API.
     * The response contains the retrieved movies as a list of Movie objects.
     */
    @GET
    public Response getMovieByTitleWithExternalAPI(@QueryParam("title") String title) {
        Client client = ClientBuilder.newClient();
        if(title == null || title.trim().isEmpty()) {
            title = "simple";
        }
        String url = "https://moviesdatabase.p.rapidapi.com/titles/search/keyword/"+ title+"?limit=50";
        System.out.println("----URL" + url);
        WebTarget target = client.target(url);
        String rs = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("X-RapidAPI-Key", "8af82a1349msh0eaa9740d762cdbp11f4fbjsnfb370f69e1f7")
                .header("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .get(String.class);
        return Response.ok(rs).build();
    }

}
