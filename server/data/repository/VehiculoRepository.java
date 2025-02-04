package server.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import server.domain.model.Vehiculo;

public class VehiculoRepository {
    
    private List<Vehiculo> vehiculos;
    private AtomicInteger id;
    

    public VehiculoRepository(){
        vehiculos = new ArrayList<Vehiculo>();
        id = new AtomicInteger(0);

        cargarDatos();
    }

    private void cargarDatos(){
        addVehiculo("mercedes", 2000);
        addVehiculo("alfa", 2200);
        addVehiculo("audi", 2500);
        addVehiculo("bmw", 3000);
        addVehiculo("cupra", 1700);
    }

    synchronized public Vehiculo addVehiculo(String modelo, int cilindrada){
        Vehiculo newVehiculo = new Vehiculo(id.getAndIncrement(), modelo, cilindrada);
        vehiculos.add(newVehiculo);
        return newVehiculo;
    }

    synchronized public Vehiculo getVehiculoById(int id){
        for(Vehiculo vehiculo : vehiculos){
            if (vehiculo.getId() == id) {
                return vehiculo;
            }
        }
        return null;
    }

    synchronized public List<Integer> getAllId(){
        List<Integer> ids = new ArrayList<>();
        for(Vehiculo vehiculo : vehiculos){
            ids.add(vehiculo.getId());
        }

        return ids;
    }

    synchronized public boolean updateVehiculo(int id, String modelo, int cilindrada){
        for(Vehiculo vehiculo : vehiculos){
            if (vehiculo.getId() == id) {
                vehiculo.setModelo(modelo);
                vehiculo.setCilindrada(cilindrada);

                return true;
            }
        }

        return false;
    }

    synchronized public boolean deleteVehiculo(int id){
        return vehiculos.removeIf(vehiculo -> vehiculo.getId() == id);
    }

}
