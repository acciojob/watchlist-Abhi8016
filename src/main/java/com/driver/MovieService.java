package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public ResponseEntity<String> addMovie(Movie movie) {
        movieRepository.addMovie(movie);
        return new ResponseEntity<>("Successfully added movie: " + movie.getName(), HttpStatus.OK);
    }

    public ResponseEntity<String> addDirector(Director director) {
        movieRepository.addDirector(director);
        return new ResponseEntity<>("Successfully added director: " + director.getName(), HttpStatus.OK);
    }

    public ResponseEntity<String> addMovieDirectorPair(String movieName, String directorName) {
        movieRepository.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>("Successfully added movie-director pair: " + movieName + " - " + directorName, HttpStatus.OK);
    }

    public ResponseEntity<Movie> getMovieByName(String movieName) {
        return new ResponseEntity<>(movieRepository.getMovieByName(movieName), HttpStatus.OK);
    }

    public ResponseEntity<Director> getDirectorByName(String directorName) {
        return new ResponseEntity<>(movieRepository.getDirectorByName(directorName), HttpStatus.OK);
    }

    public ResponseEntity<List<String>> getMoviesByDirectorName(String directorName) {
        return new ResponseEntity<>(movieRepository.getMoviesByDirectorName(directorName), HttpStatus.OK);
    }

    public ResponseEntity<List<String>> findAllMovies() {
        return new ResponseEntity<>(movieRepository.findAllMovies(), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteDirectorByName(String directorName) {
        movieRepository.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Successfully deleted director: " + directorName, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
        return new ResponseEntity<>("Successfully deleted all directors", HttpStatus.OK);
    }


}
