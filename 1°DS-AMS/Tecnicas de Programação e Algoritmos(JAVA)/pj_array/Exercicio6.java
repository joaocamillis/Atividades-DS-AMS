/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_array;

/**
 *
 * @author FATEC ZONA LESTE
 */
import java.util.Arrays;
public class Exercicio6 {
    public static void main(String[] args) {
        int[] numerosImpares = new int[100];

        for (int i = 0; i < 100; i++) {
            numerosImpares[i] = i * 2 + 1; //numeros impares de 1 a 199
        }

        System.out.println("Números ímpares: " + Arrays.toString(numerosImpares));
    }
}
