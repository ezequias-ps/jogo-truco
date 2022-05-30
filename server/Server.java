package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ArrayList<Attend> threads;

    public static void main(String[] args) {
        final int PORT = 1234;
        ServerSocket serverSocket;
        Socket clientSocket = null;


        // criar o socket
        try {
            serverSocket = new ServerSocket(PORT);
            threads = new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Porta " + PORT + " indisponível.");
            System.out.println(e.getMessage());
            return;
        }

        // esperar a conexão
        try {
            while (threads.size() < 4){
                System.out.println("Aguardando conexão...");
                clientSocket = serverSocket.accept();
                Attend attend = new Attend(clientSocket, threads);
                attend.start();
                threads.add(attend);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Jogo jogo = new Jogo(threads);
        jogo.start();

        // encerrar conexão
        // try {
        //     clientSocket.close();
        //     serverSocket.close();
        // } catch (Exception e) {
        //     System.out.println(e.getMessage());
        // }

    }
}