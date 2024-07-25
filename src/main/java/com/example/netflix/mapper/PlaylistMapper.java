package com.example.netflix.mapper;

import com.example.netflix.dto.request.PlaylistRequest;
import com.example.netflix.dto.response.PlaylistResponse;
import com.example.netflix.entities.PlaylistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {


    @Mapping(source = "userEntity" ,target = "userEntity.idUser")
    public PlaylistEntity mapPlaylistDtoToEntity(PlaylistRequest playlistRequest);
    @Mapping(source = "userEntity.idUser" ,target = "userEntity")
    public PlaylistResponse mapPlaylistEntityToResponse(PlaylistEntity playlistEntity);
}
