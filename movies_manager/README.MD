
# Movie Management Project

This is a Java project for a movie database system. It provides APIs for retrieving movies and actors information based on movie title, actor name, and actor ID. It also includes an external API integration to retrieve movies from an external movie database API.

## Team Members

- ZAiNOUL Meryem (zainoulmeryem1@gmail.com)
- JAMAL Hajar (Hajarjamal05@gmail.com)

## Project Introduction

The Movie Management Project is designed to provide a RESTful API for retrieving movie and actor information. It includes the following components:

- `MovieResource`: A JAX-RS resource class that provides endpoints for retrieving movies based on movie title, save movie.
- `ActorResource`: A JAX-RS resource class that provides endpoints for retrieving actors based on actor name, save actor.
- `DataConnection`: A utility class that handles the database connection.

In addition, the project includes an external API integration using the `ExternAPI` class, which provides an endpoint for retrieving movies from an external movie database API.

## Services' Description

The Movie Management Project provides the following APIs:

### MovieResource

- `GET /api/movies?title={title}`: Retrieves a list of movies based on the provided movie title. The `title` parameter is mandatory and specifies the movie title to search for.
- `POST /api/movies`: Saves a new movie to the database based on the provided movie data in the request body.

### ActorResource

- `GET /api/actors?actor_name={actor_name}`: Retrieves a list of actors based on the provided actor name. The `actor_name` parameter is mandatory and specifies the name of the actor to search for.
- `POST /api/actors`: Saves a new actor to the database based on the provided actor data in the request body.

## Clients' Description

The Movie Management Project includes an external API integration using the `ExternAPI` class, which provides an endpoint for retrieving movies from an external movie database API based on movie title.

### ExternalMovieClient

- `GET /api/movies/external`: Retrieves a list of movies from an external movie database API based on the provided movie title.

## Use Case

The Movie Management Project can be used for the following use cases:

1. Searching for movies by title: Users can search for movies by title using the `/api/movies?title={title}` endpoint. They can provide the movie title as a query parameter, and the API will return a list of movies that match the provided title.

3. Searching for actors by name: Users can search for actors by name using the `api/actors?actor_name={actor_name}` endpoint. They can provide the actor name as a query parameter, and the API will return a list of actors that match the provided name.

4. Adding a new Movie, Actor
5.  Used the api to make a complete interface. Let's run the project to experience
