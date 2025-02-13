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
import java.util.Collections;

public class Exercicio4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer[] numeros = new Integer[50]; // Usamos Integer para permitir a ordenação decrescente

        System.out.println("Digite 50 números:");
        for (int i = 0; i < 50; i++) {
            numeros[i] = scanner.nextInt();
        }

        Arrays.sort(numeros, Collections.reverseOrder()); // Ordena os números em ordem decrescente
        System.out.println("Números em ordem decrescente: " + Arrays.toString(numeros));
        scanner.close();
    }
}

