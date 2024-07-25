package com.example.netflix.mapper;

import com.example.netflix.dto.request.MovieRequest;
import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.entities.MovieEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel= "spring")
public interface MovieMapper {


    public MovieEntity mapMoveDtoToEntity(MovieRequest movieRequest);
    public MovieResponse mapMovieEntityToResponse(MovieEntity movieEntity);
    public List<MovieEntity> mapMovieDtoListToEntity(List<MovieRequest> movieRequestList);
    public List<MovieResponse> mapMovieEntityListToDto(List<MovieEntity> movieEntityList);
}
