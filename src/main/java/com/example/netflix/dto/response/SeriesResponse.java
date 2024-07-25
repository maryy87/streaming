package com.example.netflix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesResponse implements Serializable {

    private Integer idSeries;
    private String titolo;
    private  String genere;
    private Integer numeroDiStagioni;
    private Integer numeroDiEpisodi;

}
