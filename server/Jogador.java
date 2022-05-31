package server;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private Attend atendente;
    private ArrayList<String> cartas;

    public Jogador(Attend atendente){
        this.atendente = atendente;
        this.nome = atendente.getNomeJogador();
    }

    public Attend getAtendente() {
        return atendente;
    }

    public String getNome() {
        return nome;
    }

    public void setCartas(ArrayList<String> cartas) {
        this.cartas = cartas;
    }
    public ArrayList<String> getCartas() {
        return cartas;
    }
}
