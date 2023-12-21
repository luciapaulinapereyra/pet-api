# Pet Lost and Found API
The Pet Lost and Found API is a simple application designed to publish information about lost pets. It provides endpoints for registering new users, logging in, and posting details about lost pets. This project is developed in Java using the Spring Boot framework and uses JWT (JSON Web Tokens) for authentication.

## Requisitos previos
1. Clona este repositorio: git clone <URL_DEL_REPOSITORIO>
2. Navega al directorio del proyecto: cd pet-lost-found-api

## Configuración de la base de datos
1. Asegúrate de tener un servidor MySQL en ejecución en localhost:3306.
2. Crea un esquema (base de datos) para la aplicación.
3. Actualiza las credenciales de la base de datos en el archivo application.properties ubicado en src/main/resources.

## Ejecutar la aplicación
`mvn spring-boot:run`

## Endpoints
### Publicar mascota perdida
- Endpoint: /api/pets
- Método: POST
- Descripción: Registra una nueva mascota en el sistema.
- Cuerpo de la solicitud:
```json
{
    "id": null,
    "name": "Lola",
    "years": 2,
    "description": "A big white dog",
    "category": "Dog",
    "picture": "urlImage",
    "lost_zone": "Buenos Aires"
}
```
### Obtener mascotas perdidas
- Endpoint: /api/pets/all
- Método: GET
- Descripción: Devuelve una página de mascotas perdidas

### Obtener mascotas perdidas
- Endpoint: /api/pets/{category}
- Método: GET
- Descripción: Devuelve las mascotas de la categoría enviada por params
- Params: category

### Editar una mascota
- Endpoint: /api/{id}
- Método: PUT
- Descripción: Edita la mascota con ID enviado por params
- Params: ID
- Cuerpo de la solicitud:
```json
{
    "id": null,
    "name": "Lola",
    "years": 2,
    "description": "A big white dog",
    "category": "Dog",
    "picture": "urlImage",
    "lost_zone": "Buenos Aires"
}
```

### Eliminar una mascota
- Endpoint: /api/{id}
- Método: DELETE
- Descripción: Elimina la mascota con ID enviado por params
- Params: ID


## Notas adicionales
- Este proyecto aún no está dockerizado, pero se considerará para futuras versiones.
- Asegúrate de tener las dependencias de Maven instaladas para compilar y ejecutar la aplicación.
- Se recomienda utilizar una herramienta como Postman para interactuar con la API.
