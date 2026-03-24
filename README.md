# 🚀 Usuario CRUD API - Spring Boot 3

Este proyecto es un microservicio para la gestión de usuarios (CRUD), desarrollado con **Spring Boot 3**, siguiendo patrones de **Arquitectura Limpia** y las mejores prácticas de desarrollo backend.

---

## 🛠️ Stack Tecnológico
- **Java 21**
- **Spring Boot 3.4.2**
- **Spring Data JPA** (Persistencia)
- **PostgreSQL** (Base de Datos)
- **Lombok** (Productividad)
- **Jakarta Validation** (Validaciones)
- **Springdoc OpenAPI (Swagger)** (Documentación)
- **Docker** (Contenerización)

---

## 🏗️ Arquitectura del Proyecto
La aplicación está organizada por capas para facilitar el mantenimiento y la escalabilidad:
- `controller`: Expone los endpoints REST.
- `service`: Contiene la lógica de negocio (Interfaz + Impl).
- `repository`: Interfaz para la interacción con PostgreSQL.
- `model`: Entidades JPA que mapean a la base de datos.
- `dto`: Objetos de transferencia de datos independientes de las entidades.
- `exception`: Manejo de errores personalizado y global.
- `config`: Configuraciones de Swagger y componentes del sistema.

---

## 🔗 Endpoints principales (CRUD)
Base URL: `/api/usuarios`

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/usuarios` | Lista todos los usuarios. |
| **GET** | `/api/usuarios/{id}` | Obtiene un usuario por su ID. |
| **POST** | `/api/usuarios` | Crea un nuevo usuario (con validación de email único). |
| **PUT** | `/api/usuarios/{id}` | Actualiza datos de un usuario existente. |
| **DELETE** | `/api/usuarios/{id}` | Elimina un usuario. |
| **GET** | `/api/usuarios/test` | Endpoint de verificación rápida. |

### 📖 Documentación Interactiva
Una vez ejecutada la aplicación, puedes acceder a la interfaz de **Swagger UI** en:
[http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

---

## 💻 Configuración Local

### 1. Requisitos
- Java 21+
- Maven 3.9+
- PostgreSQL (Instalado o una instancia en la nube como Supabase)

### 2. Variables de Entorno
Crea un archivo `.env.properties` o configura las siguientes variables en tu sistema:
```ini
DB_URL=jdbc:postgresql://tu-host:5432/tu-db
DB_USER=tu-usuario
DB_PASSWORD=tu-contraseña
PORT=9090
```

### 3. Ejecutar
```bash
mvn clean spring-boot:run
```

---

## 🐳 Despliegue con Docker

### Construir la imagen localmente:
```bash
docker build -t lab02-nube .
```

### Ejecutar el contenedor:
```bash
docker run -p 8080:8080 --env-file .env.properties lab02-nube
```

---

## ☁️ Despliegue en Render (Recomendado)

1. Sube tu código a un repositorio en **GitHub** o **GitLab**.
2. En Render, crea un nuevo **Web Service**.
3. Selecciona tu repositorio y usa el **Dockerfile** proporcionado.
4. En la sección **Environment**, agrega las variables críticas:
   - `DB_URL`
   - `DB_USER`
   - `DB_PASSWORD`
   - `PORT` (Render lo maneja automáticamente, pero asegúrate de que Spring lo use).

> [!NOTE]
> El archivo `application.properties` ya está configurado para cargar el puerto desde la variable `${PORT}` que inyecta Render.

---

## 🛡️ Validaciones y Errores
- **Email Único**: No se permite registrar dos usuarios con el mismo correo.
- **Validación de Campos**: `nombre` y `email` son obligatorios; `email` debe tener un formato válido.
- **Global Error Handler**: Devuelve respuestas JSON estandarizadas ante errores `404`, `400` y `500`.
