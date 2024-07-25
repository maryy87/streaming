package com.example.netflix.services;

import com.example.netflix.dto.request.FiltroSeriesRequest;
import com.example.netflix.dto.request.SeriesRequest;
import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.dto.response.SeriesResponse;
import com.example.netflix.dto.response.VisioniSeries;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.SeriesEntity;
import com.example.netflix.mapper.SeriesMapper;
import com.example.netflix.repository.CustomContenutoDao;
import com.example.netflix.repository.SeriesRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private SeriesMapper seriesMapper;
    @Autowired
    private CustomContenutoDao customContenutoDao;

    public List<VisioniSeries> seriesPiuVisualizzato() {
        List<Tuple> tupleList = seriesRepository.seriesPiuVisualizzato();
        Tuple[] array = tupleList.toArray(new Tuple[0]);
        Long count = array[0].get("c", Long.class);
        List<VisioniSeries> visioniSeriesList = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            if (tuple.get("c", Long.class) == count) {
                VisioniSeries visioniSeries = new VisioniSeries();
                visioniSeries.setTitolo(tuple.get("titolo", String.class));
                visioniSeries.setGenere(tuple.get("genere", String.class));
                Timestamp timestamp = tuple.get("data_di_visione", Timestamp.class);
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                visioniSeries.setData_di_Visione(localDateTime);
                Timestamp timestamp1 = tuple.get("data_di_fine", Timestamp.class);
                LocalDateTime localDateTime1 = timestamp.toLocalDateTime();
                visioniSeries.setData_di_fine(localDateTime1);
                visioniSeries.setNumero_di_stagioni(tuple.get("numero_di_stagioni", Integer.class));
                visioniSeries.setNumero_di_episodi(tuple.get("numero_di_episodi", Integer.class));
                visioniSeriesList.add(visioniSeries);
            }
        }
        return visioniSeriesList;
    }

    public List<SeriesResponse> seriesPiuSalvato() {
        List<Tuple> tupleList = seriesRepository.seriesPiuSalvato();
        List<SeriesResponse> seriesResponseList = new ArrayList<>();
        Tuple[] array = tupleList.toArray(new Tuple[0]);
        Long count = array[0].get("c", Long.class);
        for (Tuple tuple : tupleList) {
            if (tuple.get("c", Long.class) == count) {
                SeriesResponse seriesResponse = new SeriesResponse();
                seriesResponse.setIdSeries(tuple.get("id_series", Integer.class));
                seriesResponse.setTitolo(tuple.get("titolo", String.class));
                seriesResponse.setGenere(tuple.get("genere", String.class));
                seriesResponse.setNumeroDiEpisodi(tuple.get("numero_di_episodi", Integer.class));
                seriesResponse.setNumeroDiStagioni(tuple.get("numero_di_stagioni", Integer.class));
                seriesResponseList.add(seriesResponse);
            }
        }
        return seriesResponseList;
    }

    public List<SeriesResponse> topDieciSeriesPiuSalvati() {
        List<SeriesEntity> seriesEntityList = seriesRepository.topDieciSeriesPiuSalvati();
        if (!seriesEntityList.isEmpty()) {
            return seriesMapper.mapSeriesEntityListToResponse(seriesEntityList);
        }
        return null;
    }

    public List<SeriesResponse> ricercaSeries(FiltroSeriesRequest filtroSeriesRequest) {
        if (filtroSeriesRequest != null) {
            List<SeriesResponse> seriesResponseList = seriesMapper.mapSeriesEntityListToResponse(customContenutoDao.findSeriesByFiltro(filtroSeriesRequest));
            return seriesResponseList;

        }
        return new ArrayList<>();
    }

    public SeriesResponse addSeries(SeriesRequest seriesRequest) {
        SeriesEntity seriesEntity = seriesMapper.mapMoveDtoToEntity(seriesRequest);
        SeriesEntity seriesEntity1 = seriesRepository.save(seriesEntity);
        return seriesMapper.mapSeriesEntityToResponse(seriesEntity1);
    }

    public Boolean deleteSeries(int id) {
        Optional<SeriesEntity> seriesEntity = seriesRepository.findById(id);
        if (seriesEntity.isPresent()) {
            seriesRepository.deleteById(id);
            Optional<SeriesEntity> seriesEntity1 = seriesRepository.findById(id);
            if (seriesEntity1.isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public SeriesResponse updateSeries(SeriesRequest seriesRequest, int id) {
        SeriesEntity seriesEntity = seriesMapper.mapMoveDtoToEntity(seriesRequest);
        if (seriesEntity != null) {
            seriesEntity.setIdSeries(id);
            SeriesEntity seriesEntity1 = seriesRepository.save(seriesEntity);
            SeriesResponse seriesResponse = seriesMapper.mapSeriesEntityToResponse(seriesEntity1);
            return seriesResponse;
        }
        return null;
    }

    public SeriesResponse getSeries(int id) {
        Optional<SeriesEntity> seriesEntity = seriesRepository.findById(id);
        if (seriesEntity.isPresent()) {
            return seriesMapper.mapSeriesEntityToResponse(seriesEntity.get());
        }
        return null;
    }
}


