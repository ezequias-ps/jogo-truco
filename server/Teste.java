package server;

import java.util.Arrays;

public class Teste {
    public static void main(String[] args) {
        int[] duplas = {0, 0};

        int[] aux = {0, 2};
        int[] aux2 = {1, 2};
        if (Arrays.equals(aux, aux2)){
            duplas[0]++;
        }

        System.out.println(duplas[0]);
    }
}
