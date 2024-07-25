package com.example.netflix.services;

import com.example.netflix.dto.request.FiltroViewingRequest;
import com.example.netflix.dto.request.ViewingRequest;
import com.example.netflix.dto.response.ViewingResponse;
import com.example.netflix.dto.response.VisioniMovie;
import com.example.netflix.dto.response.VisioniSeries;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.SeriesEntity;
import com.example.netflix.entities.UserEntity;
import com.example.netflix.entities.ViewingEntity;
import com.example.netflix.mapper.MovieMapper;
import com.example.netflix.mapper.ViewingMapper;
import com.example.netflix.repository.CustomViewingRepository;
import com.example.netflix.repository.MovieRepository;
import com.example.netflix.repository.SeriesRepository;
import com.example.netflix.repository.ViewingRepository;
import com.example.netflix.util.Stato;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewingService {

    @Autowired
    private ViewingRepository viewingRepository;
    @Autowired
    private ViewingMapper viewingMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private CustomViewingRepository customViewingRepository;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private SeriesService seriesService;

    public List contenutoPiuVisualizzato() {
        List<Tuple> tupleSeriesList = seriesRepository.seriesPiuVisualizzato();
        Tuple[] array = tupleSeriesList.toArray(new Tuple[0]);
        Long countSeries = array[0].get("c", Long.class);

        List<Tuple> tupleMovieList = movieRepository.moviePiuVisualizzato();
        Tuple[] array1 = tupleMovieList.toArray(new Tuple[0]);
        Long countMovie = array1[0].get("c", Long.class);
        List list = new ArrayList();
        if (countMovie > countSeries) {
            List<VisioniMovie> visioniMovieList = movieService.moviePiuVisualizzato();
            list.addAll(visioniMovieList);
            return list;
        }
        List<VisioniSeries> visioniSeriesList=seriesService.seriesPiuVisualizzato();
        list.addAll(visioniSeriesList);
        return list;
    }

    public List<ViewingResponse> ricercaViewing(FiltroViewingRequest filtroViewingRequest) {
        if (filtroViewingRequest != null) {
            List<ViewingEntity> viewingEntityList = customViewingRepository.findViewingeByFiltro(filtroViewingRequest);
            List<ViewingResponse> viewingResponseList = viewingMapper.mapViewingEntityListToResponse(viewingEntityList);
            return viewingResponseList;
        }
        return new ArrayList<>();
    }

    public List visioniStoricheUtente(int id_user) {
        List<VisioniMovie> visioniMovieList = visioniStoricheMovieUtente(id_user);
        List<VisioniSeries> visioniSeriesList = visioniStoricheSeriesUtente(id_user);
        if (!visioniSeriesList.isEmpty() || !visioniMovieList.isEmpty()) {
            List list = new ArrayList();
            list.addAll(visioniMovieList);
            list.addAll(visioniSeriesList);
            return list;
        }
        return new ArrayList<>();
    }

    public List<VisioniSeries> visioniStoricheSeriesUtente(int id_user) {
        List<Tuple> tupleList = viewingRepository.visioniStoricheSeriesUtente(id_user);
        if (tupleList != null && !tupleList.isEmpty()) {
            List<VisioniSeries> visioniSeriesList = new ArrayList<>();
            for (Tuple tuple : tupleList) {
                VisioniSeries visioniSeries = new VisioniSeries();
                visioniSeries.setTitolo(tuple.get("titolo", String.class));
                visioniSeries.setGenere(tuple.get("genere", String.class));
                visioniSeries.setNumero_di_stagioni(tuple.get("numero_di_stagioni", Integer.class));
                visioniSeries.setNumero_di_episodi(tuple.get("numero_di_episodi", Integer.class));
                Timestamp timestamp = tuple.get("data_di_Visione", Timestamp.class);
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                visioniSeries.setData_di_Visione(localDateTime);
                Timestamp timestamp1 = tuple.get("data_di_fine", Timestamp.class);
                LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                visioniSeries.setData_di_fine(localDateTime1);
                visioniSeriesList.add(visioniSeries);
            }
            return visioniSeriesList;
        }
        return new ArrayList<>();
    }

    public List<VisioniMovie> visioniStoricheMovieUtente(int id_user) {
        List<Tuple> tupleList = viewingRepository.visioniStoricheMovieUtente(id_user);
        if (tupleList != null && !tupleList.isEmpty()) {
            List<VisioniMovie> visioniMovieUtentesList = new ArrayList<>();
            for (Tuple tuple : tupleList) {
                VisioniMovie visioniMovie = new VisioniMovie();
                visioniMovie.setTitolo(tuple.get("titolo", String.class));
                visioniMovie.setGenere(tuple.get("genere", String.class));
                visioniMovie.setDurata(tuple.get("durata", Integer.class));
                Timestamp timestamp = tuple.get("data_di_visione", Timestamp.class);
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                visioniMovie.setDataDiVisione(localDateTime);
                Timestamp timestamp1 = tuple.get("data_di_fine", Timestamp.class);
                LocalDateTime localDateTime1 = timestamp1.toLocalDateTime();
                visioniMovie.setDataDiFine(localDateTime1);
                Timestamp timestamp2 = tuple.get("data_di_pubblicazione", Timestamp.class);
                LocalDateTime localDateTime2 = timestamp1.toLocalDateTime();
                visioniMovie.setDataDiPubblicazione(localDateTime2);
                visioniMovieUtentesList.add(visioniMovie);
            }
            return visioniMovieUtentesList;
        }
        return new ArrayList<>();
    }


    public Boolean addMovieViewing(int idMovie, int idUser) {
        if (idMovie != 0 && idUser != 0) {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setIdMovie(idMovie);
            UserEntity userEntity = new UserEntity();
            userEntity.setIdUser(idUser);
            ViewingEntity viewingEntity1 = new ViewingEntity(0, userEntity, LocalDateTime.now(), null, Stato.ATTIVA.name(), movieEntity, null);
            ViewingEntity viewingEntity2 = viewingRepository.save(viewingEntity1);
            return true;
        }
        return false;
    }


    public Boolean addSeriesViewing(int idSeries, int idUser) {
        if (idSeries != 0 && idUser != 0) {
            SeriesEntity seriesEntity = new SeriesEntity();
            seriesEntity.setIdSeries(idSeries);
            UserEntity userEntity = new UserEntity();
            userEntity.setIdUser(idUser);
            ViewingEntity viewingEntity1 = new ViewingEntity(0, userEntity, LocalDateTime.now(), null, Stato.ATTIVA.name(), null, seriesEntity);
            ViewingEntity viewingEntity2 = viewingRepository.save(viewingEntity1);
            return true;
        }
        return false;
    }

    public Boolean deleteViewing(int id) {
        Optional<ViewingEntity> viewingEntityOptional = viewingRepository.findById(id);
        if (viewingEntityOptional.isPresent()) {
            viewingRepository.deleteById(id);
            Optional<ViewingEntity> viewingEntityOptional1 = viewingRepository.findById(id);
            if (viewingEntityOptional1.isPresent()) {
                return false;
            }
            return true;

        }
        return false;
    }

    public ViewingResponse updateViewing(ViewingRequest viewingRequest, int id) {
        Optional<ViewingEntity> viewingEntityOptional = viewingRepository.findById(id);
        if (viewingEntityOptional.isPresent()) {
            ViewingEntity viewingEntity = viewingMapper.mapMoveDtoToEntity(viewingRequest);
            viewingEntity.setIdViewing(id);
            ViewingEntity viewingEntity1 = viewingRepository.save(viewingEntity);
            return viewingMapper.mapMovieEntityToResponse(viewingEntity1);
        }
        return null;
    }

    public ViewingResponse getViewing(int id) {
        Optional<ViewingEntity> viewingEntityOptional = viewingRepository.findById(id);
        if (viewingEntityOptional.isPresent()) {
            return viewingMapper.mapMovieEntityToResponse(viewingEntityOptional.get());
        }
        return null;
    }
}







