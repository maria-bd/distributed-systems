import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class client {
    Socket socket;
    int port = 3000;
    
    PrintWriter out;
    BufferedReader in;
    BufferedReader userInput;

    public client() {
        System.out.println("Client démarré");
        try {
            socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userInput = new BufferedReader(new InputStreamReader(System.in));

            String message;
            while (true) {
                System.out.print("Saisissez un message (ou 'exit' pour quitter) : ");
                message = userInput.readLine();
                out.println(message);
                if (message.equals("exit")) {
                    break;
                }
                String response = in.readLine();
                System.out.println("Réponse du serveur : " + response);
            }

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new client();
    }
}
