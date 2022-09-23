/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.movie.Movie;
import hr.algebra.model.Person;
import hr.algebra.model.login.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class MovieSqlRepository implements Repository {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DURATION = "Duration";
    private static final String GENRE = "Genre";
    private static final String PICTURE_PATH = "PicturePath";

    private static final String DIRECTOR_ID = "DirectorID";
    private static final String DIRECTOR_TABLE_ID = "IDDirector";
    private static final String ACTOR_ID = "ActorID";
    private static final String ACTOR_TABLE_ID = "IDActor";
    private static final String DIRECTOR_FIRST_NAME = "FirstNameDirector";
    private static final String DIRECTOR_LAST_NAME = "LastNameDirector";
    private static final String ACTOR_FIRST_NAME = "FirstNameActor";
    private static final String ACTOR_LAST_NAME = "LastNameActor";

    private static final String ID_USER = "IDUserInfo";
    private static final String USERNAME = "Username";
    private static final String USER_PASSWORD = "UserPassword";
    private static final String USER_ADMIN = "IsAdmin";

    private static final String CREATE_MOVIE = "{ CALL CreateMovie (?, ?, ?, ?, ?, ?, ?, ?, ?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?, ?, ?, ?, ?, ?, ?, ?, ?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String GET_MOVIE = "{ CALL GetMovie (?) }";
    private static final String GET_MOVIES = "{ CALL GetMovies }";
    private static final String DELETE_MOVIES = "{ CALL DeleteMovies }";

    private static final String CREATE_DIRECTOR = "{ CALL CreateDirector(?, ?, ?) }";
    private static final String GET_DIRECTORS = "{ CALL GetDirectors }";
    private static final String GET_ACTORS = "{ CALL GetActors }";
    private static final String EDIT_ACTOR = "{ CALL EditActor(?, ?, ?) }";
    private static final String EDIT_DIRECTOR = "{ CALL EditDirector(?, ?, ?) }";
    private static final String GET_ACTOR_BY_NAME = "{ CALL GetActorByName(?, ?) }";
    private static final String GET_DIRECTOR_BY_NAME = "{ CALL GetDirectorByName(?, ?) }";
    private static final String CREATE_ACTOR = "{ CALL CreateActor(?, ?, ?) }";
    private static final String CREATE_MOVIE_ACTOR = "{ CALL CreateMovieActor(?, ?, ?) }";
    private static final String DELETE_ACTOR_FROM_MOVIE_ACTOR = "{ CALL DeleteActorFromMovieActor(?) }";
    private static final String ADD_FAVORITE_ACTOR = "{ CALL AddFavoriteActor(?) }";
    private static final String ADD_FAVORITE_DIRECTOR = "{ CALL AddFavoriteDirector(?) }";
    private static final String GET_FAVORITE_ACTORS = "{ CALL GetFavoriteActors }";
    private static final String GET_FAVORITE_DIRECTORS = "{ CALL GetFavoriteDirectors }";
    private static final String REMOVE_FROM_FAVORITE_DIRECTORS = "{ CALL RemoveFromFavoriteDirectors(?) }";
    private static final String REMOVE_FROM_FAVORITE_ACTORS = "{ CALL RemoveFromFavoriteActors(?) }";

    private static final String CREATE_USER = "{ CALL CreateUser (?, ?, ?) }";
    private static final String GET_USER = "{ CALL GetUser (?) }";
    private static final String GET_USERS = "{ CALL GetUsers }";
    private static final String DELETE_USER = "{ CALL DeleteUser (?) }";
    private static final String MAKE_USER_ADMIN = "{ CALL MakeUserAdmin (?) }";

    @Override
    public int createMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOriginalTitle());
            stmt.setString(5, movie.getDuration());
            stmt.setString(6, movie.getGenre());
            stmt.setString(7, movie.getPicturePath());
            stmt.setInt(8, movie.getDirector().getId());

            stmt.registerOutParameter(9, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(9);
        }
    }

    @Override
    public int createMovieActor(Movie movie, Person actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_ACTOR)) {
            stmt.setInt(1, movie.getId());
            stmt.setInt(2, actor.getId());

            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(3);
        }
    }

    @Override
    public int createActor(Person actor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACTOR)) {
            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());

            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(3);
        }
    }

    @Override
    public int createDirector(Person director) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {
            stmt.setString(1, director.getFirstName());
            stmt.setString(2, director.getLastName());

            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(3);
        }
    }

    @Override
    public List<Movie> getMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
            Movie movie = null;
            List<Person> actors = null;
            while (rs.next()) {
                if (movie == null || rs.getInt(ID_MOVIE) != movie.getId()) {
                    actors = new ArrayList<>(); //prije je bilo null
                    if (rs.getString(ACTOR_FIRST_NAME) != null) {
                        //actors = new ArrayList<>();
                        actors.add(new Person(rs.getInt(ACTOR_ID), rs.getString(ACTOR_FIRST_NAME), rs.getString(ACTOR_LAST_NAME)));
                    }
                    movie = new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            new Person(rs.getInt(DIRECTOR_ID), rs.getString(DIRECTOR_FIRST_NAME), rs.getString(DIRECTOR_LAST_NAME)),
                            actors,
                            rs.getString(DURATION),
                            rs.getString(GENRE),
                            rs.getString(PICTURE_PATH));
                    movies.add(movie);
                } else {
                    actors.add(new Person(rs.getInt(ACTOR_ID), rs.getString(ACTOR_FIRST_NAME), rs.getString(ACTOR_LAST_NAME)));
                }

            }
        }

        return movies;
    }

    @Override
    public void deleteMovies() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIES)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public int createUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.registerOutParameter(3, Types.INTEGER);

            stmt.executeUpdate();

            return stmt.getInt(3);
        }
    }

    @Override
    public Optional<User> getUser(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USER)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(
                            rs.getInt(ID_USER),
                            rs.getString(USERNAME),
                            rs.getString(USER_PASSWORD),
                            rs.getBoolean(USER_ADMIN)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<User> getUsers() throws Exception {
        List<User> users = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USERS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(ID_USER),
                        rs.getString(USERNAME),
                        rs.getString(USER_PASSWORD),
                        rs.getBoolean(USER_ADMIN)
                ));
            }
        }
        return users;
    }

    @Override
    public void deleteUser(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_USER)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void makeUserAdmin(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(MAKE_USER_ADMIN)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Movie> getMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    List<Person> actors = new ArrayList<>();
                    Movie movie = new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_TITLE),
                            new Person(rs.getInt(DIRECTOR_ID), rs.getString(DIRECTOR_FIRST_NAME), rs.getString(DIRECTOR_LAST_NAME)),
                            actors,
                            rs.getString(DURATION),
                            rs.getString(GENRE),
                            rs.getString(PICTURE_PATH));
                    actors.add(new Person(rs.getInt(ACTOR_ID), rs.getString(ACTOR_FIRST_NAME), rs.getString(ACTOR_LAST_NAME)));

                    while (rs.next()) {
                        actors.add(new Person(rs.getInt(ACTOR_ID), rs.getString(ACTOR_FIRST_NAME), rs.getString(ACTOR_LAST_NAME)));
                    }
                    return Optional.of(movie);
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Person> getActors() throws Exception {
        List<Person> actors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ACTORS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                actors.add(new Person(
                        rs.getInt(ACTOR_TABLE_ID),
                        rs.getString(ACTOR_FIRST_NAME),
                        rs.getString(ACTOR_LAST_NAME)));
            }
        }

        return actors;
    }

    @Override
    public List<Person> getDirectors() throws Exception {
        List<Person> directors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_DIRECTORS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                directors.add(new Person(
                        rs.getInt(DIRECTOR_TABLE_ID),
                        rs.getString(DIRECTOR_FIRST_NAME),
                        rs.getString(DIRECTOR_LAST_NAME)));
            }
        }

        return directors;
    }

    @Override
    public void addFavoriteActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_FAVORITE_ACTOR)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public List<Person> getFavoriteActors() throws Exception {
        List<Person> favoriteActors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_FAVORITE_ACTORS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                favoriteActors.add(new Person(
                        rs.getInt(ACTOR_TABLE_ID),
                        rs.getString(ACTOR_FIRST_NAME),
                        rs.getString(ACTOR_LAST_NAME)));
            }
        }

        return favoriteActors;
    }

    @Override
    public void addFavoriteDirector(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_FAVORITE_DIRECTOR)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public List<Person> getFavoriteDirectors() throws Exception {
        List<Person> favoriteDirectors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_FAVORITE_DIRECTORS);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                favoriteDirectors.add(new Person(
                        rs.getInt(DIRECTOR_TABLE_ID),
                        rs.getString(DIRECTOR_FIRST_NAME),
                        rs.getString(DIRECTOR_LAST_NAME)));
            }
        }

        return favoriteDirectors;
    }

    @Override
    public void removeFromFavoriteDirectors(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REMOVE_FROM_FAVORITE_DIRECTORS)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void removeFromFavoriteActors(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(REMOVE_FROM_FAVORITE_ACTORS)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void editActor(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_ACTOR)) {
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());

            stmt.executeUpdate();
        }
    }

    @Override
    public void editDirector(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(EDIT_DIRECTOR)) {
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getLastName());

            stmt.executeUpdate();
        }
    }

    @Override
    public boolean checkIfActorNameTaken(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_ACTOR_BY_NAME)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkIfDirectorNameTaken(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_DIRECTOR_BY_NAME)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void deleteMovie(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteActorFromMovieActor(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTOR_FROM_MOVIE_ACTOR)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(4, movie.getDescription());
            stmt.setString(5, movie.getOriginalTitle());
            stmt.setString(6, movie.getDuration());
            stmt.setString(7, movie.getGenre());
            stmt.setString(8, movie.getPicturePath());
            stmt.setInt(9, movie.getDirector().getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public boolean checkIfUserExists(String username) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USER)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }

}
