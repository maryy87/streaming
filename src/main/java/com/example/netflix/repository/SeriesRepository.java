package com.example.netflix.repository;

import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.SeriesEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<SeriesEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT series.id_series, series.titolo, series.genere, series.numero_di_episodi, series.numero_di_stagioni , COUNT(contenuti_salvati.id_series) AS c \n" +
            " FROM contenuti_salvati\n" +
            " JOIN series ON series.id_series=contenuti_salvati.id_series\n" +
            "GROUP BY id_series\n" +
            "ORDER BY c DESC\n" +
            "LIMIT 10")
    List<SeriesEntity> topDieciSeriesPiuSalvati();

    @Query(nativeQuery = true,value = "SELECT series.id_series, series.titolo, series.genere, series.numero_di_episodi, series.numero_di_stagioni , COUNT(contenuti_salvati.id_series) AS c \n" +
            " FROM contenuti_salvati\n" +
            " JOIN series ON series.id_series=contenuti_salvati.id_series\n" +
            "GROUP BY id_series\n" +
            "ORDER BY c DESC")
    List<Tuple> seriesPiuSalvato();

    @Query(nativeQuery = true,value = "SELECT series.numero_di_episodi,series.numero_di_stagioni,series.titolo,series.genere,viewing.data_di_Visione,viewing.data_di_fine, COUNT(viewing.id_series)AS c FROM series \n" +
            "JOIN viewing ON viewing.id_series=series.id_series\n" +
            "GROUP BY viewing.id_series\n" +
            "ORDER BY c desc")
    List<Tuple> seriesPiuVisualizzato();
}
