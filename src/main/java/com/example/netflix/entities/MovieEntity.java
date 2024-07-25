package com.example.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movie")
public class MovieEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovie")
    private int idMovie;
    @Column(name = "titolo")
    private String titolo;
    @Column(name = "genere")
    private  String genere;
    @Column(name = "dataDiPubblicazione")
    private LocalDateTime dataDiPubblicazione;
    @Column(name = "durata")
    private int durata;
    @OneToMany(mappedBy ="movieEntity")
    private List<ViewingEntity> viewingEntityList;
    @OneToMany(mappedBy = "idMovie")
    private List<ContenutiSalvati> contenutiSalvatiList;

}
