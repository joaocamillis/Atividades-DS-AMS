/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarar
 */
import javax.swing.JOptionPane;

public class Exercicio1 {
    public static void main(String[] args) {
        // Matriz a (caracteres)
        char[][] matrizA = {
            {'a', 'b', 'c', 'd', 'e'},
            {'f', 'g', 'h', 'i', 'j'},
            {'l', 'm', 'n', 'o', 'p'},
            {'q', 'r', 's', 't', 'u'}
        };

        // Exibir Matriz A
        StringBuilder exibeA = new StringBuilder("Matriz A:\n");
        for (char[] linha : matrizA) {
            for (char elemento : linha) {
                exibeA.append(elemento).append(" ");
            }
            exibeA.append("\n");
        }

        // Matriz b (inteiros)
        int[][] matrizB = {
            {19, 25, 100, 99},
            {10, 7, 25, 14},
            {35, 2, 47, 74}
        };

        // Exibir Matriz B
        StringBuilder exibeB = new StringBuilder("Matriz B:\n");
        for (int[] linha : matrizB) {
            for (int elemento : linha) {
                exibeB.append(elemento).append(" ");
            }
            exibeB.append("\n");
        }

        // Matriz c (decimais)
        double[][] matrizC = {
            {1.9, 2.5, 10.0},
            {1.0, 7.8, 2.5},
            {3.5, 2.2, 4.7}
        };

        // Exibir Matriz C
        StringBuilder exibeC = new StringBuilder("Matriz C:\n");
        for (double[] linha : matrizC) {
            for (double elemento : linha) {
                exibeC.append(elemento).append(" ");
            }
            exibeC.append("\n");
        }

        // Exibindo tudo
        JOptionPane.showMessageDialog(null, exibeA + "\n" + exibeB + "\n" + exibeC);
    }
}

