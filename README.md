# Concurrente Magia

Concurrente Magia es un juego de aventuras mágicas donde los usuarios se enfrentan a varios desafiantes en diferentes niveles. Cada nivel aumenta en dificultad, y los usuarios deben usar sus hechizos sabiamente para derrotar a sus oponentes.

## Requisitos

- Java 11 o superior
- Spring Boot 2.5.4 o superior
- Maven 3.6.3 o superior
- Base de datos H2 (integrada)

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/concurrente-magia.git
    cd concurrente-magia
    ```

2. Compila y ejecuta la aplicación:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

3. Accede a la aplicación en tu navegador:
    ```
    http://localhost:8080
    ```

## Estructura del Proyecto

### Configuración de Seguridad

`SecurityConfig.java` configura la seguridad de la aplicación, incluyendo la autenticación y autorización de usuarios.

### Controladores

- `HomeController.java`: Controlador para la página de inicio.
- `MagicalEventController.java`: Controlador para gestionar eventos mágicos.
- `SpellController.java`: Controlador para gestionar hechizos.
- `UserController.java`: Controlador para el registro y login de usuarios.

### Modelos

- `MagicalEvent.java`: Representa un evento mágico.
- `Spell.java`: Representa un hechizo.
- `User.java`: Representa un usuario.
- `SpellType.java`: Enumeración de los tipos de hechizos.

### Repositorios

- `MagicalEventRepository.java`: Repositorio para eventos mágicos.
- `SpellRepository.java`: Repositorio para hechizos.
- `UserRepository.java`: Repositorio para usuarios.

### Servicios

- `MagicalEventService.java`: Lógica de negocio para eventos mágicos.
- `SpellService.java`: Lógica de negocio para hechizos.
- `UserService.java`: Lógica de negocio para usuarios.
- `UserDetailsImpl.java`: Implementación de `UserDetails` para la autenticación.

### Aspectos

- `UserActivityAspect.java`: Aspecto para registrar actividades de usuario como registro y login.

### Plantillas

- `home.html`: Página de inicio.
- `login.html`: Página de login.
- `registration.html`: Página de registro.
- `spells.html`: Página para listar y gestionar hechizos.
- `spell-form.html`: Formulario para añadir o editar hechizos.
- `challenge.html`: Página para el desafío de eventos mágicos.
- `victory.html`: Página de victoria.
- `defeat.html`: Página de derrota.
- `final.victory.html`: Página de victoria final.

## Uso

### Registro y Login

1. Regístrate en la aplicación en la página de registro (`/registration`).
2. Inicia sesión en la página de login (`/login`).

### Gestión de Hechizos

1. Añade nuevos hechizos en la página de hechizos (`/spells/new`).
2. Lista y gestiona tus hechizos en la página de hechizos (`/spells`).

### Desafíos de Eventos Mágicos

1. Participa en desafíos de eventos mágicos en la página de desafío (`/events/challenge`).
2. Usa tus hechizos para derrotar a los desafiantes y avanzar a los siguientes niveles.

