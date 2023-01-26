package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;


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

    public List<String> getMoviesByDirectorName(String directorName) {
        return movieDirectorPairs.getOrDefault(directorName, new ArrayList<>());
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        directors.remove(directorName);
        movieDirectorPairs.remove(directorName);
    }

    public void deleteAllDirectors() {
        directors.clear();
        movieDirectorPairs.clear();
    }
}
