package com.example.netflix.mapper;

import com.example.netflix.dto.request.SeriesRequest;
import com.example.netflix.dto.request.UserRequest;
import com.example.netflix.dto.response.SeriesResponse;
import com.example.netflix.dto.response.UserResponse;
import com.example.netflix.entities.SeriesEntity;
import com.example.netflix.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserEntity mapMoveDtoToEntity(UserRequest userRequest);
    public UserResponse mapMovieEntityToResponse(UserEntity userEntity);
}
