package com.example.netflix.repository;

import com.example.netflix.entities.MovieEntity;
import com.example.netflix.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity,Integer> {


}
