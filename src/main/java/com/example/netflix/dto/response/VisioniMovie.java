package com.example.netflix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisioniMovie {

    private LocalDateTime dataDiPubblicazione;
    private  int durata;
    private String titolo;
    private String genere;
    private LocalDateTime dataDiVisione;
    private LocalDateTime dataDiFine;


}
