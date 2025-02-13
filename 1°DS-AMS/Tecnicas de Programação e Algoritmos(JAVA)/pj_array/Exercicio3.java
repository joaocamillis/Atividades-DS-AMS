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

public class Exercicio3 {
    public static void main(String[] args) {
        int[] numeros = new int[50];

        for (int i = 0; i < 50; i++) {
            numeros[i] = i + 1; // Armazenando números de 1 a 50
        }

        Arrays.sort(numeros);
        System.out.println("Números em ordem crescente: " + Arrays.toString(numeros));
    }
}

