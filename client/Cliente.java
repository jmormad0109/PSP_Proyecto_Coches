package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR = "127.0.0.1";
    private static final int PUERTO = 44444;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVIDOR, PUERTO);
             Scanner ioS = new Scanner(System.in);
             Scanner sc = new Scanner(socket.getInputStream());
             PrintWriter pw = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado al servidor en " + SERVIDOR + ":" + PUERTO);

            while (true) {
                System.out.print("Escribe una petición: ");
                String mensaje = ioS.nextLine().trim();

                if (mensaje.isEmpty()) {
                    System.out.println("No puedes enviar una petición vacía");
                    continue;
                }

                pw.println(mensaje);
                System.out.println("Petición enviada: " + mensaje);

                if (sc.hasNextLine()) {
                    String respuesta = sc.nextLine();
                    System.out.println("Servidor: " + respuesta);
                }

                if (mensaje.equalsIgnoreCase("fin")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
