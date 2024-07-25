package com.example.netflix.repository;

import com.example.netflix.entities.ContenutiSalvati;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenutiSalvatiRepository extends JpaRepository<ContenutiSalvati,Integer> {
}
