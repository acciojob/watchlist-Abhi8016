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
        if (movies.containsKey(movieName) && directors.containsKey(directorName)) {
            List<String> currentMoviesByDirector = new ArrayList<>();
            if(directors.containsKey(directorName))
                currentMoviesByDirector = movieDirectorPairs.get(directorName);
            currentMoviesByDirector.add(movieName);
            movieDirectorPairs.put(directorName, currentMoviesByDirector);
        }

    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {
        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String directorName) {

        List<String> moviesList = new ArrayList<String>();
        if(movieDirectorPairs.containsKey(directorName))
            moviesList = movieDirectorPairs.get(directorName);
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movies.keySet());
    }

    public void deleteDirectorByName(String directorName) {
        List<String> movies1 = new ArrayList<String>();
        if(movieDirectorPairs.containsKey(directorName)){
            movies1 = movieDirectorPairs.get(directorName);

            for(String movie: movies1){
                if(movies.containsKey(movie)){
                    movies.remove(movie);
                }
            }
            movieDirectorPairs.remove(directorName);
        }
        if(directors.containsKey(directorName)){
            directors.remove(directorName);
        }
    }

    public void deleteAllDirectors() {
        HashSet<String> moviesSet = new HashSet<String>();
        directors = new HashMap<>();
        for(String director: movieDirectorPairs.keySet()){
            for(String movie: movieDirectorPairs.get(director)){
                moviesSet.add(movie);
            }
        }
        for(String movie: moviesSet){
            if(movies.containsKey(movie)){
                movies.remove(movie);
            }
        }
        directors = new HashMap<>();
    }
}
