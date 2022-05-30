package server;

import java.util.List;

public class Jogo{
    private List<Attend> jogadores;

    public Jogo(List<Attend> jogadores){
        this.jogadores = jogadores;
    }

    public void start(){
        montaDuplas();
    }

    private void montaDuplas(){
        Attend jogador1 = jogadores.get(0);
        Attend jogador2 = jogadores.get(1);
        Attend jogador3 = jogadores.get(2);
        Attend jogador4 = jogadores.get(3);

        jogador1.enviarMensagem("Você é o jogador 1 e fará dupla com o jogador 3"); // No lugar do 3 colocar o nome do jogador;
        jogador3.enviarMensagem("Você é o jogador 3 e fará dupla com o jogador 1");

        jogador2.enviarMensagem("Você é o jogador 2 e fará dupla com o jogador 4");
        jogador4.enviarMensagem("Você é o jogador 4 e fará dupla com o jogador 2");
    }

    

}
