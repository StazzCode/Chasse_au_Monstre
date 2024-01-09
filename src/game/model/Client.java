package game.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        final String serverAddress = "adresse_ip_du_serveur"; // Remplacez par l'adresse IP du serveur
        final int serverPort = 12345;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println("Connecté au serveur!");

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Exemple : Recevoir un message du serveur
            Object receivedObject = inputStream.readObject();
            if (receivedObject instanceof String) {
                String serverMessage = (String) receivedObject;
                System.out.println("Message du serveur : " + serverMessage);
            }

            // Exemple : Envoyer un message au serveur
            String messageToSend = "Bonjour, serveur!";
            outputStream.writeObject(messageToSend);

            // Ajoutez votre logique de jeu et de communication ici

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
