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
    private String descripcion;
    private String plataforma;
    private String genero;
    private Double precio;
    private Integer stock;
    private String desarrolladora;
    private Integer anioLanzamiento;
    private String portada;
}
