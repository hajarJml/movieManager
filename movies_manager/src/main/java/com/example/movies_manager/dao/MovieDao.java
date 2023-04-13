package com.example.movies_manager.dao;

import com.example.movies_manager.dto.MovieDto;
import com.example.movies_manager.entity.Actor;
import com.example.movies_manager.entity.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The MovieDao class represents a data access object for managing movies in the system.
 * It provides methods for CRUD operations on movie objects.
 */
public class MovieDao {


    /**
     * Retrieves a list of movies from the system based on the given title.
     *
     * @param title The title of the movie or a part of the title for which to retrieve movies.
     * @return A list of movies whose title contains the given title.
     *         An empty list if no movies are found.
     */
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> list = new ArrayList<>();
        DataConnection db = new DataConnection();
        Connection cnn = db.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from movie where title like ?";
            statement = cnn.prepareStatement(sql);
            if(title == null) {
                title = "";
            }
            String key = "%" + title + "%";
            statement.setString(1, key);
            System.out.println("SQL QUERY:" + statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String titleInDb = rs.getString(2);
                Long duration = rs.getLong(3);
                Float rating = rs.getFloat(4);
                String description = rs.getString(5);
                ActorDao actorDao = new ActorDao();
                List<Actor> actors = actorDao.getActorsByMovieId(id);
                Movie movie = new Movie(id, titleInDb, duration, rating, description, actors);
                list.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(statement != null) {
                    statement.close();
                }
                cnn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }


    /**
     * Saves a movie to the system.
     *
     * @param movie The movie object to be saved.
     * @return The movie object with updated information, including the generated movie ID.
     * @throws RuntimeException If there is an error accessing the database.
     */
    public MovieDto saveMovie(MovieDto movie) {
        DataConnection dataConnection = new DataConnection();
        try (Connection conn = dataConnection.getConnection()) {
            conn.setAutoCommit(false);
            String orderQuery = "INSERT INTO movie(`title`, `duration`, `rating`, `description`) VALUES (?, ?, ? , ?)";
            try (PreparedStatement stmt = conn.prepareStatement(orderQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, movie.getTitle());
                stmt.setLong(2, movie.getDuration());
                stmt.setFloat(3, movie.getRating());
                stmt.setString(4, movie.getDescription());
                System.out.println("SQL:" + stmt.toString());
                int affectedRows = stmt.executeUpdate();
                ResultSet keys = stmt.getGeneratedKeys();
                if(keys.next()) {
                	 movie.setId(keys.getLong(1));
                }
                conn.commit();
                System.out.println("Transaction commit...");
                return movie;
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction rollback...");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
