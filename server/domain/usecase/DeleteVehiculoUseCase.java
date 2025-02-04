package server.domain.usecase;

import server.data.repository.VehiculoRepository;
import server.domain.interfaces.RestInterface;

public class DeleteVehiculoUseCase implements RestInterface{
    private VehiculoRepository vehiculoRepository;

    public DeleteVehiculoUseCase(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public String execute(String[] parametros) {
       if (parametros.length != 2) {
            return "Error, el formato de la petición es incorrecto";
       }

       try {
            int id = Integer.parseInt(parametros[1]);
            if (vehiculoRepository.deleteVehiculo(id)) {
                return "Vehículo eleiminado correctamente";
            }else{
                return "Error, no se ha podido eliminar el vehículo";
            }
       } catch (NumberFormatException e) {
            return "Error, el valor del ID no es un número";
       }
    }

    
}
