package server.domain.usecase;

import java.util.List;

import server.data.repository.VehiculoRepository;
import server.domain.interfaces.RestInterface;

public class GetAllVehiculosUseCase implements RestInterface{
    private VehiculoRepository vehiculoRepository;

    public GetAllVehiculosUseCase(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    @Override
    public String execute(String[] parametros) {
        if (parametros.length != 1) {
            return "Erro, el formato de la petición es incorrecto";
        }

        List<Integer> ids = vehiculoRepository.getAllId();
        return "Vehículos registrado: " + ids.toString();
    }

}
