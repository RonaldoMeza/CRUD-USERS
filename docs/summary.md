# Resumen de Implementación CRUD Usuarios

Se ha implementado un microservicio CRUD para la entidad **Usuario** siguiendo principios de **Clean Architecture** y las mejores prácticas de desarrollo con **Spring Boot 3**.

## 🚀 Tecnologías y Herramientas
- **Spring Boot 3.5.12**
- **Java 21**
- **Spring Data JPA**: Para la persistencia de datos.
- **PostgreSQL**: Base de datos relacional.
- **Lombok**: Reducción de código boilerplate (Getters, Setters, Builders).
- **Jakarta Validation**: Validaciones en los DTOs y la entidad.
- **Springdoc OpenAPI (Swagger)**: Documentación interactiva de la API.

## 🏗️ Estructura del Proyecto
```text
com.lab02
├── config           # Configuración de Swagger
├── controller       # Endpoints REST
├── dto              # Objetos de Transferencia de Datos (Request/Response)
├── exception        # Excepciones personalizadas y Global Handler
├── model            # Entidad Usuario
├── repository       # Interfaz JpaRepository
└── service          # Lógica de negocio (Interface e Impl)
```

## 🔗 Endpoints (CRUD)
Base URL: `/api/usuarios`

- `GET /api/usuarios`: Lista todos los usuarios.
- `GET /api/usuarios/{id}`: Obtiene un usuario específico por su ID.
- `POST /api/usuarios`: Crea un nuevo usuario.
- `PUT /api/usuarios/{id}`: Actualiza los datos de un usuario existente.
- `DELETE /api/usuarios/{id}`: Elimina un usuario por ID.

## 🛠️ Configuración de Base de Datos
- **Estrategia JPA**: `hibernate.ddl-auto=validate`. Esto garantiza que el código sea compatible con el esquema de base de datos existente sin intentar modificarlo.
- **Variables de Entorno**: Se utilizan `DB_URL`, `DB_USER` y `DB_PASSWORD` para cargar las credenciales de forma dinámica desde el entorno.

## 🛡️ Validaciones y Errores
- Se valida que el `nombre` y `email` sean obligatorios.
- Se valida el formato del `email`.
- Se implementó una lógica de validación para asegurar que el `email` sea único en la base de datos.
- El `GlobalExceptionHandler` captura errores comunes como `404 Not Found` y `400 Bad Request` (errores de validación) y devuelve un formato JSON consistente.

## 📄 Documentación API
La documentación interactiva puede visualizarse en:
`http://localhost:9090/swagger-ui.html` (o el puerto configurado).
