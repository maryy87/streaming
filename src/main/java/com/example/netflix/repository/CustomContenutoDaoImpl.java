package com.example.netflix.repository;

import com.example.netflix.dto.request.FiltroMovieRequest;
import com.example.netflix.dto.request.FiltroSeriesRequest;
import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.SeriesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomContenutoDaoImpl implements CustomContenutoDao {

    final EntityManager entityManager;

    @Autowired
    public CustomContenutoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MovieEntity> findMovieByFiltro(FiltroMovieRequest filtroMovieRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieEntity> cq = cb.createQuery(MovieEntity.class);
        Root<MovieEntity> movieRoot = cq.from(MovieEntity.class);
        List<Predicate> predicateList = new ArrayList<>();

        if (filtroMovieRequest.getTitolo() != null && !filtroMovieRequest.getTitolo().isEmpty()) {
            Predicate predicate = cb.like(movieRoot.get("titolo"), "%" + filtroMovieRequest.getTitolo() + "%");
            predicateList.add(predicate);
        }
        if (filtroMovieRequest.getGenere() != null && !filtroMovieRequest.getGenere().isEmpty()) {
            Predicate predicate = cb.like(movieRoot.get("genere"), "%" + filtroMovieRequest.getGenere() + "%");
            predicateList.add(predicate);
        }
        if (filtroMovieRequest.getDurata() != null) {
            Predicate predicate = cb.equal(movieRoot.get("durata"), filtroMovieRequest.getDurata());
            predicateList.add(predicate);
        }
        if (filtroMovieRequest.getDurataMin() != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(movieRoot.get("durata"), filtroMovieRequest.getDurataMin());
            predicateList.add(predicate);
        }
        if (filtroMovieRequest.getDurataMax() != null) {
            Predicate predicate = cb.lessThanOrEqualTo(movieRoot.get("durata"), filtroMovieRequest.getDurataMax());
            predicateList.add(predicate);
        }
        cq.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }


    @Override
    public List<SeriesEntity> findSeriesByFiltro(FiltroSeriesRequest filtroSeriesRequest) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SeriesEntity> cq = cb.createQuery(SeriesEntity.class);
        Root<SeriesEntity> seriesRoot = cq.from(SeriesEntity.class);
        List<Predicate> predicateList = new ArrayList<>();

        if (filtroSeriesRequest.getTitolo() != null && !filtroSeriesRequest.getTitolo().isEmpty()) {
            Predicate predicate = cb.like(seriesRoot.get("titolo"), "%" + filtroSeriesRequest.getTitolo() + "%");
            predicateList.add(predicate);
        }

        if (filtroSeriesRequest.getGenere() != null && !filtroSeriesRequest.getGenere().isEmpty()) {
            Predicate predicate = cb.like(seriesRoot.get("genere"), "%" + filtroSeriesRequest.getGenere() + "%");
            predicateList.add(predicate);
        }
        if (filtroSeriesRequest.getNumeroDiStagioni() != null) {
            Predicate predicate = cb.equal(seriesRoot.get("numeroDiStagioni"), filtroSeriesRequest.getNumeroDiStagioni());
            predicateList.add(predicate);
        }
        if (filtroSeriesRequest.getNumeroDiStagionMin() != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(seriesRoot.get("numeroDiStagioni"), filtroSeriesRequest.getNumeroDiStagionMin());
            predicateList.add(predicate);
        }
        if (filtroSeriesRequest.getNumeroDiStagioniMax() != null) {
            Predicate predicate = cb.lessThanOrEqualTo(seriesRoot.get("numeroDiStagioni"), filtroSeriesRequest.getNumeroDiStagioniMax());
            predicateList.add(predicate);
        }
        if (filtroSeriesRequest.getNumeroDiEpisodi() != null) {
            Predicate predicate = cb.equal(seriesRoot.get("numeroDiEpisodi"), filtroSeriesRequest.getNumeroDiEpisodi());
            predicateList.add(predicate);
        }
        if (filtroSeriesRequest.getNumeroDiEpisodiMin() != null) {
            Predicate predicate = cb.greaterThanOrEqualTo(seriesRoot.get("numeroDiEpisodi"), filtroSeriesRequest.getNumeroDiEpisodiMin());
            predicateList.add(predicate);
        }
        if (filtroSeriesRequest.getNumeroDiEpisodiMax() != null) {
            Predicate predicate = cb.lessThanOrEqualTo(seriesRoot.get("numeroDiEpisodi"), filtroSeriesRequest.getNumeroDiEpisodiMax());
            predicateList.add(predicate);
        }
        cq.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();

    }
}
