/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_array;
import java.util.Arrays;
/**
 *
 * @author FATEC ZONA LESTE
 */
public class Exercicio5 {
    public static void main(String[] args) {
        int[] numerosPares = new int[100];

        for (int i = 0; i < 100; i++) {
            numerosPares[i] = i * 2 + 2; // Números pares de 2 a 200
        }   

        System.out.println("Números pares: " + Arrays.toString(numerosPares));
    }
}

