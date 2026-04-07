// Validación del formulario de Login
        document.getElementById('loginForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            let isValid = true;
            const email = document.getElementById('loginEmail');
            const password = document.getElementById('loginPassword');
            
            // Validar email
            if (!email.value.includes('@') || email.value.length < 5) {
                email.classList.add('is-invalid');
                isValid = false;
            } else {
                email.classList.remove('is-invalid');
                email.classList.add('is-valid');
            }
            
            // Validar contraseña
            if (password.value.length < 6) {
                password.classList.add('is-invalid');
                isValid = false;
            } else {
                password.classList.remove('is-invalid');
                password.classList.add('is-valid');
            }
            
            if (isValid) {
                alert('¡Inicio de sesión exitoso! 🎉\n\n(Esto es una simulación)');
                const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
                modal.hide();
                
                // Limpiar formulario
                this.reset();
                email.classList.remove('is-valid');
                password.classList.remove('is-valid');
            }
        });

        // Validación del formulario de Registro
        document.getElementById('registerForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            let isValid = true;
            const nombre = document.getElementById('regNombre');
            const apellido = document.getElementById('regApellido');
            const email = document.getElementById('regEmail');
            const password = document.getElementById('regPassword');
            const terms = document.getElementById('terms');
            
            // Validar nombre y apellido
            if (nombre.value.trim().length < 2) {
                nombre.classList.add('is-invalid');
                isValid = false;
            } else nombre.classList.remove('is-invalid');
            
            if (apellido.value.trim().length < 2) {
                apellido.classList.add('is-invalid');
                isValid = false;
            } else apellido.classList.remove('is-invalid');
            
            // Validar email
            if (!email.value.includes('@')) {
                email.classList.add('is-invalid');
                isValid = false;
            } else email.classList.remove('is-invalid');
            
            // Validar contraseña
            if (password.value.length < 6) {
                password.classList.add('is-invalid');
                isValid = false;
            } else password.classList.remove('is-invalid');
            
            // Validar términos
            if (!terms.checked) {
                alert("Debes aceptar los términos y condiciones");
                isValid = false;
            }
            
            if (isValid) {
                alert('¡Cuenta creada exitosamente! 🎉\n\nBienvenido a NegocioPro');
                const modal = bootstrap.Modal.getInstance(document.getElementById('registerModal'));
                modal.hide();
                
                // Limpiar formulario
                this.reset();
            }
        });