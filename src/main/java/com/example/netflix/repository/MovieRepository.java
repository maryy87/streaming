package com.example.netflix.repository;

import com.example.netflix.dto.response.MovieResponse;
import com.example.netflix.entities.MovieEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT  movie.durata,movie.id_movie,movie.data_di_pubblicazione,movie.genere,movie.titolo FROM movie\n" +
            "JOIN  contenuti_salvati  ON movie.id_movie=contenuti_salvati.id_movie \n" +
            "GROUP BY contenuti_salvati.id_movie\n" +
            "LIMIT 10\n")
    List<MovieEntity> topDieciMoviePiuSalvati();

    @Query(nativeQuery = true,value = "SELECT movie.durata,movie.id_movie,movie.data_di_pubblicazione,movie.genere,movie.titolo , COUNT(contenuti_salvati.id_movie) AS c FROM contenuti_salvati \n" +
            "JOIN movie ON movie.id_movie=contenuti_salvati.id_movie \n" +
            "GROUP BY id_movie\n" +
            "ORDER BY c desc")
    List<Tuple> moviePiuSalvato();

    @Query(nativeQuery = true,value = "SELECT  movie.data_di_pubblicazione,movie.Durata , movie.id_movie, movie.titolo,movie.genere,viewing.data_di_Visione,viewing.data_di_fine, COUNT(viewing.id_movie) AS c FROM movie\n" +
            "JOIN viewing ON viewing.id_movie=movie.id_movie\n" +
            "GROUP BY viewing.id_movie\n" +
            "ORDER BY c desc")
    List<Tuple> moviePiuVisualizzato();
}
