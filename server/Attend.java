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
    private String nomeJogador;

    public Attend(Socket clientSocket, ArrayList<Attend> threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
    }

    @Override
    public void run() {
        try {
            output = new PrintStream(clientSocket.getOutputStream());
            input = new Scanner(clientSocket.getInputStream());

            this.enviarMensagem("Qual o seu nome?");
            String msg = ouvir();
            nomeJogador = msg;
            Jogo.qtdJogadores++;
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println("Erro na comunicação");
            System.out.println(e.getMessage());
        }
    }

    public void enviarMensagem(String msg) {
        output.println(msg);
    }

    public String ouvir(){
            String msg;
            enviarMensagem("Me responde!");
            do {
                msg = input.nextLine();
            } while (msg.equals(null));
            return(msg);
    }

    public String getNomeJogador() {
        return nomeJogador;
    }
}
