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
import java.util.Collections;

public class Exercicio2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nomes = new String[20];

        System.out.println("Digite 20 nomes:");
        for (int i = 0; i < 20; i++) {
            nomes[i] = scanner.nextLine();
        }

        Arrays.sort(nomes, Collections.reverseOrder());
        System.out.println("Nomes em ordem decrescente: " + Arrays.toString(nomes));
        scanner.close();
    }
}
