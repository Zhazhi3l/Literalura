# 📚 Literalura: Biblioteca Digital Relacional 📚 

<p align="center">
  <img src="https://img.shields.io/badge/ESTADO-En%20progreso-yellow">
</p>

## 📖 Tabla de Contenidos
1. [Introducción](#📌 ¿De qué trata el proyecto "Literalura"?)
2. [Características]
3. [Acceso al proyecto]
4. [Tecnologías utilizadas]
5. [Uso del sistema]
6. [Autor]

## 📌 ¿De qué trata el proyecto "Literalura"?

  Literalura es una aplicación backend que permite consultar, deserializar y almacenar libros provenientes de una API externa, asegurando persistencia relacional en la base de datos. El sistema evita duplicaciones y mantiene la integridad entre autores y sus obras mediante una arquitectura basada en JPA y Spring Boot.

## 📚 Características y funcionamiento 

Literalura funciona como un catálogo relacional en el que cada libro está vinculado a un autor. El sistema realiza:

### 🔎 Búsqueda de libros
- Consulta de libros por título en una API externa.
- Deserialización de datos JSON en objetos DTO.
- Validación de existencia previa en la BD para evitar duplicados.
### 👤 Gestión de autores
- Verificación de si el autor del libro ya existe en la base.
- Creación del autor si es nuevo, con relación bidireccional autor ↔ libro.
- Consulta de autores vivos en un año determinado.
### 🌐 Relación robusta
- Cada libro está relacionado con su autor.
- Si un autor ya existe, se reutiliza y se le agrega el nuevo libro.
- Uso de cascade para garantizar persistencia encadenada.

## 📁 ¿Cómo acceder al proyecto 📂</h2>
### 💻 A través de Git 💻</h3>

```
git clone https://github.com/Zhazhi3l/Literalura.git
```

### 🔧 Configuración previa
- Tener PostgreSQL instalado y levantado en localhost:5432.
- Crear la base de datos llamada literalura.
- Actualizar las credenciales en application.properties o exportar variables de entorno.
<br>Ejemplo en application.properties:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## ✅ Tecnologías utilizadas 
<p align="center"><img src="https://upload.wikimedia.org/wikipedia/commons/3/35/Spring_Logo.png" alt="Spring" width="90"><img src="https://upload.wikimedia.org/wikipedia/commons/3/38/Jpa_logo.png" alt="JPA" width="70"><img src="https://upload.wikimedia.org/wikipedia/commons/2/29/Postgresql_elephant.svg" alt="PostgreSQL" width="60"><img src="https://upload.wikimedia.org/wikipedia/commons/b/b2/Java_logo_icon.svg" alt="Java" width="60"></p>

## 🔨 ¿Cómo utilizar Literalura? 🔨 
### Paso 1: Ejecuta el proyecto
Compila y ejecuta con Maven o desde tu IDE (Requiere Java 17+).

### Paso 2: Usa el menú interactivo
Al iniciar se desplegará el menú principal para interactuar:
- Buscar libro por título
- Consultar libros por idioma
- Buscar autores vivos en un año
- Mostrar todos los libros y autores registrados
### Paso 3: Revisa la base de datos
Todos los libros y autores quedan guardados en PostgreSQL con relaciones definidas (autor_id en libros).

## 💡 Autor 
[¡Puedes visitar mi perfil de LinkedIn!](https://www.linkedin.com/in/hazzav/)
<p align="center">
  <img src="https://upload.wikimedia.org/wikipedia/commons/c/ca/LinkedIn_logo_initials.png" width="50">
</p>
