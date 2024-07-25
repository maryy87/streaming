package com.example.netflix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private  Integer idUser;
    private  String nome;
    private String cognome;
    private String email;
}
