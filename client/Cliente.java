package client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    final String IP = "127.0.0.1";
    final int PORT = 1234;
    Socket socket = null;
    Scanner input = null;
    Scanner teclado;
    PrintStream output = null;

    public void solicitarConexao (){
        try {
            socket = new Socket(IP, PORT);
        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao servidor.");
            System.out.println(e.getMessage());
            return;
        }
    }

    // comunicação
    public void DigitarParaServidor(){
        try {
            output = new PrintStream(socket.getOutputStream());
            teclado = new Scanner(System.in);
            String msg;
            System.out.print("Digite a menssagem: ");
            msg = teclado.nextLine();
            output.println(msg);              
        } catch (Exception e) {
            System.out.println("Erro na comunicação");
            System.out.println(e.getMessage());
        }
    }


    public void ouvir(){
        try {
            input = new Scanner(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            Ouvidor ouvidor = new Ouvidor(this);
            ouvidor.start();
        } catch (Exception e) {
            System.out.println("Erro na comunicação");
            System.out.println(e.getMessage());
        }
    }

    
}
