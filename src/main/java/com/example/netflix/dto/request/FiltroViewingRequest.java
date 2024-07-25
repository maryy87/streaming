package com.example.netflix.dto.request;

import com.example.netflix.util.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiltroViewingRequest implements Serializable {

    private Stato stato;
    private LocalDateTime dataDiFine;
    private LocalDateTime dataDiFineMin;
    private LocalDateTime dataDiFineMax;
    private LocalDateTime dataDiVisione;
    private LocalDateTime dataDiVisioneMin;
    private LocalDateTime dataDiVisioneMax;
}
