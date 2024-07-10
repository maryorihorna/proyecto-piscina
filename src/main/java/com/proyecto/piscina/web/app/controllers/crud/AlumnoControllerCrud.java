package com.proyecto.piscina.web.app.controllers.crud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/Alumno")
public class AlumnoControllerCrud {

    @GetMapping("/index")
    public String index() {
        return "Alumno/index"; // Asegúrate de que este archivo exista en templates/Alumno/index.html
    }

    @GetMapping("/create")
    public String create() {
        return "Alumno/create"; // Asegúrate de que este archivo exista en templates/Alumno/create.html
    }

    @GetMapping("/update")
    public String update() {
        return "Alumno/update"; // Asegúrate de que este archivo exista en templates/Alumno/update.html
    }

    @GetMapping("/delete")
    public String delete() {
        return "Alumno/delete"; // Asegúrate de que este archivo exista en templates/Alumno/delete.html
    }
}
