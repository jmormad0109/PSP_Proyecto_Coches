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

    @Override
    public void run() {
        try{
            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            while (sc.hasNextLine()) {
                String peticion = sc.nextLine().trim();

                if (peticion.equalsIgnoreCase("fin")) {
                    pw.println("Conexión cerrada con el cliente.");
                    break;
                }

                System.out.println("Petición recibida: " + peticion);

                String respuesta = routing.procesarPerticion(peticion);
                System.out.println("Respuesta enviada: " + respuesta);
                pw.println(respuesta);
            }
            
            sc.close();
        }catch(IOException e) {
            System.out.println("Error en el cliente; " + e.getMessage());
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}
