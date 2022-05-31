package server;

import java.util.ArrayList;

public class Jogo{
    private ArrayList<Attend> atendentes;
    private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
    private Baralho baralho;
    private String cartaVira;
    private int numRodada;
    public static int qtdJogadores;
    private Juiz juiz;

    public Jogo(ArrayList<Attend> atendentes){
        this.atendentes = atendentes;
    }

    public void start(){
        while (true){
            if (Jogo.qtdJogadores == 4) break;
            else System.out.println(Jogo.qtdJogadores);
        }
        setJogadores();
        montaDuplas();
        baralho = new Baralho();
        baralho.embaralhar();
        distribuirCartas();
        mostraACartaVira();
        iniciarRodada();
    }

    private void setJogadores(){
        for (Attend atendente : atendentes) {
            Jogador jogador = new Jogador(atendente);
            jogadores.add(jogador);
        }
    }

    private void montaDuplas(){
        jogadores.get(0).getAtendente().enviarMensagem(jogadores.get(0).getNome() + " você fará dupla com " + jogadores.get(2).getNome());
        jogadores.get(2).getAtendente().enviarMensagem(jogadores.get(2).getNome() + " você fará dupla com " + jogadores.get(0).getNome());

        jogadores.get(1).getAtendente().enviarMensagem(jogadores.get(1).getNome() + " você fará dupla com " + jogadores.get(3).getNome());
        jogadores.get(3).getAtendente().enviarMensagem(jogadores.get(3).getNome() + " você fará dupla com " + jogadores.get(1).getNome());
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

    private void mostraACartaVira(){
        this.cartaVira = baralho.getCartas().remove(0);
        String texto = "A carta vira é: " + this.cartaVira;
        this.mostraParaTodos(texto);
    }

    public void iniciarRodada(){
        this.juiz = new Juiz(this.cartaVira);
        ArrayList<String> cartasJogadas = new ArrayList<String>();
        for (Jogador jogador : jogadores){
            mostraParaTodos(jogador.getNome() + " está jogando...", jogador);
            jogador.getAtendente().enviarMensagem("Sua vez de jogar! Escolha dua carta ou diga TRUCO:");
            String texto = jogador.getAtendente().ouvir();
            mostraParaTodos(jogador.getNome() + " jogou a carta " + texto, jogador);
        }

        int[] ganhadoresDaRodada = juiz.ganhadoresDaRodada(cartasJogadas);
        jogadores.get(ganhadoresDaRodada[0]).getAtendente().enviarMensagem(
            "Você e o " + jogadores.get(ganhadoresDaRodada[1]).getNome() + " ganharam a rodada!");
        jogadores.get(ganhadoresDaRodada[1]).getAtendente().enviarMensagem(
            "Você e o " + jogadores.get(ganhadoresDaRodada[2]).getNome() + " ganharam a rodada!");

        this.numRodada++;
    }

    private void mostraParaTodos(String msg){
        for (Jogador jogador : jogadores) {
            jogador.getAtendente().enviarMensagem(msg);
        }
    }
    private void mostraParaTodos(String msg, Jogador jogadorJogando){
        for (Jogador jogador : jogadores) {
            if (!jogador.equals(jogadorJogando)){
                jogador.getAtendente().enviarMensagem(msg);
            }
        }
    }

}
