package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;
    private String login;
    public ClientHandler(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try{
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Podaj login: ");
            String login = in.readLine();

            out.println("Podaj hasło: ");
            String password = in.readLine();
            if (!server.getDataBase().authenticate(login, password)) {
                out.println("Błąd logowania. Rozłączono.");
                socket.close();
                server.removeClient(this);
                return;
            }

            this.login = login;
            out.println("Zalogowano jako: " + login);

            String message;
            while((message = in.readLine())!= null) {
                System.out.println("[" + login + "]" + message);
                server.broadcast("[ " + login +"]" + message);
            }

        }catch(IOException e) {
            System.out.println("Klient rozłączony: " + socket);
        } finally {
            server.removeClient(this);
        }
        try{
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String msg){
        if(out!= null) {
            out.println(msg);
        }
    }
    private void closeConnection() {
        try{
            server.removeClient(this);
            if(in!= null) in.close();
            if(out!= null) out.close();
            if(socket !=null) socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
//zwykle handler tak wygląda
//while ((msg = in.readLine()) != null) {
// obsługa wiadomości
//      out.println("Echo: " + msg);
//}

