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

        String placar = montarPlacar(jogadores);
        Util.mostraParaTodos(jogadores, placar);

        Util.mostraParaTodos(jogadores, "\n\n\n\n");
        
    }


    private String montarPlacar(ArrayList<Jogador> jogadores) {
        String aux = "Dupla 1\n" + 
            jogadores.get(0).getNome() + ": " + jogadores.get(0).getPontos() + " pontos.\n" +
            jogadores.get(2).getNome() + ": " + jogadores.get(2).getPontos() + " pontos.\n\n" +
            "Dupla 2\n" +
            jogadores.get(1).getNome() + ": " + jogadores.get(1).getPontos() + " pontos.\n" +
            jogadores.get(3).getNome() + ": " + jogadores.get(3).getPontos() + " pontos.";

        return aux;
    }

}
