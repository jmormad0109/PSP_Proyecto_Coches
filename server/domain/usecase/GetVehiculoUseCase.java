package server.domain.usecase;

import server.data.repository.VehiculoRepository;
import server.domain.interfaces.RestInterface;
import server.domain.model.Vehiculo;

public class GetVehiculoUseCase implements RestInterface{
    private VehiculoRepository vehiculoRepository;

    public GetVehiculoUseCase(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public String execute(String[] parametros) {
        if (parametros.length != 2) {
            return "Error, el formato de la petición es incorrecto";
        }

        try {
            int id = Integer.parseInt(parametros[1]);
            Vehiculo vehiculo = vehiculoRepository.getVehiculoById(id);

            if (vehiculo != null) {
                return "Vehículo encontrado: ID: " + vehiculo.getId() + ", Modelo: " + vehiculo.getModelo() + ", Cilindrada: " + vehiculo.getCilindrada();
            }else{
                return "Error, no se ha encontrado el vehículo";
            }
        } catch (NumberFormatException e) {
            return "Error, el valor del ID no es un número";
        }
    }

    
}
