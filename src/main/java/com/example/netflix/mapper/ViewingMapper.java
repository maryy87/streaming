package com.example.netflix.mapper;


import com.example.netflix.dto.request.ViewingRequest;
import com.example.netflix.dto.response.UserResponse;
import com.example.netflix.dto.response.ViewingResponse;
import com.example.netflix.entities.UserEntity;
import com.example.netflix.entities.ViewingEntity;
import com.example.netflix.util.Stato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ViewingMapper {


    @Mapping(source = "movieEntity" ,target = "movieEntity.idMovie")
    @Mapping(source = "seriesEntity" ,target = "seriesEntity.idSeries")
    @Mapping(source = "userEntity" ,target = "userEntity.idUser")
    public ViewingEntity mapMoveDtoToEntity(ViewingRequest viewingRequest);

    public List<ViewingEntity> mapViewingLIstRequestToEntity(List<ViewingRequest> viewingRequestList);

    @Mapping(source = "userEntity.idUser" ,target = "userEntity")
    @Mapping(source = "movieEntity.idMovie" ,target = "movieEntity")
    @Mapping(source = "seriesEntity.idSeries" ,target = "seriesEntity")
    public ViewingResponse mapMovieEntityToResponse(ViewingEntity viewingEntity);
    public List<ViewingResponse> mapViewingEntityListToResponse(List<ViewingEntity> viewingEntityList);


}
