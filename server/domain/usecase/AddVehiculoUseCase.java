package server.domain.usecase;

import server.data.repository.VehiculoRepository;
import server.domain.interfaces.RestInterface;
import server.domain.model.Vehiculo;

public class AddVehiculoUseCase implements RestInterface{
    private VehiculoRepository vehiculoRepository;

    public AddVehiculoUseCase(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public String execute(String[] parametros) {
        if(parametros.length != 3){
            return "Error, el formato de la petición es incorrecto";
        }

        try {
            String modelo = parametros[1];
            int cilindrada = Integer.parseInt(parametros[2]);
            Vehiculo vehiculo = vehiculoRepository.addVehiculo(modelo, cilindrada);

            if (vehiculo != null) {
                return "Vehículo agregado correctamente.";
            }else{
                return "Error, No se ha podido agrear el vehículo";
            }
        } catch (NumberFormatException e) {
            return "Error, el valor de la cilindrada no es un numero";
        }
    }
    
    
}
