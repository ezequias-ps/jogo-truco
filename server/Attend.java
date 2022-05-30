package server;

import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Attend extends Thread {
    private Socket clientSocket;
    private ArrayList<Attend> threads;
    Scanner input = null;
    PrintStream output = null;

    public Attend(Socket clientSocket, ArrayList<Attend> threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
    }

    @Override
    public void run() {
        // comunicação
        try {
            input = new Scanner(clientSocket.getInputStream());
            output = new PrintStream(clientSocket.getOutputStream());

            this.enviarMensagem("Qual o seu nome?");
            this.enviarMensagem("Me Responde!");

            String msg;
            do {
                msg = input.nextLine();
                System.out.println(msg);
                for (Attend attend : threads) {
                    attend.enviarMensagem(msg);
                }
            } while (!msg.equals("exit"));

            input.close();
            output.close();

        } catch (Exception e) {
            System.out.println("Erro na comunicação");
            System.out.println(e.getMessage());
        }
    }

    public void enviarMensagem(String msg) {
        output.println(msg);
    }
}
