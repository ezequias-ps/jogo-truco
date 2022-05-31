package server;

import java.util.ArrayList;

public class Partida {
    private int[] ganhadoresDaRodada;
    private Rodada rodada;

    public void iniciarPartida(ArrayList<Jogador> jogadores){
        this.rodada = new Rodada(jogadores);

        while (!(jogadores.get(0).getPontos() >= 12 || jogadores.get(1).getPontos() >= 12)){
            ganhadoresDaRodada = rodada.iniciarRodada();
            
            jogadores.get(ganhadoresDaRodada[0]).getAtendente().enviarMensagem(
                "Você e o(a) " + jogadores.get(ganhadoresDaRodada[0]).getNome() +
                " ganharam a rodada e acumularam " + jogadores.get(ganhadoresDaRodada[0]).getPontos() + "pontos.");
            jogadores.get(ganhadoresDaRodada[1]).getAtendente().enviarMensagem(
                "Você e o(a) " + jogadores.get(ganhadoresDaRodada[1]).getNome() +
                " ganharam a rodada e acumularam " + jogadores.get(ganhadoresDaRodada[0]).getPontos() + "pontos.");
        }

    }

}
