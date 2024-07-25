package com.example.netflix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeriesRequest implements Serializable {

    private String titolo;
    private  String genere;
    private Integer numeroDiStagioni;
    private Integer numeroDiEpisodi;

}
