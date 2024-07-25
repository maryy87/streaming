package com.example.netflix.dto.response;

import com.example.netflix.util.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewingResponse implements Serializable {

    private Integer idViewing;
    private Integer userEntity;
    private LocalDateTime dataDiVisione;
    private  LocalDateTime dataDiFine;
    private Stato stato;
    private Integer movieEntity;
    private Integer seriesEntity;
}
