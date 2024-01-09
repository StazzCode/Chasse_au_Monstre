package game.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        final int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion établie avec " + clientSocket.getInetAddress());

                // Traitez la communication avec le client dans un thread séparé
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientHandler(Socket socket) {
        try {
            this.clientSocket = socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            // Gérez la communication avec le client ici

            // Exemple : Envoyer un message au client
            String messageToSend = "Bienvenue sur le serveur!";
            outputStream.writeObject(messageToSend);

            // Exemple : Recevoir un message du client
            Object receivedObject = inputStream.readObject();
            if (receivedObject instanceof String) {
                String clientMessage = (String) receivedObject;
                System.out.println("Message reçu du client : " + clientMessage);
            }

            // Ajoutez votre logique de jeu et de communication ici

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Fermez les flux et le socket
            try {
                inputStream.close();
                outputStream.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

