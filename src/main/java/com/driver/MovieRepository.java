package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movies;
    private HashMap<String, Director> directors;
    private HashMap<String, List<String>> movieDirectorPair;

    public MovieRepository(){
        this.movies = new HashMap<String, Movie>();
        this.directors = new HashMap<String, Director>();
        this.movieDirectorPair = new HashMap<String, List<String>>();
    }

    public void addMovie(Movie movie){
        movies.put(movie.getName(), movie);
    }
    public void addDirector(Director director){
        directors.put(director.getName(), director);
    }

    public void addMovieDirectorPair(String movie, String director){
        if(movies.containsKey(movie) && directors.containsKey(director)){

            List<String> recentMovie = new ArrayList<>();

            if(movieDirectorPair.containsKey(director)) {

                recentMovie = movieDirectorPair.get(director);
            }

            recentMovie.add(movie);
            movieDirectorPair.put(director, recentMovie);
        }
    }

    public Movie getMovieByName (String movie){
        return movies.get(movie);
    }
    public Director getDirectorByName(String director){
        return directors.get(director);
    }

    public List<String> getMoviesByDirectorName(String director){
        List<String> list = new ArrayList<>();
        if(movieDirectorPair.containsKey(director)){
            list = movieDirectorPair.get(director);
        }
        return list;
    }

    public List<String> findAllMovies(){
        return new ArrayList<>(movies.keySet());
    }


    public void deleteDirectorByName(String director){
        List<String> movie = new ArrayList<>();
        if(movieDirectorPair.containsKey(director)){
            movie = movieDirectorPair.get(director);
            for(String m : movie){
                if(movies.containsKey(m)){
                    movies.remove(m);
                }
            }
            movieDirectorPair.remove(director);
        }
        if(directors.containsKey(director)){
            directors.remove(director);
        }
    }

    public void deleteAllDirectors(){
        HashSet<String> removalMovies = new HashSet<>();

        for(String director: movieDirectorPair.keySet()){
            for(String movie: movieDirectorPair.get(director)){
                removalMovies.add(movie);
            }
        }
        for(String movie: removalMovies){
            if(movies.containsKey(movie)){
                movies.remove(movie);
            }
        }
    }
}
