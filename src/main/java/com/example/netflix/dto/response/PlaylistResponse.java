package com.example.netflix.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistResponse implements Serializable {

    private Integer idplaylist;
    private String nome;
    private Integer userEntity ;
}
