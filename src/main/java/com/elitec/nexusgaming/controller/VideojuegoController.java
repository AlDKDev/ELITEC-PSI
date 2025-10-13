package com.elitec.nexusgaming.controller;

import com.elitec.nexusgaming.model.VideojuegoEntity;
import com.elitec.nexusgaming.services.VideojuegoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // SPRING BOOT MVC
@RequestMapping("/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    // Inyección de Depentecias en modalidad constructor
    private final VideojuegoService videojuegoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("videojuegos", videojuegoService.listarTodos());
        model.addAttribute("total", videojuegoService.contarTotal());
        model.addAttribute("valorInventario", videojuegoService.calcularValorInventario());
        return "videojuegos/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("videojuego", new VideojuegoEntity());
        return "videojuegos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        VideojuegoEntity videojuego = videojuegoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Videojuego no encontrado"));

        model.addAttribute("videojuego", videojuego);
        return "videojuegos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute VideojuegoEntity videojuego) {
        videojuegoService.guardar(videojuego);
        return "redirect:/videojuegos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        videojuegoService.eliminar(id);
        return "redirect:/videojuegos";
    }

    @GetMapping("/disponibles")
    public String listarDisponibles(Model model) {
        model.addAttribute("videojuegos", videojuegoService.buscarDisponibles());
        return "videojuegos/listar";
    }
}
