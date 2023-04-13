package com.example.movies_manager.dao;

import com.example.movies_manager.entity.Actor;
import com.example.movies_manager.entity.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ActorDAO class represents a data access object for managing actors in the system.
 * It provides methods for CRUD operations on actor objects.
 */
public class ActorDao {

    /**
     * Retrieves a list of actors from the system based on the title of a movie.
     *
     * @param title The title of the movie for which to retrieve actors.
     * @return A list of actors who have participated in the movie with the given title.
     *         An empty list if no actors are found.
     */
    public List<Actor> getActorsByMovieId(Long movieId) {
        List<Actor> list = new ArrayList<>();
        DataConnection db = new DataConnection();
        Connection cnn = db.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select actor.* from actor inner join link_movie_actor " +
                         "on actor.id = link_movie_actor.id_actor " +
                         "where link_movie_actor.id_movie = ?";
            statement = cnn.prepareStatement(sql);
            statement.setLong(1, movieId);
            System.out.println("SQL QUERY:" + statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                Integer age = rs.getInt(4);
                String nationality = rs.getString(5);
                Actor actor = new Actor(id, name, lastName, age, nationality);
                list.add(actor);
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
     * Retrieves a list of actors from the system based on the given name.
     *
     * @param name The name of the actor or a part of the name for which to retrieve actors.
     * @return A list of actors whose name contains the given name.
     *         An empty list if no actors are found.
     */
    public List<Actor> getActorsByName(String name) {
        List<Actor> list = new ArrayList<>();
        DataConnection db = new DataConnection();
        Connection cnn = db.getConnection();
        PreparedStatement statement = null;
        try {
            String sql = "select * from actor where name like ?";
            statement = cnn.prepareStatement(sql);
            if(name == null) {
                name = "";
            }
            String key = "%" + name + "%";
            statement.setString(1, key);
            System.out.println("SQL QUERY:" + statement);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong(1);
                String nameInDb = rs.getString(2);
                String lastName = rs.getString(3);
                Integer age = rs.getInt(4);
                String nationality = rs.getString(5);
                Actor actor = new Actor(id, nameInDb, lastName, age, nationality);
                list.add(actor);
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
     * Creates a new actor in the system.
     *
     * @param actor The actor object to be created.
     * @return The ID of the newly created actor.
     */
    public Actor saveActor(Actor actor) {
        DataConnection dataConnection = new DataConnection();
        try (Connection conn = dataConnection.getConnection()) {
            conn.setAutoCommit(false);
            String orderQuery = "INSERT INTO actor(`name`, `last_name`, `age`, `nationality`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(orderQuery,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, actor.getName());
                stmt.setString(2, actor.getLastName());
                stmt.setInt(3, actor.getAge());
                stmt.setString(4, actor.getNationality());
                System.out.println("SQL:" + stmt.toString());
                stmt.executeUpdate();
                ResultSet keys = stmt.getGeneratedKeys();
                if(keys.next()) {
                    actor.setId(keys.getLong(1));
                }
                conn.commit();
                System.out.println("Transaction commit...");
                return actor;
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
