package client;

public class Ouvidor extends Thread{
    Cliente cliente;
    private boolean running;

    public Ouvidor (Cliente cliente){
        this.cliente = cliente;
        running = true;
    }

    @Override
    public void run() {
        do {
            String resposta = cliente.input.nextLine();
            if (resposta.equals("Me responde!")) cliente.DigitarParaServidor();
            else System.out.println(resposta);
        } while (running);
    }

    public void parar(){
        running = false;
    }
}
