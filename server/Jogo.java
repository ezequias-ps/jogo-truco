package server;

import java.util.ArrayList;
import java.util.Collections;

public class Jogo{
    private ArrayList<Attend> atendentes;
    private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    public static int qtdJogadores;
    private Partida partida;

    public Jogo(ArrayList<Attend> atendentes){
        this.atendentes = atendentes;
    }

    public void start(){
        while (true){
            if (Jogo.qtdJogadores == 4) break;
            //else System.out.println(Jogo.qtdJogadores);
        }
        setJogadores();
        Collections.shuffle(this.jogadores); // Mistura ordem de jogadores no arraylist
        Util.mostraDuplas(this.jogadores);
        this.partida = new Partida();
        partida.iniciarPartida(jogadores);
    }

    private void setJogadores(){
        for (Attend atendente : atendentes) {
            Jogador jogador = new Jogador(atendente);
            jogadores.add(jogador);
        }
    }

}
