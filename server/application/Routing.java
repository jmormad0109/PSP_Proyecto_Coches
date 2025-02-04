package server.application;

import java.util.HashMap;
import java.util.Map;

import server.data.repository.VehiculoRepository;
import server.domain.interfaces.RestInterface;
import server.domain.usecase.AddVehiculoUseCase;
import server.domain.usecase.DeleteVehiculoUseCase;
import server.domain.usecase.GetAllVehiculosUseCase;
import server.domain.usecase.GetVehiculoUseCase;
import server.domain.usecase.UpdateVehiculoUseCase;

public class Routing {
    Map<String, RestInterface> manejadorEndPoints;
    

    public Routing(VehiculoRepository vehiculoRepository){
        this.manejadorEndPoints = new HashMap<>();

        manejadorEndPoints.put("post", new AddVehiculoUseCase(vehiculoRepository));
        manejadorEndPoints.put("get", new GetVehiculoUseCase(vehiculoRepository));
        manejadorEndPoints.put("getall", new GetAllVehiculosUseCase(vehiculoRepository));
        manejadorEndPoints.put("put", new UpdateVehiculoUseCase(vehiculoRepository));
        manejadorEndPoints.put("delete", new DeleteVehiculoUseCase(vehiculoRepository));

        System.out.println("Endpoints registrados: " + manejadorEndPoints.keySet());

    }

    public String procesarPeticion(String peticion) { 
        String[] partes = peticion.split("\\s+");
        if (partes.length == 0) {
            return "Error, petición vacia";
        }

        String comando = partes[0].toLowerCase();
        RestInterface casoUso = manejadorEndPoints.get(comando);

        if (casoUso != null) {
            return casoUso.execute(partes);
        } else {
            return "Error, comando desconocido";
        }
    }
}
