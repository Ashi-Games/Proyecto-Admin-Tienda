package com.storebuilder.storebuilder.service;

import com.storebuilder.storebuilder.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        save(new User("Administrador", "admin@storebuilder.com", "admin123", "admin"));
        save(new User("Juan Pérez", "cliente@tienda.com", "123456", "cliente"));
    }

    public Optional<User> authenticate(String email, String password) {
        if (email == null || password == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(email.toLowerCase()))
                .filter(user -> user.getPassword().equals(password));
    }

    public boolean emailExists(String email) {
        return email != null && users.containsKey(email.toLowerCase());
    }

    public User register(String nombre, String apellido, String correo, String password) {
        String nombreCompleto = String.format("%s %s", nombre.trim(), apellido.trim());
        User user = new User(nombreCompleto, correo.toLowerCase(), password, "cliente");
        return save(user);
    }

    public Optional<User> findByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(users.get(email.toLowerCase()));
    }

    private User save(User user) {
        users.put(user.getEmail().toLowerCase(), user);
        return user;
    }
}
