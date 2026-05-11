package com.storebuilder.storebuilder.controller;

import com.storebuilder.storebuilder.model.ContactForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PageController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Crea tu tienda online en minutos");
        model.addAttribute("subtitulo", "Una plataforma Spring Boot que usa Thymeleaf para renderizar contenido dinámico desde el backend.");
        model.addAttribute("beneficios", List.of(
                "Diseños profesionales y responsive",
                "Carrito de compras y pagos integrados",
                "Fácil administración desde el backend"
        ));
        return "index";
    }

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("servicios", List.of(
                "Desarrollo Web y Tiendas Online",
                "Marketing Digital",
                "Diseño Gráfico",
                "Consultoría Digital",
                "Soporte y Mantenimiento"
        ));
        return "servicios";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model model) {
        model.addAttribute("empresa", "StoreBuilder");
        model.addAttribute("vision", "Ayudar a negocios a crecer con presencia digital profesional.");
        return "nosotros";
    }

    @GetMapping("/contacto")
    public String contacto(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contacto";
    }

    @PostMapping("/contacto")
    public String enviarContacto(
            @Valid @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "contacto";
        }

        model.addAttribute("resultado", "Gracias " + contactForm.getNombre() + ", recibimos tu mensaje correctamente.");
        model.addAttribute("contactForm", new ContactForm());
        return "contacto";
    }
}