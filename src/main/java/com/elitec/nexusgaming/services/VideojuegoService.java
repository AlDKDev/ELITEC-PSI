package com.elitec.nexusgaming.services;

import com.elitec.nexusgaming.model.VideojuegoEntity;

import java.util.List;
import java.util.Optional;

public interface VideojuegoService {
    // Operaciones CRUD básicas
    List<VideojuegoEntity> listarTodos();
    Optional<VideojuegoEntity> buscarPorId(Long id);
    VideojuegoEntity guardar(VideojuegoEntity videojuego);
    void eliminar(Long id);

    // Lógica de negocio
    long contarTotal();
    double calcularValorInventario();
    List<VideojuegoEntity> buscarDisponibles();
    boolean existePorTitulo(String titulo);
}
