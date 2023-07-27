# Este ejercicio corresponde a una prueba tecnica dentro de un proceso de postulacion real
## El ejercicio:

## Desafio técnico Mantenedor 1
## Crear una aplicación Web [front - back] que permita realizar el registro de tareas, con las
siguientes funcionalidades:
- Listar tareas
- Agregar una tarea
- Remover una tarea
- Editar una tarea
## Consideraciones
### Front
- Utilizar las librerías React + Redux
- Validar que los controles de entrada sean obligatorios
- Crear pruebas unitarias
### Back
• Construir utilizando el framework Spring Boot
- Exponer mediate API REST
- Utilizar Swagger para documentar las API
- Validar que en las acciones agregar/editar sean obligatorios los campos de entrada
- Utilizar para la persistencia lo que más le acomode [ORM o nativo JDBCI
- Crear pruebas unitarias
### DB
- Utilizar el motor DB que más le acomode [relacional, NoSql]
- La estructura de la tarea deberá contener los siguientes campos: identificador[numérico],
descripción [cadena], fechaCreación [fecha tempo], vigente [booleano]

## Entrega
- Compartir en un repositorio git el código fuente del front, back y script de base de datos.
Además crear instructivo que indique como desplegar el aplicativo.

# Para ejecutar
- Correr el `main` de la clase `com.fgonzalez.pruebatecnica.TareaApp` desde tu IDE favorito.

- Tambien puedes correr el siguiente comando en terminal
  
```
mvn spring-boot:run
```

## Esto despliega una interfaz swagger en la siguiente URL [swagger ui](http://localhost:8080/swagger-ui/) 
## Tecnologies
- Java
    - Spring Boot
    - Lombok
    - Spring Web
    - H2 DB
    - Spring Data JPA
    - MapStruct
    - Swagger
    - JUnit (test)
    - Mockito (test)
