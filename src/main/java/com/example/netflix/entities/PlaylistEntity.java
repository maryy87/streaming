package com.example.netflix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist")
public class PlaylistEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idplaylist")
    private int idplaylist;
    @Column(name = "nome")
    private String nome;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "idUser",nullable = true)
    private UserEntity userEntity ;
    @OneToMany(mappedBy = "idPlaylist")
    private List<ContenutiSalvati> contenutiSalvatiList;
}
