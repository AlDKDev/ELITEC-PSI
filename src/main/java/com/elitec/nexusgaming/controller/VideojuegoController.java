package com.elitec.nexusgaming.controller;

import com.elitec.nexusgaming.model.VideojuegoEntity;
import com.elitec.nexusgaming.repository.VideojuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller // SPRING BOOT MVC
//@RestController // SPRING BOOT API REST
@RequestMapping("/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {

    // Inyección de Depentecias en modalidad constructor
    private final VideojuegoRepository videojuegoRepository;

    //@Autowired
    //private VideojuegoRepository videojuegoRepository2;

    // ========================================
    // LISTAR TODOS (READ)
    // ========================================
    @GetMapping
    public String ListarVideojuegos(Model model){
        List<VideojuegoEntity> videojuegos = videojuegoRepository.findAll();

        // Calcular estadísticas
        long totalVideojuegos = videojuegos.size();
        long stockTotal = videojuegos.stream()
                .mapToInt(VideojuegoEntity::getStock)
                .sum();
        double valorInventario = videojuegos.stream()
                .mapToDouble(VideojuegoEntity::valorInventario)
                .sum();
        long stockBajo = videojuegos.stream()
                .filter(VideojuegoEntity::stockBajo)
                .count();

        // Enviar datos a la vista
        model.addAttribute("videojuegos", videojuegos);
        model.addAttribute("totalVideojuegos", totalVideojuegos);
        model.addAttribute("stockTotal", stockTotal);
        model.addAttribute("valorInventario", valorInventario);
        model.addAttribute("stockBajo", stockBajo);

        return "videojuegos/lista";
    }

    // ========================================
    // MOSTRAR FORMULARIO CREAR (CREATE)
    // ========================================
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("videojuego", new VideojuegoEntity());
        model.addAttribute("accion", "Crear");
        return "videojuegos/formulario";
    }

    // ========================================
    // GUARDAR NUEVO (CREATE)
    // ========================================

    /**
     * POST /videojuegos
     * Procesa el formulario y guarda un nuevo videojuego
     */
    @PostMapping
    public String guardarVideojuego(@RequestParam("titulo") String titulo,
                                    @RequestParam("descripcion") String descripcion,
                                    @RequestParam("desarrolladora") String desarrolladora,
                                    @RequestParam("plataforma") String plataforma,
                                    @RequestParam("precio") Double precio,
                                    @RequestParam("stock") Integer stock,
                                    @RequestParam("genero") String genero,
                                    @RequestParam(value = "urlCaratula", required = false) String urlCaratula) {

        // Construir objeto con patrón Builder de Lombok
        VideojuegoEntity videojuego = VideojuegoEntity.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .desarrolladora(desarrolladora)
                .plataforma(plataforma)
                .precio(precio)
                .stock(stock)
                .genero(genero)
                .portada(urlCaratula)
                .activo(true)
                .build();

        // Guardar en base de datos
        videojuegoRepository.save(videojuego);

        // Redirigir a la lista (patrón POST-REDIRECT-GET)
        return "redirect:/videojuegos";
    }

    // ========================================
    // VER DETALLE (READ)
    // ========================================

    /**
     * GET /videojuegos/{id}
     * Muestra los detalles de un videojuego específico
     */
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable("id") Long id, Model model) {
        Optional<VideojuegoEntity> videojuegoOpt = videojuegoRepository.findById(id);

        if (videojuegoOpt.isPresent()) {
            model.addAttribute("videojuego", videojuegoOpt.get());
            return "videojuegos/detalle";
        } else {
            // Si no existe, redirigir a la lista
            return "redirect:/videojuegos";
        }
    }

    // ========================================
    // MOSTRAR FORMULARIO EDITAR (UPDATE)
    // ========================================

    /**
     * GET /videojuegos/editar/{id}
     * Muestra el formulario pre-llenado para editar
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        Optional<VideojuegoEntity> videojuegoOpt = videojuegoRepository.findById(id);

        if (videojuegoOpt.isPresent()) {
            model.addAttribute("videojuego", videojuegoOpt.get());
            model.addAttribute("accion", "Editar");
            return "videojuegos/formulario";
        } else {
            return "redirect:/videojuegos";
        }
    }

    // ========================================
    // ACTUALIZAR (UPDATE)
    // ========================================

    /**
     * POST /videojuegos/actualizar
     * Procesa el formulario de edición y actualiza el videojuego
     */
    @PostMapping("/actualizar")
    public String actualizarVideojuego(@RequestParam("id") Long id,
                                       @RequestParam("titulo") String titulo,
                                       @RequestParam("descripcion") String descripcion,
                                       @RequestParam("desarrolladora") String desarrolladora,
                                       @RequestParam("plataforma") String plataforma,
                                       @RequestParam("precio") Double precio,
                                       @RequestParam("stock") Integer stock,
                                       @RequestParam("genero") String genero,
                                       @RequestParam(value = "urlCaratula", required = false) String urlCaratula) {

        Optional<VideojuegoEntity> videojuegoOpt = videojuegoRepository.findById(id);

        if (videojuegoOpt.isPresent()) {
            VideojuegoEntity videojuego = videojuegoOpt.get();

            // Actualizar campos
            videojuego.setTitulo(titulo);
            videojuego.setDescripcion(descripcion);
            videojuego.setDesarrolladora(desarrolladora);
            videojuego.setPlataforma(plataforma);
            videojuego.setPrecio(precio);
            videojuego.setStock(stock);
            videojuego.setGenero(genero);
            videojuego.setPortada(urlCaratula);

            // Guardar cambios
            videojuegoRepository.save(videojuego);
        }

        return "redirect:/videojuegos";
    }

    // ========================================
    // ELIMINAR (DELETE)
    // ========================================

    /**
     * POST /videojuegos/eliminar/{id}
     * Elimina un videojuego de la base de datos
     */
    @PostMapping("/eliminar/{id}")
    public String eliminarVideojuego(@PathVariable("id") Long id) {
        videojuegoRepository.deleteById(id);
        return "redirect:/videojuegos";
    }
}
