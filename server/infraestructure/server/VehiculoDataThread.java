package server.infraestructure.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import server.application.Routing;

public class VehiculoDataThread extends Thread{
    private Socket socket;
    private Routing routing;

    public VehiculoDataThread(Socket socket, Routing routing){
        this.socket = socket;
        this.routing = routing;
    }

    public void run() {
        try (Scanner sc = new Scanner(socket.getInputStream());
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

            while (sc.hasNextLine()) {
                String peticion = sc.nextLine().trim();
                
                if (peticion.equalsIgnoreCase("fin")) {
                    pw.println("Conexión cerrada con el cliente.");
                    break;
                }
                
                System.out.println("Petición recibida: " + peticion);
                String respuesta = routing.procesarPeticion(peticion);
                
                if (respuesta == null || respuesta.isEmpty()) {
                    respuesta = "error 400 (petición mal formada)";
                }
                
                System.out.println("Respuesta enviada: " + respuesta);
                pw.println(respuesta);
                pw.flush();
            }
        } catch (IOException e) {
            System.out.println("Error en el hilo de cliente: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}