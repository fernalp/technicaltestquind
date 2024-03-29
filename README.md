# API de Prueba Técnica de Quind con Spring Boot

Este proyecto contiene una API desarrollada con Spring Boot que proporciona los servicios descritos en la prueba técnica de la empresa Quind.

## Requisitos

- Java 17 o superior
- Entorno de Desarrollo Integrado (IDE)

## Instalación

1. Clona este repositorio: `git clone https://github.com/fernalp/technicaltestquind.git`

## Uso

1. Ejecuta la aplicación.
2. Accede a la API en `http://localhost:8080`

## Documentación de la API

La documentación de la API se genera automáticamente mediante Swagger. Después de iniciar la aplicación, puedes acceder a la documentación en `http://localhost:8080/swagger-ui.html`.

## Endpoints Principales

- `GET /api/v1/customers`: Obtiene todos los clientes.
- `GET /api/v1/customers/{id}`: Obtiene un cliente por su ID.
- `POST /api/v1/customers`: Crea un nuevo cliente.
- `PUT /api/v1/customers/{id}`: Actualiza un cliente existente.
- `DELETE /api/v1/customers/{id}`: Elimina un cliente existente.

