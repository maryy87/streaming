package com.example.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "viewing")
public class ViewingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="idViewing")
    private int idViewing;
    @ManyToOne
    @JoinColumn(name="idUser")
    private UserEntity userEntity;
    @Column(name = "dataDiVisione")
    private LocalDateTime dataDiVisione;
    @Column(name = "dataDiFine")
    private  LocalDateTime dataDiFine;
    @Column(name = "stato")
    private String stato;
    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    @JoinColumn(name = "idMovie", nullable = true)
    private MovieEntity movieEntity;
    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    @JoinColumn(name = "idSeries", nullable = true)
    private SeriesEntity seriesEntity;
}
