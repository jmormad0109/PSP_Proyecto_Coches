package server.domain.usecase;

import server.data.repository.VehiculoRepository;
import server.domain.interfaces.RestInterface;

public class UpdateVehiculoUseCase implements RestInterface{
    private VehiculoRepository vehiculoRepository;

    public UpdateVehiculoUseCase(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public String execute(String[] parametros) {
        if (parametros.length != 4) {
            return "Error, el formato de la petición es incorrecto";
        }

        try {
            int id = Integer.parseInt(parametros[1]);
            String modelo = parametros[2];
            int cilindrada = Integer.parseInt(parametros[3]);

            if (vehiculoRepository.updateVehiculo(id, modelo, cilindrada)) {
                return "Vehículo actualizado de forma correcta";
            }else{
                return "Error, no se pudo actualizar el vehículo";
            }
        } catch (NumberFormatException e) {
            return "Error, alguno de los valores numericos no es correcto";
        }
    }

    
}
