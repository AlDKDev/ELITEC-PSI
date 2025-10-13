package com.elitec.nexusgaming.services.impl;

import com.elitec.nexusgaming.model.VideojuegoEntity;
import com.elitec.nexusgaming.repository.VideojuegoRepository;
import com.elitec.nexusgaming.services.VideojuegoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideojuegoServiceImpl implements VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    @Override
    public List<VideojuegoEntity> listarTodos() {
        log.info("Listando todos los videojuegos");
        return videojuegoRepository.findAll();
    }

    @Override
    public Optional<VideojuegoEntity> buscarPorId(Long id) {
        log.info("Buscando videojuego con ID: {}", id);
        return videojuegoRepository.findById(id);
    }

    @Override
    public VideojuegoEntity guardar(VideojuegoEntity videojuego) {
        log.info("Guardando videojuego: {}", videojuego.getTitulo());

        // Validaciones de negocio
        if (videojuego.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        if (videojuego.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        return videojuegoRepository.save(videojuego);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando videojuego con ID: {}", id);

        if (!videojuegoRepository.existsById(id)) {
            throw new IllegalArgumentException("Videojuego no encontrado");
        }

        videojuegoRepository.deleteById(id);

    }

    @Override
    public long contarTotal() {
        return videojuegoRepository.count();
    }

    @Override
    public double calcularValorInventario() {
        List<VideojuegoEntity> videojuegos = videojuegoRepository.findAll();
        return videojuegos.stream()
                .mapToDouble(v -> v.getPrecio() * v.getStock())
                .sum();
    }

    @Override
    public List<VideojuegoEntity> buscarDisponibles() {
        List<VideojuegoEntity> videojuegos = videojuegoRepository.findAll();
        return videojuegos.stream()
                .filter(v -> v.getStock() > 0)
                .toList();
    }

    @Override
    public boolean existePorTitulo(String titulo) {
        List<VideojuegoEntity> videojuegos = videojuegoRepository.findAll();
        return videojuegos.stream()
                .anyMatch(v -> v.getTitulo().equalsIgnoreCase(titulo));
    }
}
