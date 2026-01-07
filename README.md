# Sistema Bancario - Microservicios

Este proyecto implementa una solución de microservicios para un sistema bancario utilizando **Spring Boot**, **MySQL** y **Docker**.

## 🚀 Estructura del Proyecto

El sistema consta de dos microservicios principales y una base de datos compartida:

*   **client-service (Puerto 8081)**: Gestiona la información de los clientes (Personas).
*   **account-service (Puerto 8089)**: Gestiona las cuentas bancarias y los movimientos.
*   **mysqldb (Puerto 3306)**: Base de datos MySQL 8.0 compartida.

## 📋 Prerrequisitos

*   **Docker** y **Docker Compose** instalados y ejecutándose.
*   No es necesario tener Java o Maven instalados localmente para el despliegue, ya que se utilizan contenedores Docker multi-stage.

## 🛠️ Instrucciones de Despliegue

1.  Abre una terminal en la carpeta raíz `services/`.
2.  Ejecuta el siguiente comando para construir y levantar todos los servicios:

    ```bash
    docker-compose up --build
    ```

3.  Espera a que finalice la construcción. El sistema estará listo cuando veas logs indicando que las aplicaciones han iniciado en los puertos 8081 y 8089.

    > **Nota**: La primera vez puede tardar unos minutos ya que MySQL debe inicializarse y ejecutar el script `schema.sql`.

4.  Para detener el sistema:
    ```bash
    docker-compose down
    ```
    *(Usa `docker-compose down -v` si necesitas borrar la base de datos y empezar de cero).*

## 🧪 Pruebas (Endpoints)

Puedes probar la API utilizando **Postman** o `curl`.

### 1. Microservicio de Clientes (Puerto 8081)

**Crear Cliente**
*   **URL**: `http://localhost:8081/clientes`
*   **Método**: `POST`
*   **Body (JSON)**:
    ```json
    {
        "Nombres": "Jose Lema",
        "Dirección": "Otavalo sn y principal",
        "Teléfono": "098254785",
        "Contraseña": "1234",
        "Estado": true,
        "Identificacion": "1234567890",
        "Genero": "Masculino",
        "Edad": 30,
        "ClienteId": "jose.lema"
    }
    ```

### 2. Microservicio de Cuentas (Puerto 8089)

**Crear Cuenta**
*   **URL**: `http://localhost:8089/cuentas`
*   **Método**: `POST`
*   **Body (JSON)**:
    ```json
    {
        "Numero Cuenta": "478758",
        "Tipo": "Ahorros",
        "Saldo Inicial": 2000.00,
        "Estado": true,
        "Cliente": "Jose Lema",
        "Identificacion Cliente": "1234567890"
    }
    ```

**Realizar Movimiento (Depósito/Retiro)**
*   **URL**: `http://localhost:8089/movimientos`
*   **Método**: `POST`
*   **Body (JSON)**:
    ```json
    {
        "Numero Cuenta": "478758",
        "Movimiento": 575.00
    }
    ```
    *(Usa valores negativos para retiros, ej: -100.00)*

**Generar Reporte**
*   **URL**: `http://localhost:8089/movimientos/reportes`
*   **Método**: `GET`
*   **Parámetros**:
    *   `fechaInicio`: `2024-01-01`
    *   `fechaFin`: `2025-12-31`
    *   `cliente`: `1234567890` (Debe coincidir con la identificación del cliente)

## ⚠️ Solución de Problemas Comunes

*   **Error "Ports are not available"**: Asegúrate de detener cualquier instancia de la aplicación que estés corriendo en tu IDE (IntelliJ/Eclipse) antes de ejecutar Docker.
*   **Error de conexión a Base de Datos**: Si ves errores de "Communications link failure" al inicio, espera unos segundos. El `docker-compose` está configurado con un *Healthcheck* para reiniciar los servicios automáticamente una vez que MySQL esté listo.
