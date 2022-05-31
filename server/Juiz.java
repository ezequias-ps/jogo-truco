package server;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Juiz {
    //  3 > 2 > A > k > J > Q > 7 > 6 > 5 > 4
    private String cartaVira;
    Dictionary<Character, Integer> dic = new Hashtable<Character, Integer>();

    public Juiz(String cartaVira){
        this.cartaVira = cartaVira;

        dic.put('3', 10);
        dic.put('2', 9);
        dic.put('A', 8);
        dic.put('K', 7);
        dic.put('J', 6);
        dic.put('Q', 5);
        dic.put('7', 4);
        dic.put('6', 3);
        dic.put('5', 2);
        dic.put('4', 1);
    }

    public int[] ganhadoresDaRodada(ArrayList<String> cartasJogadas){
        String maiorCarta = cartasJogadas.get(0);
        char n1 = cartasJogadas.get(0).charAt(0);

        for (int i = 1; i < cartasJogadas.size(); i++) {
            char n2 = cartasJogadas.get(i).charAt(0);
            if (dic.get(n2) > dic.get(n1)) {
                maiorCarta = cartasJogadas.get(i);
                n1 = n2;
            }
        }

        int jogadorComMaiorCarta = cartasJogadas.indexOf(maiorCarta);
        
        if (jogadorComMaiorCarta == 0 || jogadorComMaiorCarta == 2){
            int[] jogadores = {0, 2};
            return jogadores;
        }else {
            int[] jogadores = {1, 3};
            return jogadores;
        }
    }

}
