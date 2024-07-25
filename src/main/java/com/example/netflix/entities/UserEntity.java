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
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private  Integer idUser;
    @Column(name = "nome")
    private  String nome;
    @Column(name="cognome")
    private String cognome;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "userEntity")
    private List<ViewingEntity> viewingEntityList;
    @OneToMany(mappedBy = "userEntity")
    private List<PlaylistEntity> playlistEntities;
}
