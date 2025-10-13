package com.elitec.nexusgaming.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="videojuego")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideojuegoEntity {
    //Crear nuestra Clave Primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "título", nullable = false, length = 3200)
    private String titulo;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "plataforma", nullable = false, length = 50)
    private String plataforma;

    @Column(name = "genero", length = 50)
    private String genero;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "desarrolladora", length = 100)
    private String desarrolladora;

    @Column(name = "fecha_lanzamiento")
    private LocalDate fechaLanzamiento;

    @Column(name = "url_caratula", length = 500)
    private String portada;

    @Column(name = "activo")
    private Boolean activo = true;

    // MÉTODOS DEL NEGOCIO
    public boolean tieneStock(){
        return this.stock != null && this.stock > 0;
    }

    public boolean stockBajo(){
        return this.stock != null && this.stock < 5;
    }

    public Double valorInventario() {
        if (this.precio != null && this.stock != null) {
            return this.precio * this.stock;
        }
        return 0.0;
    }








    public String getColorPlataforma(){
        switch (this.plataforma){
            case "PlayStation 5":
                return "bg-primary";
            case "Xbox Series X":
                return "bg-success";
            case "Nintendo Switch":
                return "bg-danger";
            default:
                return "bg-secondary";
        }

    }
}
