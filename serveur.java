import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {
    ServerSocket ss;
    int port = 3000;

    public serveur() {
        try {
            System.out.println("Serveur démarré, j'attends des clients...");
            ss = new ServerSocket(port);
            Socket socket = ss.accept();
            System.out.println("Un client est arrivé");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String message = in.readLine();
                System.out.println("Message du client : " + message);

                if (message.equals("exit")) {
                    break;
                }
                
                out.println("Bienvenue, client ! Vous avez dit : " + message);
            }

            socket.close();
            ss.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new serveur();
    }
}
