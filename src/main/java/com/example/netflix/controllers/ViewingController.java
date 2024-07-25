package com.example.netflix.controllers;

import com.example.netflix.dto.request.FiltroViewingRequest;
import com.example.netflix.dto.request.ViewingRequest;
import com.example.netflix.dto.response.ViewingResponse;
import com.example.netflix.dto.response.VisioniMovie;
import com.example.netflix.dto.response.VisioniSeries;
import com.example.netflix.services.ViewingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/viewing")
public class ViewingController {

    @Autowired
    private ViewingService viewingService;

@GetMapping("/contenutoPiuVisualizzato")
    public ResponseEntity<List> contenutoPiuVisualizzato() {
        List list = viewingService.contenutoPiuVisualizzato();
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/addMovieViewing/{idMovie}/{idUser}")
    public ResponseEntity<String> addMovieViewing(@PathVariable int idMovie, @PathVariable int idUser) {
        Boolean b = viewingService.addMovieViewing(idMovie, idUser);
        if (b) {
            return ResponseEntity.ok("movie aggiunto con sucesso");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/addSeriesViewing/{idSeries}/{idUser}")
    public ResponseEntity<String> addSeriesViewing(@PathVariable int idSeries, @PathVariable int idUser) {
        Boolean b = viewingService.addSeriesViewing(idSeries, idUser);
        if (b) {
            return ResponseEntity.ok("series aggiunta con sucesso");
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/ricercaViewing")
    public ResponseEntity<List<ViewingResponse>> ricercaViewing(@RequestBody FiltroViewingRequest filtroViewingRequest) {
        List<ViewingResponse> viewingResponseList = viewingService.ricercaViewing(filtroViewingRequest);
        if (!viewingResponseList.isEmpty()) {
            return ResponseEntity.ok(viewingResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/visioniStoricheUtente/{id_user}")
    public ResponseEntity<List> visioniStoricheUtente(@PathVariable int id_user) {
        List list = viewingService.visioniStoricheUtente(id_user);
        if (!list.isEmpty()) {
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/visioniStoricheSeriesUtente/{id_user}")
    public ResponseEntity<List<VisioniSeries>> visioniStoricheSeriesUtente(@PathVariable int id_user) {
        List<VisioniSeries> visioniSeriesList = viewingService.visioniStoricheSeriesUtente(id_user);
        if (!visioniSeriesList.isEmpty()) {
            return ResponseEntity.ok(visioniSeriesList);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/visioniStoricheMovieUtente/{id_user}")
    public ResponseEntity<List<VisioniMovie>> visioniStoricheMovieUtente(@PathVariable int id_user) {
        List<VisioniMovie> visioniMovieList = viewingService.visioniStoricheMovieUtente(id_user);
        if (!visioniMovieList.isEmpty()) {
            return ResponseEntity.ok(visioniMovieList);
        }
        return ResponseEntity.notFound().build();
    }

//    @PostMapping("/addViewing")
//    public ResponseEntity addViewing(@RequestBody ViewingRequest viewingRequest) throws Exception {
//        ViewingResponse viewingResponse = viewingService.addsERIESViewing(viewingRequest);
//        if (viewingResponse != null) {
//            return ResponseEntity.ok(viewingResponse);
//        }
//        throw new Exception("ERRORE:  viewing non inserito");
//    }

    @DeleteMapping("/deleteViewing/{id}")
    public ResponseEntity<String> deleteViewing(@PathVariable int id) {
        Boolean b = viewingService.deleteViewing(id);
        if (b) {
            return ResponseEntity.ok("viewing eliminato");
        }
        return ResponseEntity.ofNullable("il viewing non esiste");
    }

    @PostMapping("/updateViewing/{id}")
    public ResponseEntity updateViewing(@RequestBody ViewingRequest viewingRequest, @PathVariable int id) throws Exception {
        ViewingResponse viewingResponse = viewingService.updateViewing(viewingRequest, id);
        if (viewingResponse != null) {
            return ResponseEntity.ok(viewingResponse);
        }
        throw new Exception("ERRORE:  viewing non esistente");
    }

    @GetMapping("/getViewing/{id}")
    public ResponseEntity getViewing(@PathVariable int id) throws Exception {
        ViewingResponse viewingResponse = viewingService.getViewing(id);
        if (viewingResponse != null) {
            return ResponseEntity.ok(viewingResponse);
        }
        throw new Exception("ERRORE:  viewing non esistente");
    }
}

