package com.example.netflix.mapper;

import com.example.netflix.dto.request.SeriesRequest;
import com.example.netflix.dto.response.SeriesResponse;
import com.example.netflix.entities.SeriesEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeriesMapper {

    public SeriesEntity mapMoveDtoToEntity(SeriesRequest seriesRequest);
    public List<SeriesEntity> mapMovieListDtoToEntity(List<SeriesRequest> seriesRequest);
    public SeriesResponse mapSeriesEntityToResponse(SeriesEntity seriesEntity);
    public List<SeriesResponse> mapSeriesEntityListToResponse(List<SeriesEntity> seriesEntityList);
}
