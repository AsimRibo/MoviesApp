/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.movie.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.login.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author asim2
 */
public interface Repository {

    int createMovie(Movie movie) throws Exception;

    int createMovieActor(Movie movie, Person actor) throws Exception;

    int createActor(Person actor) throws Exception;

    int createDirector(Person director) throws Exception;

    public Optional<Movie> getMovie(int id) throws Exception;

    List<Movie> getMovies() throws Exception;

    void deleteMovies() throws Exception;
    
    void deleteMovie(int id) throws Exception;
    
    void updateMovie(Movie movie) throws Exception;
    
    void deleteActorFromMovieActor(int id) throws Exception;

    int createUser(User user) throws Exception;

    Optional<User> getUser(String username) throws Exception;
    
    boolean checkIfUserExists(String username) throws Exception;

    List<User> getUsers() throws Exception;

    void deleteUser(int id) throws Exception;

    void makeUserAdmin(int id) throws Exception;

    List<Person> getActors() throws Exception;

    List<Person> getDirectors() throws Exception;

    void addFavoriteActor(int id) throws Exception;

    List<Person> getFavoriteActors() throws Exception;

    void addFavoriteDirector(int id) throws Exception;
    
    void removeFromFavoriteDirectors(int id) throws Exception;
    
    void removeFromFavoriteActors(int id) throws Exception;

    List<Person> getFavoriteDirectors() throws Exception;
    
    void editActor(Person person) throws Exception;
    
    void editDirector(Person person) throws Exception;
    
    boolean checkIfActorNameTaken(Person person) throws Exception;
    
    boolean checkIfDirectorNameTaken(Person person) throws Exception;

}
