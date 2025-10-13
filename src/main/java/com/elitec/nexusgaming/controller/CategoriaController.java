package com.elitec.nexusgaming.controller;

import com.elitec.nexusgaming.model.CategoriaEntity;
import com.elitec.nexusgaming.model.VideojuegoEntity;
import com.elitec.nexusgaming.repository.CategoriaRepository;
import com.elitec.nexusgaming.repository.VideojuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepository repositorio;

    // ========================================
    // LISTAR TODOS (READ)
    // ========================================
    @GetMapping
    public String ListarCategoria(Model model){
        List<CategoriaEntity> entidades = repositorio.findAll();
        return "demo";
    }
}
