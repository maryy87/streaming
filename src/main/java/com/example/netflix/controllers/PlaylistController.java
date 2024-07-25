package com.example.netflix.controllers;

import com.example.netflix.dto.request.PlaylistRequest;
import com.example.netflix.dto.request.SeriesRequest;
import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.dto.response.PlaylistResponse;
import com.example.netflix.dto.response.SeriesResponse;
import com.example.netflix.entities.ContenutiSalvati;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.PlaylistEntity;
import com.example.netflix.entities.SeriesEntity;
import com.example.netflix.mapper.PlaylistMapper;
import com.example.netflix.repository.PlaylistRepository;
import com.example.netflix.services.PlaylistService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

@GetMapping("/contenutoPiuSalvato")
    public ResponseEntity<List> contenutoPiuSalvato() {
     List list=playlistService.contenutoPiuSalvato();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

@PostMapping("/addMovieInPlaylist/{idPlaylist}/{idMovie}")
    public ResponseEntity<String> addMovieInPlaylist(@PathVariable int idPlaylist,@PathVariable int idMovie) {
        Boolean b=playlistService.addMovieInPlaylist(idPlaylist,idMovie);
        if (b) {
            return  ResponseEntity.ok("film aggiunto con successo");
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addSeriesInPlaylist/{idPlaylist}/{seriesRequest}")
    public ResponseEntity<String> addSeriesInPlaylist(@PathVariable int idPlaylist, @PathVariable int seriesRequest) {
       Boolean b= playlistService.addSeriesInPlaylist(idPlaylist, seriesRequest);
        if (b) {
            return ResponseEntity.ok("serie aggiunta con successo");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/topDieciFilmSalvati")
    public ResponseEntity<List<MovieResponse>> topDieciFilmSalvati() {
        List<MovieResponse> movieResponseList = playlistService.topDieciFilmSalvati();
        if (!movieResponseList.isEmpty()) {
            return ResponseEntity.ok(movieResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addPlaylist")
    public ResponseEntity addPlaylist(@RequestBody PlaylistRequest playlistRequest) throws Exception {
        PlaylistResponse playlistResponse = playlistService.addPlaylist(playlistRequest);
        if (playlistResponse != null) {
            return ResponseEntity.ok(playlistResponse);
        }
        throw new Exception("ERRORE:  playlist non inserito");

    }

    @DeleteMapping("/deleteplaylist/{id}")
    public ResponseEntity<String> deleteplaylist(@PathVariable int id) {
        Boolean b = playlistService.deleteplaylist(id);
        if (b) {
            return ResponseEntity.ok("playlist eliminato");
        }
        return ResponseEntity.ofNullable("il playlist non esiste");
    }

    @PostMapping("/updatePlaylist/{id}")
    public ResponseEntity updatePlaylist(@RequestBody PlaylistRequest playlistRequest, @PathVariable int id) throws Exception {
        PlaylistResponse playlistResponse = playlistService.updatePlaylist(playlistRequest, id);
        if (playlistResponse != null) {
            return ResponseEntity.ok(playlistResponse);
        }
        throw new Exception("ERRORE: il playlist non esiste");
    }

    @GetMapping("/getPlaylist/{id}")
    public ResponseEntity getPlaylist(@PathVariable int id) throws Exception {
        PlaylistResponse playlistResponse = playlistService.getPlaylist(id);
        if (playlistResponse != null) {
            return ResponseEntity.ok(playlistResponse);
        }
        throw new Exception("ERRORE: il playlist non esiste");
    }
}

