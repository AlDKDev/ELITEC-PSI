package com.elitec.nexusgaming.repository;

import com.elitec.nexusgaming.model.VideojuegoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepository extends JpaRepository<VideojuegoEntity, Long>{

    // Busquedas de videojuego por plataforma
    List<VideojuegoEntity> findByPlataforma(String plataforma);

    // Busqueda con fragmento de texto
    List<VideojuegoEntity> findByTituloContainingIgnoreCase(String titulo);

    // Busqueda de videojugos activos
    List<VideojuegoEntity> findByActivoTrue();
}
