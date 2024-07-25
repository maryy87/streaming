package com.example.netflix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest implements Serializable {


    private String titolo;
    private  String genere;
    private LocalDateTime dataDiPubblicazione;
    private Integer durata;

}
