package com.storebuilder.storebuilder.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginForm {

    @NotBlank(message = "El correo es requerido")
    @Email(message = "Debes ingresar un correo válido")
    private String email;

    @NotBlank(message = "La contraseña es requerida")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
