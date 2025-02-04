# Proyecto Coches

Aplicación sencilla sobre la conexión entre un Servidor, y varios Clientes por terminal, usando el protocolo TCP y Sockets para la conexión.

## Comandos/EndPoints


| Método | Comando | Descripción |
|----------|----------|----------|
| GET   | get id   | El servidor devuelve el vehículo con el ID indicado|
| GETAll   | getAll  | El servidor devuelve todos los IDs de los vehículos que tiene guardados |
| POST   | post modelo cilindrada   | Añade un nuevo vehículo |
| PUT   | put id modelo cilindrada  | Edita el vehículo con el ID indicado con los atributos nuevos |
| DELETE   | delete id   | Elimina el vehículo con el ID indicado |

### Atributos clase Vehículo
```java
    int id;
    String modelo;
    int cilindrada;
```


### Diagrama UML (Diagrama de Clases)
