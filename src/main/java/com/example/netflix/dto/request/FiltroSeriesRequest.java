package com.example.netflix.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroSeriesRequest implements Serializable {

    private  String titolo;
    private String genere;
    private Integer numeroDiEpisodi;
    private Integer numeroDiEpisodiMin;
    private Integer numeroDiEpisodiMax;
    private Integer numeroDiStagioni;
    private Integer numeroDiStagionMin;
    private Integer numeroDiStagioniMax;

}


