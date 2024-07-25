package com.example.netflix.repository;

import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.UserEntity;
import com.example.netflix.entities.ViewingEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewingRepository extends JpaRepository<ViewingEntity,Integer> {

    @Query(nativeQuery = true,value = "SELECT movie.data_di_pubblicazione,movie.Durata ,movie.titolo,movie.genere,viewing.data_di_Visione,viewing.data_di_fine FROM movie \n" +
            "                JOIN viewing ON viewing.id_movie=movie.id_movie\n" +
            "                WHERE viewing.id_user=:id_user")
    public List<Tuple> visioniStoricheMovieUtente(@Param("id_user") int  id_user);

    @Query(nativeQuery = true,value = "SELECT series.numero_di_episodi,series.numero_di_stagioni,series.titolo,series.genere,viewing.data_di_Visione,viewing.data_di_fine FROM series \n" +
            "                JOIN viewing ON viewing.id_series=series.id_series\n" +
            "                WHERE viewing.id_user=:id_user")
    public List<Tuple> visioniStoricheSeriesUtente(@Param("id_user") int  id_user);
}
