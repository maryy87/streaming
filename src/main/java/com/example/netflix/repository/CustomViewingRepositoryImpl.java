package com.example.netflix.repository;

import com.example.netflix.dto.request.FiltroViewingRequest;
import com.example.netflix.entities.ViewingEntity;
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
public class CustomViewingRepositoryImpl implements CustomViewingRepository {
    final EntityManager entityManager;

    @Autowired
    public CustomViewingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<ViewingEntity> findViewingeByFiltro(FiltroViewingRequest filtroViewingRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ViewingEntity> cq = cb.createQuery(ViewingEntity.class);
        Root<ViewingEntity> viewingRoot = cq.from(ViewingEntity.class);
        List<Predicate> predicateList = new ArrayList<>();

        if (filtroViewingRequest.getDataDiFine() != null) {
            Predicate predicate = cb.equal(viewingRoot.get("dataDifine"), filtroViewingRequest.getDataDiFine());
            predicateList.add(predicate);
        }
        if (filtroViewingRequest.getDataDiVisione() != null) {
            Predicate predicate=cb.equal(viewingRoot.get("dataDiFine"),filtroViewingRequest.getDataDiVisione());
            predicateList.add(predicate);
        }
        if (filtroViewingRequest.getDataDiVisioneMin() != null) {
            Predicate predicate=cb.greaterThanOrEqualTo(viewingRoot.get("dataDiVisioneMin"),filtroViewingRequest.getDataDiVisioneMin());
            predicateList.add(predicate);
        }
        if (filtroViewingRequest.getDataDiVisioneMax() != null) {
            Predicate predicate=cb.lessThanOrEqualTo(viewingRoot.get("dataDiVisioneMax"),filtroViewingRequest.getDataDiVisioneMax());
            predicateList.add(predicate);
        }
        if (filtroViewingRequest.getStato() != null) {
            Predicate predicate=cb.like(viewingRoot.get("stato"), "%"+ filtroViewingRequest.getStato() + "%");
            predicateList.add(predicate);
        }
        if (filtroViewingRequest.getDataDiFineMax() != null) {
            Predicate predicate=cb.lessThanOrEqualTo(viewingRoot.get("dataDiFineMax"),filtroViewingRequest.getDataDiFineMax());
            predicateList.add(predicate);
        }
        if (filtroViewingRequest.getDataDiFineMin() != null) {
            Predicate predicate=cb.greaterThanOrEqualTo(viewingRoot.get("dataDiFineMax"),filtroViewingRequest.getDataDiFineMin());
            predicateList.add(predicate);
        }

        cq.where(predicateList.toArray(new Predicate[0]));
        return entityManager.createQuery(cq).getResultList();
    }
}
