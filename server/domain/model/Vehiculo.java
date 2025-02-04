package server.domain.model;

public class Vehiculo {
    int id;
    String modelo;
    int cilindrada;

    public Vehiculo(String modelo, int cilindrada){
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    public Vehiculo(int id, String modelo, int cilindrada){
        this.id = id;
        this.modelo = modelo;
        this.cilindrada = cilindrada;
    }

    public int getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    
}
