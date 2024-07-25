package com.example.netflix.services;

import com.example.netflix.dto.request.FiltroMovieRequest;
import com.example.netflix.dto.request.MovieRequest;
import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.dto.response.VisioniMovie;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.mapper.MovieMapper;
import com.example.netflix.repository.CustomContenutoDao;
import com.example.netflix.repository.MovieRepository;
import jakarta.persistence.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private CustomContenutoDao customContenutoDao;

    public List<VisioniMovie> moviePiuVisualizzato() {
        List<Tuple> tupleList = movieRepository.moviePiuVisualizzato();
        Tuple[] array = tupleList.toArray(new Tuple[0]);
        Long count = array[0].get("c", Long.class);
        List<VisioniMovie> visioniMoviesList = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            if (tuple.get("c", Long.class )== count) {
                VisioniMovie visioniMovie=new VisioniMovie();
                visioniMovie.setTitolo(tuple.get("titolo", String.class));
                visioniMovie.setGenere(tuple.get("genere", String.class));
                visioniMovie.setDurata(tuple.get("durata", Integer.class));
                Timestamp timestamp=tuple.get("data_di_visione",Timestamp.class);
                LocalDateTime localDateTime=timestamp.toLocalDateTime();
                visioniMovie.setDataDiVisione(localDateTime);
                Timestamp timestamp1=tuple.get("data_di_fine",Timestamp.class);
                LocalDateTime localDateTime1=timestamp.toLocalDateTime();
                visioniMovie.setDataDiVisione(localDateTime1);
                Timestamp timestamp2=tuple.get("data_di_pubblicazione",Timestamp.class);
                LocalDateTime localDateTime2=timestamp.toLocalDateTime();
                visioniMovie.setDataDiVisione(localDateTime2);
                visioniMoviesList.add(visioniMovie);
            }
        }
        return visioniMoviesList;
    }

    public List<MovieResponse> moviePiuSalvati() {
        List<Tuple> tupleList = movieRepository.moviePiuSalvato();
        List<MovieResponse> movieResponseList = new ArrayList<>();
        Tuple[] array = tupleList.toArray(new Tuple[0]);
        Long count = array[0].get("c", Long.class);
        for (Tuple tuple : tupleList) {
            if (tuple.get("c", Long.class) == count) {
                MovieResponse movieResponse = new MovieResponse();
                movieResponse.setIdMovie(tuple.get("id_movie", Integer.class));
                movieResponse.setDurata(tuple.get("durata", Integer.class));
                movieResponse.setTitolo(tuple.get("titolo", String.class));
                movieResponse.setGenere(tuple.get("genere", String.class));
                Timestamp timestamp = tuple.get("data_di_pubblicazione", Timestamp.class);
                LocalDateTime dataDiPubblicazione = timestamp.toLocalDateTime();
                movieResponse.setDataDiPubblicazione(dataDiPubblicazione);
                movieResponseList.add(movieResponse);
            }
        }
        return movieResponseList;
    }


    public List<MovieResponse> ricercaMovie(FiltroMovieRequest filtroMovieRequest) {
        if (filtroMovieRequest != null) {
            List<MovieResponse> movieResponseList = movieMapper.mapMovieEntityListToDto(customContenutoDao.findMovieByFiltro(filtroMovieRequest));
            if (!movieResponseList.isEmpty()) {
                return movieResponseList;
            }
        }
        return new ArrayList<>();
    }

    public MovieResponse addMovie(MovieRequest movieRequest) {
        MovieEntity movieEntity = movieMapper.mapMoveDtoToEntity(movieRequest);
        MovieEntity movieSave = movieRepository.save(movieEntity);
        return movieMapper.mapMovieEntityToResponse(movieSave);
    }

    public Boolean deleteMovie(int idMovie) {
        if (movieRepository.findById(idMovie).isPresent()) {
            movieRepository.deleteById(idMovie);
            MovieResponse movieResponse = ricercaMovieById(idMovie);
            if (movieResponse != null) {
                return false;
            }
            return true;
        }
        return false;
    }

    public MovieResponse updateMovie(MovieRequest movieRequest, Integer id) {
        MovieEntity movieEntity = movieMapper.mapMoveDtoToEntity(movieRequest);
        if (movieRepository.findById(id).isPresent()) {
            movieEntity.setIdMovie(id);
            MovieEntity movieEntity1 = movieRepository.save(movieEntity);
            return movieMapper.mapMovieEntityToResponse(movieEntity1);
        }
        return null;
    }

    public MovieResponse ricercaMovieById(Integer id) {
        Optional<MovieEntity> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return movieMapper.mapMovieEntityToResponse(optionalMovie.get());
        }
        return null;
    }
}
