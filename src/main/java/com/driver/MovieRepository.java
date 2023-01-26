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

    public List<String> getMoviesByDirectorName(String director){
        List<String> movies = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : movieDirectorPairs.entrySet()){
            if(entry.getValue().equals(director)){
                movies.add(entry.getKey());
            }
        }
        return movies;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String name){
        directors.remove(name);
        List<String> moviesToRemove = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : movieDirectorPairs.entrySet()){
            if(entry.getValue().equals(name)){
                moviesToRemove.add(entry.getKey());
            }
        }
        for(String movie : moviesToRemove){
            movies.remove(movie);
            movieDirectorPairs.remove(movie);
        }
    }

    public void deleteAllDirectors() {
        directors.clear();
        //movies.clear();
        movieDirectorPairs.clear();
    }

}