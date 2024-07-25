package com.example.netflix.controllers;

import com.example.netflix.dto.request.FiltroMovieRequest;
import com.example.netflix.dto.request.MovieRequest;
import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.dto.response.VisioniMovie;
import com.example.netflix.services.MovieService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

@GetMapping("/moviePiuVisualizzato")
    public ResponseEntity<List<VisioniMovie>> moviePiuVisualizzato() {
       List<VisioniMovie> visioniMovieList=movieService.moviePiuVisualizzato();
        if (!visioniMovieList.isEmpty()) {
            return ResponseEntity.ok(visioniMovieList);
        }
        return ResponseEntity.notFound().build();
    }

@GetMapping("/moviePiuSalvati")
    public ResponseEntity<List<MovieResponse>> moviePiuSalvati() {
        List<MovieResponse> movieResponseList = movieService.moviePiuSalvati();
        if (!movieResponseList.isEmpty()) {
            return ResponseEntity.ok(movieResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ricercaMovie")
    public ResponseEntity<List<MovieResponse>> ricercaMovie(@RequestBody FiltroMovieRequest filtroMovieRequest) {
        List<MovieResponse> movieResponseList = movieService.ricercaMovie(filtroMovieRequest);
        if (!movieResponseList.isEmpty()) {
            return ResponseEntity.ok(movieResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addMovie")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest movieRequest) throws Exception {
        MovieResponse movieResponse = movieService.addMovie(movieRequest);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        throw new Exception("ERRORE: film non inserito perche esiste gia");
    }

    @DeleteMapping("/deleteMovie/{idMovie}")
    public ResponseEntity<String> deleteMovie(@PathVariable int idMovie) {
        Boolean b = movieService.deleteMovie(idMovie);
        if (b) {
            return ResponseEntity.ok("film eliminato");
        }

        return ResponseEntity.ofNullable("ERRORE: film non trovato");
    }

    @PostMapping("/updateMovie/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@RequestBody MovieRequest movieRequest, @PathVariable Integer id) throws Exception {
        MovieResponse movieResponse = movieService.updateMovie(movieRequest, id);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        throw new Exception("ERRORE:  il film non esiste");
    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<MovieResponse> ricercaMovieById(@PathVariable Integer id) throws Exception {
        MovieResponse movieResponse = movieService.ricercaMovieById(id);
        if (movieResponse != null) {
            return ResponseEntity.ok(movieResponse);
        }
        throw new Exception("ERRORE:  il film non esiste");
    }
}
