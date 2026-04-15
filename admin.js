// admin.js

// Usuarios predefinidos
const usuariosPredefinidos = [
    {
        nombre: "Juan Pérez",
        email: "cliente@tienda.com",
        password: "123456",
        rol: "cliente"
    },
    {
        nombre: "Administrador",
        email: "admin@storebuilder.com",
        password: "admin123",
        rol: "admin"
    }
];

function inicializarUsuarios() {
    if (!localStorage.getItem('usuarios')) {
        localStorage.setItem('usuarios', JSON.stringify(usuariosPredefinidos));
    }
}

function obtenerUsuarios() {
    return JSON.parse(localStorage.getItem('usuarios')) || [];
}

function guardarUsuario(usuario) {
    const usuarios = obtenerUsuarios();
    usuarios.push(usuario);
    localStorage.setItem('usuarios', JSON.stringify(usuarios));
}

// Función para actualizar la navbar según si hay sesión
function actualizarNavbar() {
    const usuarioActual = JSON.parse(localStorage.getItem('usuarioActual'));
    const authDiv = document.getElementById('authButtons');

    if (usuarioActual) {
        // Usuario logueado
        authDiv.innerHTML = `
            <span class="me-3 text-dark">
                Hola, <strong>${usuarioActual.nombre}</strong>
            </span>
            <button onclick="cerrarSesion()" class="btn btn-outline-danger">
                Cerrar Sesión
            </button>
        `;
    } else {
        // Sin sesión
        authDiv.innerHTML = `
            <button class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#loginModal">
                Iniciar sesión
            </button>
            <button class="btn btn-primary btn-cta" data-bs-toggle="modal" data-bs-target="#registerModal">
                Registrarse
            </button>
        `;
    }
}

function cerrarSesion() {
    localStorage.removeItem('usuarioActual');
    alert("Sesión cerrada correctamente");
    actualizarNavbar();
}

// ==================== LOGIN ====================
document.getElementById('loginForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const email = document.getElementById('loginEmail').value.trim();
    const password = document.getElementById('loginPassword').value.trim();

    const usuarios = obtenerUsuarios();
    const usuarioEncontrado = usuarios.find(u => u.email === email && u.password === password);

    if (usuarioEncontrado) {
        localStorage.setItem('usuarioActual', JSON.stringify(usuarioEncontrado));
        alert(`¡Bienvenido, ${usuarioEncontrado.nombre}!`);
        
        const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
        modal.hide();
        
        actualizarNavbar();
        
    } else {
        alert("Correo o contraseña incorrectos.");
    }
});

// ==================== REGISTRO ====================
document.getElementById('registerForm').addEventListener('submit', function(e) {
    e.preventDefault();

    const nombre = document.getElementById('regNombre').value.trim();
    const apellido = document.getElementById('regApellido').value.trim();
    const email = document.getElementById('regEmail').value.trim();
    const password = document.getElementById('regPassword').value.trim();
    const terms = document.getElementById('terms').checked;

    if (nombre.length < 2 || apellido.length < 2 || !email.includes('@') || password.length < 6 || !terms) {
        alert("Por favor completa todos los campos correctamente.");
        return;
    }

    const nuevoUsuario = {
        nombre: `${nombre} ${apellido}`,
        email: email,
        password: password,
        rol: "cliente"
    };

    guardarUsuario(nuevoUsuario);
    alert(`¡Registro exitoso! Bienvenido ${nombre}`);

    const modal = bootstrap.Modal.getInstance(document.getElementById('registerModal'));
    modal.hide();
    this.reset();

    setTimeout(() => {
        new bootstrap.Modal(document.getElementById('loginModal')).show();
    }, 700);
});

// ==================== RECUPERAR ====================
document.getElementById('forgotForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const email = document.getElementById('forgotEmail').value.trim();
    alert(`Enlace de recuperación enviado a: ${email}`);
    bootstrap.Modal.getInstance(document.getElementById('forgotModal')).hide();
    this.reset();
});

// Inicializar todo
document.addEventListener('DOMContentLoaded', function() {
    inicializarUsuarios();
    actualizarNavbar();
});s