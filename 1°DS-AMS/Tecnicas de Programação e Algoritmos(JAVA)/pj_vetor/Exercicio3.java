/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_vetor;

/**
 *
 * @author FATEC ZONA LESTE
 */
import java.util.Arrays;
import java.util.Scanner;

public class Exercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numeros = new int[50];

        System.out.println("Digite 50 números:");
        for (int i = 0; i < 50; i++) {
            numeros[i] = scanner.nextInt();
        }

        Arrays.sort(numeros); // Ordena os números em ordem crescente
        System.out.println("Números em ordem crescente: " + Arrays.toString(numeros));
        scanner.close();
    }
}
