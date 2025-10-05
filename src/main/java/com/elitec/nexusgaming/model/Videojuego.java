package com.elitec.nexusgaming.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {
    private Long id;
    private String titulo;
    private String desarrolladora;
    private String plataforma;
    private Double precio;
    private Integer stock;
    private String genero;
    private String portadaUrl;
    private Boolean destacado;
}
