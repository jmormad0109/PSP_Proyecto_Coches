package server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.application.Routing;
import server.data.repository.VehiculoRepository;
import server.infraestructure.server.VehiculoDataThread;

public class Servidor{
    private static final int PUERTO = 44444;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            VehiculoRepository repositorio = new VehiculoRepository();
            Routing routing = new Routing(repositorio);


            while (true) {
                Socket socketComunicacion = serverSocket.accept();
                System.out.println("Cliente conectado: " + socketComunicacion.getInetAddress());

                new VehiculoDataThread(socketComunicacion, routing).start();
            }
        }catch(IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }finally{
            if(serverSocket != null){
                try {
                    serverSocket.close();
                    System.out.println("Servidor cerrado.");
                } catch (IOException e) {
                    System.out.println("Error al cerrar el servidor: " + e.getMessage());
                }
            }
        }
    }
}