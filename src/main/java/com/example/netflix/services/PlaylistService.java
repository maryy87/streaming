package com.example.netflix.services;

import com.example.netflix.dto.request.PlaylistRequest;
import com.example.netflix.dto.request.SeriesRequest;
import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.dto.response.PlaylistResponse;
import com.example.netflix.dto.response.SeriesResponse;
import com.example.netflix.entities.ContenutiSalvati;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.PlaylistEntity;
import com.example.netflix.entities.SeriesEntity;
import com.example.netflix.mapper.MovieMapper;
import com.example.netflix.mapper.PlaylistMapper;
import com.example.netflix.mapper.SeriesMapper;
import com.example.netflix.repository.ContenutiSalvatiRepository;
import com.example.netflix.repository.MovieRepository;
import com.example.netflix.repository.PlaylistRepository;
import com.example.netflix.repository.SeriesRepository;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private PlaylistMapper playlistMapper;
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ContenutiSalvatiRepository contenutiSalvatiRepository;
    @Autowired
    private SeriesMapper seriesMapper;
    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private SeriesService seriesService;
    @Autowired
    private MovieService movieService;

    public List contenutoPiuSalvato() {
        List<Tuple> movieTupleList = movieRepository.moviePiuSalvato();
        Tuple[] arrayMovie = movieTupleList.toArray(new Tuple[0]);
        Long countMovie = arrayMovie[0].get("c", Long.class);
        List<Tuple> seriesTupleList = seriesRepository.seriesPiuSalvato();
        Tuple[] arraySeries = seriesTupleList.toArray(new Tuple[0]);
        Long countSeries = arraySeries[0].get("c", Long.class);
        if (countSeries > countMovie) {
            return  seriesService.seriesPiuSalvato();
        }
        return  movieService.moviePiuSalvati();
    }

    public Boolean addMovieInPlaylist(int idPlaylist, int idMovie) {
        if (idPlaylist != 0 && idMovie != 0) {
            PlaylistEntity playlistEntity = new PlaylistEntity();
            playlistEntity.setIdplaylist(idPlaylist);
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setIdMovie(idMovie);
            ContenutiSalvati contenutiSalvati = new ContenutiSalvati(0, movieEntity, null, playlistEntity);
            contenutiSalvatiRepository.save(contenutiSalvati);
            return true;
        }
        return false;
    }

    public Boolean addSeriesInPlaylist(int idPlaylist, int seriesRequest) {
        if (idPlaylist != 0 && seriesRequest != 0) {
            PlaylistEntity playlistEntity = new PlaylistEntity();
            playlistEntity.setIdplaylist(idPlaylist);
            SeriesEntity seriesEntity = new SeriesEntity();
            seriesEntity.setIdSeries(seriesRequest);
            ContenutiSalvati contenutiSalvati = new ContenutiSalvati(0, null, seriesEntity, playlistEntity);
            ContenutiSalvati contenutiSalvati1 = contenutiSalvatiRepository.save(contenutiSalvati);
            return true;
        }
        return false;
    }


    public List<MovieResponse> topDieciFilmSalvati() {
        List<MovieResponse> movieResponseList = movieMapper.mapMovieEntityListToDto(movieRepository.topDieciMoviePiuSalvati());
        if (!movieResponseList.isEmpty()) {
            return movieResponseList;
        }
        return new ArrayList<>();
    }

    public PlaylistResponse addPlaylist(PlaylistRequest playlistRequest) {
        PlaylistEntity playlistEntity = playlistMapper.mapPlaylistDtoToEntity(playlistRequest);
        PlaylistEntity playlistEntity1 = playlistRepository.save(playlistEntity);
        return playlistMapper.mapPlaylistEntityToResponse(playlistEntity1);

    }

    public Boolean deleteplaylist(int id) {
        Optional<PlaylistEntity> playlistEntity = playlistRepository.findById(id);
        if (playlistEntity.isPresent()) {
            playlistRepository.deleteById(id);
            Optional<PlaylistEntity> playlistEntity1 = playlistRepository.findById(id);
            if (playlistEntity1.isPresent()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public PlaylistResponse updatePlaylist(PlaylistRequest playlistRequest, int id) {
        Optional<PlaylistEntity> playlistEntity1 = playlistRepository.findById(id);
        if (playlistEntity1.isPresent()) {
            PlaylistEntity playlistEntity = playlistMapper.mapPlaylistDtoToEntity(playlistRequest);
            playlistEntity.setIdplaylist(id);
            PlaylistEntity playlistEntity2 = playlistRepository.save(playlistEntity);
            return playlistMapper.mapPlaylistEntityToResponse(playlistEntity2);
        }
        return null;

    }

    public PlaylistResponse getPlaylist(int id) {
        Optional<PlaylistEntity> playlistEntity1 = playlistRepository.findById(id);
        if (playlistEntity1.isPresent()) {
            return playlistMapper.mapPlaylistEntityToResponse(playlistRepository.findById(id).get());
        }
        return null;
    }
}















