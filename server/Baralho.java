package server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Baralho {
    private String[] cartas = {
        //3 > 2 > A > k > J > Q > 7 > 6 > 5 > 4
        //Ás de paus > Ás de copas > Ás de espadas > Ás de ouros
        "3C", "2C", "AC", "KC", "JC", "QC", "7C", "6C", "5C", "4C", 
        "3O", "2O", "AO", "KO", "JO", "QO", "7O", "6O", "5O", "4O", 
        "3P", "2P", "AP", "KP", "JP", "QP", "7P", "6P", "5P", "4P", 
        "3E", "2E", "AE", "KE", "JE", "QE", "7E", "6E", "5E", "4E"};
    private List<String> lista = Arrays.asList(cartas);
    private ArrayList<String> baralho = new ArrayList<String>(lista);
    private Random gerador = new Random();

    public ArrayList<String> getCartas() {
        return baralho;
    }

    public void embaralhar(){
        ArrayList<String> aux = new ArrayList<String>();
        while(!(baralho.isEmpty())){
            int index = gerador.nextInt(baralho.size());
            aux.add(baralho.remove(index));
        }
        this.baralho = aux;
    }
}

