# Proyecto Coches

Aplicación sencilla sobre la conexión entre un Servidor, y varios Clientes por terminal, usando el protocolo TCP y Sockets para la conexión.

Para lanzar el Servidor, abriremos una terminal y nos iremos hasta la ruta de la carpeta raíz del proyecto, y ejecutaremos el siguiente comando:

```
    java -cp bin server.main.Servidor
```

Una vez iniciado el Servidor, desde una nueva terminal, nos iremos a la misma ruta, la raíz del proyecto, y ejecutaremos el siguinte comando para iniciar un Cliente:

```
    java -cp bin client.Cliente
```
Repetiremos el proceso del Cliente, tantas veces como clientes queramos conectar al servidor.

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

#### Imágen del Diagrama
![alt text](image.png)

#### Código de PlantUML
```plantuml
    @startuml
class Cliente {
    +main()
    +sendRequest()
}

class Servidor {
    +main()
    +acceptClient()
}

class VehiculoDataThread {
    -Socket socket
    -Routing routing
    +run()
    +processRequest()
}

class Routing {
    -Map<String, RestInterface> manejadorEndPoints
    +procesarPeticion()
}

class VehiculoRepository {
    -List<Vehiculo> vehiculos
    -AtomicInteger id
    +addVehiculo(modelo: String, cilindrada: int): Vehiculo
    +getVehiculoById(id: int): Vehiculo
    +getAllId(): List<Integer>
    +updateVehiculo(id: int, modelo: String, cilindrada: int): boolean
    +deleteVehiculo(id: int): boolean
}

class Vehiculo {
    -int id
    -String modelo
    -int cilindrada
    +getId()
    +getModelo()
    +getCilindrada()
    +setModelo(modelo: String)
    +setCilindrada(cilindrada: int)
}

interface RestInterface {
    +execute(params: String[]): String
}

class AddVehiculoUseCase
class GetVehiculoUseCase
class UpdateVehiculoUseCase
class DeleteVehiculoUseCase
class GetAllVehiculosUseCase

Cliente --> Servidor : Conecta a
Servidor --> VehiculoDataThread : Crea un hilo por cliente
VehiculoDataThread --> Routing : Procesa peticiones
Routing --> VehiculoRepository : Llama a métodos
VehiculoRepository --> Vehiculo : Administra instancias

RestInterface <|-- AddVehiculoUseCase
RestInterface <|-- GetVehiculoUseCase
RestInterface <|-- UpdateVehiculoUseCase
RestInterface <|-- DeleteVehiculoUseCase
RestInterface <|-- GetAllVehiculosUseCase

Routing --> AddVehiculoUseCase
Routing --> GetVehiculoUseCase
Routing --> UpdateVehiculoUseCase
Routing --> DeleteVehiculoUseCase
Routing --> GetAllVehiculosUseCase
@enduml

```

