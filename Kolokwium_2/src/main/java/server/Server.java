package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final int port = 12345;
    private List<ClientHandler> clients = new ArrayList<>();
    private DataBase dataBase = new DataBase();

    public void listen() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serwer nasluchuje na porcie" + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nowe połączenie od" + clientSocket.getInetAddress());
                ClientHandler clientHandler = new ClientHandler(clientSocket, this );
                addClient(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void removeClient(ClientHandler clientHandler){
        clients.remove(clientHandler);
        System.out.println("Klient rozłączony.");
    }
    public synchronized void broadcast(String message) {
        for(ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.listen();
    }
    public synchronized void addClient(ClientHandler client){
        clients.add(client);
    }


    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }
}
//Zwykle prosty serwer tak wygląda
//ServerSocket serverSocket = new ServerSocket(PORT);

//while (true) {
//Socket clientSocket = serverSocket.accept();
// nowy handler w osobnym wątku
//  new Thread(new ClientHandler(clientSocket)).start();
//}
