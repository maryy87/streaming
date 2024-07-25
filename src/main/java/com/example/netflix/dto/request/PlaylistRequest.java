package com.example.netflix.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistRequest implements Serializable {

    private String nome;
    private Integer userEntity ;
}
