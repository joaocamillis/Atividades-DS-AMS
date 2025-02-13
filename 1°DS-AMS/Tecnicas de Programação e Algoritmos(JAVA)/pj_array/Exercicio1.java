/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_array;

/**
 *
 * @author FATEC ZONA LESTE
 */
import java.util.Scanner;
import java.util.Arrays;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nomes = new String[10];

        System.out.println("Digite 10 nomes:");
        for (int i = 0; i < 10; i++) {
            nomes[i] = scanner.nextLine();
        }

        Arrays.sort(nomes);
        System.out.println("Nomes em ordem crescente: " + Arrays.toString(nomes));
        scanner.close();
    }
}

