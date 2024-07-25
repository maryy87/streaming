package com.example.netflix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroMovieRequest implements Serializable {

    private  String titolo;
    private String genere;
    private Integer durata;
    private Integer durataMin;
    private Integer durataMax;
}
