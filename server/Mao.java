package server;

import java.util.ArrayList;

public class Mao {
    ArrayList<Jogador> jogadores;
    Juiz juiz;
    String texto_truco;
    Jogador jogadorDaVez;

    public Mao(ArrayList<Jogador> jogadores){
        this.jogadores = jogadores;
        this.juiz = new Juiz();
        this.texto_truco = "TRUCO";
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
                    boolean resposta = pedirTruco(jogador, rodada);
                    if (resposta == false) {
                        Util.mostraParaTodos(jogadores, "O pedido de " + this.texto_truco + " foi recusado!");
                        if (jogadores.indexOf(this.jogadorDaVez) == 0 || jogadores.indexOf(this.jogadorDaVez) == 2) {
                            int[] ganhadoresDaMao =  {0, 1};
                            rodada.mao_acabou = true;
                            return ganhadoresDaMao;
                        } else {
                            int[] ganhadoresDaMao =  {1, 3};
                            rodada.mao_acabou = true;
                            return ganhadoresDaMao;
                        }
                    }
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

    private boolean pedirTruco(Jogador jogadorQuePediuTruco, Rodada rodada){
        switch (this.texto_truco) {
            case "TRUCO":
                Util.mostraParaTodos(jogadores, jogadorQuePediuTruco.getNome() + " Pediu " + this.texto_truco + "!", jogadorQuePediuTruco);
                rodada.setPontosDaRodada(3);
                this.texto_truco = "6";
                break;
            case "6":
                Util.mostraParaTodos(jogadores, jogadorQuePediuTruco.getNome() + " Pediu " + this.texto_truco + "!", jogadorQuePediuTruco);
                rodada.setPontosDaRodada(6);
                this.texto_truco = "12";
                break;
            case "12":
                Util.mostraParaTodos(jogadores, jogadorQuePediuTruco.getNome() + " Pediu " + this.texto_truco + "!", jogadorQuePediuTruco);
                rodada.setPontosDaRodada(12);
                break;
            default:
                break;
        }

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
            jogadores.get(oponente1).getAtendente().enviarMensagem("Digite S para aceitar, N para recusar ou " + this.texto_truco + ": ");
            r1 = jogadores.get(oponente1).getAtendente().ouvir();
            if (!(r1.equals("S") || r1.equals("N") || r1.equals(this.texto_truco))) jogadores.get(oponente1).getAtendente().enviarMensagem("Opção invalida!");
        } while (!(r1.equals("S") || r1.equals("N") || r1.equals(this.texto_truco)));

        do {
            jogadores.get(oponente2).getAtendente().enviarMensagem("Digite S para aceitar, N para recusar ou " + this.texto_truco + ": ");
            r2 = jogadores.get(oponente2).getAtendente().ouvir();
            if (!(r2.equals("S") || r2.equals("N") || r2.equals(this.texto_truco))) jogadores.get(oponente2).getAtendente().enviarMensagem("Opção invalida!");
        } while (!(r2.equals("S") || r2.equals("N") || r2.equals(this.texto_truco)));


        if (r1.equals("S") || r2.equals("S")) {
            this.jogadorDaVez = jogadorQuePediuTruco;
            return true;
        } else if(r1.equals(this.texto_truco) || r2.equals(this.texto_truco)){
            return pedirTruco(jogadores.get(oponente1), rodada);
        } else return false;
    }


}
