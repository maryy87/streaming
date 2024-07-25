package com.example.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContenutiSalvati implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idContenutiSalvati")
    private  int idContenutiSalvati;
//    @Column(name = "idMovie")
    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    @JoinColumn(name = "idMovie" , nullable = true)
    private MovieEntity idMovie;
//    @Column(name = "idSeries")
    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    @JoinColumn(name = "idSeries" , nullable = true)
    private SeriesEntity idSeries;
//    @Column(name = "idPlaylist")
    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    @JoinColumn(name = "idPlaylist" , nullable = true)
    private PlaylistEntity idPlaylist;
}
