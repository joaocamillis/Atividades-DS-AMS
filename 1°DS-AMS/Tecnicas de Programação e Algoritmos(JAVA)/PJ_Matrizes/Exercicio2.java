/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarar
 */
import javax.swing.JOptionPane;

public class Exercicio2 {
    public static void main(String[] args) {
        int[][] matriz = new int[5][5];
        int somaImpares = 0;
        int[] somaColunas = new int[5];
        int[] somaLinhas = new int[5];

        // Preenchendo a matriz
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Digite um número inteiro para a posição [" + i + "][" + j + "]:"));
                // Soma de ímpares
                if (matriz[i][j] % 2 != 0) {
                    somaImpares += matriz[i][j];
                }
                // Soma de linhas e colunas
                somaLinhas[i] += matriz[i][j];
                somaColunas[j] += matriz[i][j];
            }
        }

        // Exibindo os resultados
        StringBuilder resultado = new StringBuilder();
        resultado.append("Soma dos números ímpares: ").append(somaImpares).append("\n");

        resultado.append("Soma de cada coluna:\n");
        for (int j = 0; j < 5; j++) {
            resultado.append("Coluna ").append(j + 1).append(": ").append(somaColunas[j]).append("\n");
        }

        resultado.append("Soma de cada linha:\n");
        for (int i = 0; i < 5; i++) {
            resultado.append("Linha ").append(i + 1).append(": ").append(somaLinhas[i]).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString());
    }
}
