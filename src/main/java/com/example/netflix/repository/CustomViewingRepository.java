package com.example.netflix.repository;

import com.example.netflix.dto.request.FiltroMovieRequest;
import com.example.netflix.dto.request.FiltroViewingRequest;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.ViewingEntity;

import java.util.List;

public interface CustomViewingRepository {

    List<ViewingEntity> findViewingeByFiltro(FiltroViewingRequest filtroViewingRequest);
}
