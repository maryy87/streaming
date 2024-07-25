package com.example.netflix.repository;

import com.example.netflix.dto.request.FiltroMovieRequest;
import com.example.netflix.dto.request.FiltroSeriesRequest;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.SeriesEntity;

import java.util.List;

public interface CustomContenutoDao {

    List<MovieEntity> findMovieByFiltro(FiltroMovieRequest filtroMovieRequest);
    List<SeriesEntity> findSeriesByFiltro(FiltroSeriesRequest filtroSeriesRequest);
}
