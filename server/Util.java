package server;

import java.util.ArrayList;
import java.util.Random;

public class Util {

    public static ArrayList<Object> misturaArrayList(ArrayList<Object> ArrayList){
        Random gerador = new Random();
        ArrayList<Object> aux = new ArrayList<Object>();
        while(!(ArrayList.isEmpty())){
            int index = gerador.nextInt(ArrayList.size());
            aux.add(ArrayList.remove(index));
        }
        return aux;
    }

    public static void mostraDuplas(ArrayList<Jogador> jogadores){
        jogadores.get(0).getAtendente().enviarMensagem(jogadores.get(0).getNome() + " você fará dupla com " + jogadores.get(2).getNome());
        jogadores.get(2).getAtendente().enviarMensagem(jogadores.get(2).getNome() + " você fará dupla com " + jogadores.get(0).getNome());

        jogadores.get(1).getAtendente().enviarMensagem(jogadores.get(1).getNome() + " você fará dupla com " + jogadores.get(3).getNome());
        jogadores.get(3).getAtendente().enviarMensagem(jogadores.get(3).getNome() + " você fará dupla com " + jogadores.get(1).getNome());
    }

    public static void mostraParaTodos(ArrayList<Jogador> jogadores, String msg){
        for (Jogador jogador : jogadores) {
            jogador.getAtendente().enviarMensagem(msg);
        }
    }
    public static void mostraParaTodos(ArrayList<Jogador> jogadores, String carta, Jogador jogadorJogando){
        for (Jogador jogador : jogadores) {
            if (!jogador.equals(jogadorJogando)){
                jogador.getAtendente().enviarMensagem(carta);
            }
        }
    }
}
