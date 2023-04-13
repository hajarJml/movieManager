package com.example.movies_manager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The LinkMovieActorDao class represents a data access object for managing the relationship
 * between movies and actors in the system.
 * It provides methods for linking movies and actors together.
 */
public class LinkMovieActorDao {

    /**
     * Links a movie and an actor together in the system.
     *
     * @param movieId The ID of the movie to be linked.
     * @param actorId The ID of the actor to be linked.
     * @throws RuntimeException If there is an error accessing the database.
     */
    public void linkMovieAndActor(Long movieId, Long actorId) {
        DataConnection dataConnection = new DataConnection();
        try (Connection conn = dataConnection.getConnection()) {
            conn.setAutoCommit(false);
            String orderQuery = "INSERT INTO link_movie_actor(`id_actor`, `id_movie`) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(orderQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setLong(1, actorId);
                stmt.setLong(2, movieId);
                System.out.println("SQL:" + stmt.toString());
                stmt.executeUpdate();
                conn.commit();
                System.out.println("Transaction commit...");
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
