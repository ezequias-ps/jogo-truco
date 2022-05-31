package server;

import java.util.ArrayList;

public class Mao {
    ArrayList<Jogador> jogadores;
    Juiz juiz;

    public Mao(ArrayList<Jogador> jogadores){
        this.jogadores = jogadores;
        this.juiz = new Juiz();
    }

    public int[] iniciarMao(Rodada rodada, String cartaVira, int numDaMao) {
        Util.mostraParaTodos(this.jogadores, "\nInicio da " + numDaMao + " mao!");
        
        ArrayList<String> cartasJogadas = new ArrayList<String>();
        for (Jogador jogador : jogadores){
            Util.mostraParaTodos(jogadores, jogador.getNome() + " está jogando...", jogador);
            String cartaJogada;

            do { // Verifica se a opção foi valida.
                jogador.getAtendente().enviarMensagem("Sua vez de jogar!");
                String texto = String.join(" ", jogador.getCartas());
                jogador.getAtendente().enviarMensagem("Suas cartas são: " + texto + "\nEscolha sua carta ou diga TRUCO: ");

                cartaJogada = jogador.getAtendente().ouvir();
                if (cartaJogada.equals("TRUCO")) {
                    boolean resposta = pedirTruco(jogador);
                    if (resposta) rodada.setPontosDaRodada(3); // Altera pontos da rodada
                }

                if (!(jogador.getCartas().contains(cartaJogada) || cartaJogada.equals("TRUCO"))) jogador.getAtendente().enviarMensagem("Jogada invalida!");
            }while(!(jogador.getCartas().contains(cartaJogada) || cartaJogada.equals("TRUCO")));

            if (jogador.getCartas().remove(cartaJogada)) {
                cartasJogadas.add(cartaJogada);
                Util.mostraParaTodos(jogadores, jogador.getNome() + " jogou a carta " + cartaJogada, jogador);
            } else {
                Util.mostraParaTodos(jogadores, jogador.getNome() + " Pediu TRUCO!", jogador);
            }
            
        }

        rodada.setPontosDaRodada(6);
        int[] ganhadoresDaMao = juiz.ganhadoresDaMao(cartasJogadas, cartaVira);
        
        Util.mostraParaTodos(jogadores, "TESTANDO... 1");
        return ganhadoresDaMao;
    }

    private boolean pedirTruco(Jogador jogadorQuePediuTruco){
        int numJogador = jogadores.indexOf(jogadorQuePediuTruco);
        String r1;
        String r2;
        int oponente1 = 0;
        int oponente2 = 2;
        if (numJogador == 0 || numJogador == 2){
            oponente1 = 1;
            oponente2 = 3;
        }

        do {
            jogadores.get(oponente1).getAtendente().enviarMensagem(jogadores.get(numJogador).getNome() + " pediu truco!\nDigite S para aceitar ou N para recusar: ");
            r1 = jogadores.get(oponente1).getAtendente().ouvir();
            if (!(r1.equals("S") || r1.equals("N"))) jogadores.get(oponente1).getAtendente().enviarMensagem("Opção invalida!");
        } while (!(r1.equals("S") || r1.equals("N")));

        do {
            jogadores.get(oponente2).getAtendente().enviarMensagem(jogadores.get(numJogador).getNome() + " pediu truco!\nDigite S para aceitar ou N para recusar: ");
            r2 = jogadores.get(oponente2).getAtendente().ouvir();
            if (!(r2.equals("S") || r2.equals("N"))) jogadores.get(oponente2).getAtendente().enviarMensagem("Opção invalida!");
        } while (!(r2.equals("S") || r2.equals("N")));


        if (r1.equals("S") || r2.equals("S")) return true;
        else return false;
    }


}
