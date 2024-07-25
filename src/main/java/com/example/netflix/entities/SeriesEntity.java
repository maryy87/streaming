package com.example.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "series")
public class SeriesEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSeries")
    private int idSeries;
    @Column(name = "titolo")
    private String titolo;
    @Column(name = "genere")
    private  String genere;
    @Column(name = "numeroDiStagioni")
    private int numeroDiStagioni;
    @Column(name = "numeroDiEpisodi")
    private int numeroDiEpisodi;
    @OneToMany(mappedBy = "seriesEntity")
    private List<ViewingEntity> viewingEntityList;
    @OneToMany(mappedBy = "idSeries")
    private List<ContenutiSalvati> contenutiSalvatiList;
}
