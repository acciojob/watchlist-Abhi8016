package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private Map<String, Movie> movies = new HashMap<>();
    private Map<String, Director> directors = new HashMap<>();
    private Map<String, List<String>> movieDirectorPairs = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if (!movies.containsKey(movieName) || !directors.containsKey(directorName)) {
            return;
        }

        if (!movieDirectorPairs.containsKey(directorName)) {
            movieDirectorPairs.put(directorName, new ArrayList<>());
        }

        movieDirectorPairs.get(directorName).add(movieName);
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        return movieDirectorPairs.get(director);
    }

    public List<String> getAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String name) {
        if (!directors.containsKey(name)) {
            return;
        }
        directors.remove(name);
        movieDirectorPairs.remove(name);
    }

    public void deleteAllDirectors() {
        directors.clear();
        movieDirectorPairs.clear();
    }
}