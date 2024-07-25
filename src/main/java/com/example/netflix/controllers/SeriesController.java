package com.example.netflix.controllers;

import com.example.netflix.dto.request.FiltroSeriesRequest;
import com.example.netflix.dto.request.SeriesRequest;
import com.example.netflix.dto.response.SeriesResponse;
import com.example.netflix.dto.response.VisioniSeries;
import com.example.netflix.entities.SeriesEntity;
import com.example.netflix.mapper.SeriesMapper;
import com.example.netflix.repository.SeriesRepository;
import com.example.netflix.services.SeriesService;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;


    @GetMapping("/seriesPiuVisualizzato")
    public ResponseEntity<List<VisioniSeries>> seriesPiuVisualizzato() {
        List<VisioniSeries> visioniSeriesList =seriesService.seriesPiuVisualizzato();
        if (!visioniSeriesList.isEmpty()) {
            return ResponseEntity.ok(visioniSeriesList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/seriesPiuSalvato")
    public ResponseEntity<List<SeriesResponse>> seriesPiuSalvato() {
        List<SeriesResponse> seriesResponseList = seriesService.seriesPiuSalvato();
        if (!seriesResponseList.isEmpty()) {
            return ResponseEntity.ok(seriesResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/topDieciSeriesPiuSalvati")
    public ResponseEntity<List<SeriesResponse>> topDieciSeriesPiuSalvati() {
        List<SeriesResponse> seriesResponseList = seriesService.topDieciSeriesPiuSalvati();
        if (!seriesResponseList.isEmpty()) {
            return ResponseEntity.ok(seriesResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ricercaSeries")
    public ResponseEntity<List<SeriesResponse>> ricercaSeries(@RequestBody FiltroSeriesRequest filtroSeriesRequest) {
        List<SeriesResponse> seriesResponseList = seriesService.ricercaSeries(filtroSeriesRequest);
        if (!seriesResponseList.isEmpty()) {
            return ResponseEntity.ok(seriesResponseList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addSeries")
    public ResponseEntity<SeriesResponse> addSeries(@RequestBody SeriesRequest seriesRequest) throws Exception {
        SeriesResponse seriesResponse = seriesService.addSeries(seriesRequest);
        if (seriesResponse != null) {
            return ResponseEntity.ok(seriesResponse);
        }
        throw new Exception("ERRORE: Series non inserito perche esiste gia");
    }

    @DeleteMapping("/deleteSeries/{id}")
    public ResponseEntity<String> deleteSeries(@PathVariable int id) {
        Boolean b = seriesService.deleteSeries(id);
        if (b) {
            return ResponseEntity.ok("series eliminata");
        }
        return ResponseEntity.ofNullable(" la series non esiste");
    }

    @PostMapping("/updateSeries/{id}")
    public ResponseEntity<SeriesResponse> updateSeries(@RequestBody SeriesRequest seriesRequest, @PathVariable int id) throws Exception {
        SeriesResponse seriesResponse = seriesService.updateSeries(seriesRequest, id);
        if (seriesResponse != null) {
            return ResponseEntity.ok(seriesResponse);
        }
        throw new Exception("ERRORE: la series non esiste");
    }

    @GetMapping("/getSeries/{id}")
    public ResponseEntity<SeriesResponse> getSeries(@PathVariable int id) throws Exception {
        SeriesResponse seriesResponse = seriesService.getSeries(id);
        if (seriesResponse != null) {
            return ResponseEntity.ok(seriesResponse);
        }
        throw new Exception("ERRORE: la series non esiste");
    }

}
