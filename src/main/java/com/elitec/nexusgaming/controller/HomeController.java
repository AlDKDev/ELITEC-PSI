package com.elitec.nexusgaming.controller;

import com.elitec.nexusgaming.model.Videojuego;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("mensaje", "Bienvenido a NEXUS Gaming, lo mejor de Perú");
        model.addAttribute("subtitulo", "El mejor sistema de inventario de videojuegos");
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/faq")
    public String faq(){
        return "faq";
    }

    @GetMapping("/catalogue")
    public String catalogue(Model model) {
        // Crear lista de videojuegos de prueba
        List<Videojuego> videojuegos = crearVideojuegosPrueba();

        // Agregar lista al modelo
        model.addAttribute("videojuegos", videojuegos);

        // Agregar título de la página
        model.addAttribute("tituloPagina", "Catálogo de Videojuegos");

        // Agregar cantidad total
        model.addAttribute("totalVideojuegos", videojuegos.size());

        return "catalogue";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    private List<Videojuego> crearVideojuegosPrueba() {
        return Arrays.asList(
                new Videojuego(
                        1L,
                        "The Legend of Zelda: Tears of the Kingdom",
                        "La esperada secuela de Breath of the Wild",
                        "Nintendo Switch",
                        "Aventura",
                        279.90,
                        15,
                        "Nintendo",
                        2023,
                        "https://upload.wikimedia.org/wikipedia/en/thumb/f/fb/The_Legend_of_Zelda_Tears_of_the_Kingdom_cover.jpg/250px-The_Legend_of_Zelda_Tears_of_the_Kingdom_cover.jpg"
                ),
                new Videojuego(
                        2L,
                        "God of War Ragnarök",
                        "Kratos y Atreus en una nueva aventura nórdica",
                        "PlayStation 5",
                        "Acción",
                        249.90,
                        12,
                        "Sony Santa Monica",
                        2022,
                        "https://image.api.playstation.com/vulcan/ap/rnd/202207/1210/4xJ8XB3bi888QTLZYdl7Oi0s.png"
                ),
                new Videojuego(
                        3L,
                        "Halo Infinite",
                        "El regreso del Jefe Maestro",
                        "Xbox Series X",
                        "Shooter",
                        199.90,
                        8,
                        "343 Industries",
                        2021,
                        "https://m.media-amazon.com/images/I/614ZKXVkrUL._AC_SL1000_.jpg"
                ),
                new Videojuego(
                        4L,
                        "Elden Ring",
                        "RPG de mundo abierto de FromSoftware",
                        "PC",
                        "RPG",
                        229.90,
                        20,
                        "FromSoftware",
                        2022,
                        "https://image.api.playstation.com/vulcan/ap/rnd/202110/2000/aGhopp3MHppi7kooGE2Dtt8C.png"
                ),
                new Videojuego(
                        5L,
                        "FIFA 24",
                        "La experiencia de fútbol definitiva",
                        "PlayStation 5",
                        "Deportes",
                        299.90,
                        25,
                        "EA Sports",
                        2023,
                        "https://mundosteam.shop/wp-content/uploads/2023/12/Diseno-sin-titulo-2023-11-07T164536.409-1.png"
                ),
                new Videojuego(
                        6L,
                        "Spider-Man 2",
                        "Peter Parker y Miles Morales juntos",
                        "PlayStation 5",
                        "Acción",
                        329.90,
                        10,
                        "Insomniac Games",
                        2023,
                        "https://image.api.playstation.com/vulcan/ap/rnd/202306/1219/2028edeaf4c0b60142550a3d6e024b6009853ceb9f51591e.jpg"
                ),
                new Videojuego(
                        7L,
                        "Starfield",
                        "Explora la galaxia en este RPG espacial",
                        "Xbox Series X",
                        "RPG",
                        269.90,
                        18,
                        "Bethesda",
                        2023,
                        "https://sklepzgrami.pl/wp-content/uploads/2023/12/starfield-main.jpg"
                ),
                new Videojuego(
                        8L,
                        "Super Mario Bros Wonder",
                        "Una nueva aventura 2D de Mario",
                        "Nintendo Switch",
                        "Plataformas",
                        259.90,
                        22,
                        "Nintendo",
                        2023,
                        "https://m.media-amazon.com/images/I/81+nZRC5SBL.jpg"
                ),
                new Videojuego(
                        9L,
                        "Baldur's Gate 3",
                        "RPG épico basado en D&D",
                        "PC",
                        "RPG",
                        239.90,
                        14,
                        "Larian Studios",
                        2023,
                        "https://image.api.playstation.com/vulcan/ap/rnd/202302/2321/3098481c9164bb5f33069b37e49fba1a572ea3b89971ee7b.jpg"
                ),
                new Videojuego(
                        10L,
                        "Alan Wake 2",
                        "Terror psicológico de Remedy",
                        "PC",
                        "Terror",
                        289.90,
                        6,
                        "Remedy Entertainment",
                        2023,
                        "https://image.api.playstation.com/vulcan/ap/rnd/202305/2420/fbd0dcc88b31805fc7d49f59b8e0e5d0276403cde7fb8cc8.jpg"
                )
        );
    }
}
