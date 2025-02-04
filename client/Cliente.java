package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String SERVIDOR = "127.0.0.1";
    private static final int PUERTO = 44444;

    public static void main(String[] args) {
        Socket socket = null;
        Scanner ioS = new Scanner(System.in);

        try{
            socket = new Socket(SERVIDOR, PUERTO);
            System.out.println("Conectado al servidor en " + SERVIDOR + ":" + PUERTO);

            Scanner sc = new Scanner(socket.getInputStream());
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;
            while (true) {
                System.out.println("Escribe una petición: ");
                mensaje = ioS.nextLine().trim();

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
                    break;
                }

                sc.close();
            }
        }catch(IOException e){
            System.out.println("Error en el cliente: " + e.getMessage());
        }finally{
            try {
                if (socket != null) {
                    socket.close();
                    System.out.println("Conexión cerrada.");
                }
                ioS.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}
