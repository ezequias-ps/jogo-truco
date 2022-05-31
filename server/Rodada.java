package server;

import java.util.ArrayList;
import java.util.Arrays;

public class Rodada {
    private ArrayList<Jogador> jogadores;
    Baralho baralho;
    private Mao mao;
    int pontosDaRodada;

    public Rodada(ArrayList<Jogador> jogadores){
        this.jogadores = jogadores;
        this.pontosDaRodada = 1;
    }
    
    public int[] iniciarRodada(){
        Util.mostraParaTodos(this.jogadores, "Inicio da rodada!");
        this.baralho = new Baralho();
        this.baralho.embaralhar();
        distribuirCartas(); // Distribui 3 cartas para cada jogador

        String cartaVira = baralho.getCartas().remove(0);
        Util.mostraParaTodos(this.jogadores, "A carta vira é: " + cartaVira);

        int[] duplas = {0, 0}; // Verifica qual dupla ganhou a melhor de 2
        int[] dupla1 = {0, 2}; // Representa a primeira dupla
        int[] dupla2 = {1, 3}; // Representa a segunda dupla
        int[] ganhadoresDaMao;

        for (int numDaMao = 1; numDaMao <= 3; numDaMao++) {
            this.mao = new Mao(jogadores);
            ganhadoresDaMao = mao.iniciarMao(pontosDaRodada, cartaVira, numDaMao);
            jogadores.get(ganhadoresDaMao[0]).getAtendente().enviarMensagem(
                "Você e o(a) " + jogadores.get(ganhadoresDaMao[1]).getNome() +
                " ganharam a " + numDaMao + "mao.");
            jogadores.get(ganhadoresDaMao[1]).getAtendente().enviarMensagem(
                "Você e o(a) " + jogadores.get(ganhadoresDaMao[0]).getNome() +
                " ganharam a " + numDaMao + "mao.");

            if (Arrays.equals(ganhadoresDaMao, dupla1))duplas[0]++;
            else duplas[1]++;

            if (duplas[0] == 2 || duplas[1] == 2) break;
        }

        // pontosDaRodada += this.pontosDaMao;
        Util.mostraParaTodos(jogadores, "TESTANDO... Pontos da rodada: " + this.pontosDaRodada);

        if(duplas[0] == 2) {
            for (int i : dupla1) {
                jogadores.get(i).addPontos(pontosDaRodada);
            }
            return dupla1;
        }else {
            for (int i : dupla2) {
                jogadores.get(i).addPontos(pontosDaRodada);
            }
            return dupla2;
        }
    }

    private void distribuirCartas(){
        ArrayList<String> suasCartas;
        for (Jogador jogador : jogadores) {
            suasCartas = new ArrayList<String>();
            for (int i = 0; i < 3; i++) {
                String carta = baralho.getCartas().remove(0);
                suasCartas.add(carta);
            }
            jogador.setCartas(suasCartas);
            String texto = String.join(" ", suasCartas);
            jogador.getAtendente().enviarMensagem(texto);
        }
    }
    
}
