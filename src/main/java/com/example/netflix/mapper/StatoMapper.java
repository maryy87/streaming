package com.example.netflix.mapper;

import com.example.netflix.util.Stato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatoMapper {
    StatoMapper INSTANCE = Mappers.getMapper(StatoMapper.class);
    String toString(Stato stato);
}
