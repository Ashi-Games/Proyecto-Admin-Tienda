package com.storebuilder.storebuilder.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CurrentUserAdvice {

    @ModelAttribute
    public void addCurrentUser(Model model, HttpSession session) {
        Object usuarioActual = session.getAttribute("usuarioActual");
        model.addAttribute("usuarioActual", usuarioActual);
    }
}
