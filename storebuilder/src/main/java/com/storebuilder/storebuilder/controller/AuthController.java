package com.storebuilder.storebuilder.controller;

import com.storebuilder.storebuilder.model.LoginForm;
import com.storebuilder.storebuilder.model.RegistrationForm;
import com.storebuilder.storebuilder.model.User;
import com.storebuilder.storebuilder.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(
            @Valid @ModelAttribute("loginForm") LoginForm loginForm,
            BindingResult bindingResult,
            HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        return userService.authenticate(loginForm.getEmail(), loginForm.getPassword())
                .map(user -> {
                    session.setAttribute("usuarioActual", user);
                    return "redirect:/";
                })
                .orElseGet(() -> {
                    bindingResult.reject("login.failed", "Correo o contraseña incorrectos");
                    return "login";
                });
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(
            @Valid @ModelAttribute("registrationForm") RegistrationForm registrationForm,
            BindingResult bindingResult,
            HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.emailExists(registrationForm.getCorreo())) {
            bindingResult.rejectValue("correo", "error.correo", "Ya existe una cuenta con ese correo");
            return "register";
        }

        User user = userService.register(
                registrationForm.getNombre(),
                registrationForm.getApellido(),
                registrationForm.getCorreo(),
                registrationForm.getPassword());

        session.setAttribute("usuarioActual", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("usuarioActual");
        return "redirect:/";
    }
}
