package com.example.netflix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisioniSeries {

    private int numero_di_episodi;
    private int numero_di_stagioni;
    private String titolo;
    private String genere;
    private LocalDateTime data_di_Visione;
    private LocalDateTime data_di_fine;
}
