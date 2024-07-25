package com.example.netflix.dto.response;

import com.example.netflix.entities.ViewingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse implements Serializable {

    private Integer idMovie;
    private String titolo;
    private  String genere;
    private LocalDateTime dataDiPubblicazione;
    private Integer durata;

}
