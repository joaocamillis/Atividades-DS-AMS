/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarar
 */
import javax.swing.JOptionPane;
import java.util.HashSet;

public class Exercicio3 {
    public static void main(String[] args) {
        int[][] matriz = new int[3][5];
        HashSet<Integer> elementos = new HashSet<>();
        int pares = 0, impares = 0;
        boolean possuiRepetidos = false;

        // Preenchendo a matriz
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = Integer.parseInt(JOptionPane.showInputDialog("Digite um número inteiro para a posição [" + i + "][" + j + "]:"));
                
                // Verifica repetição
                if (elementos.contains(matriz[i][j])) {
                    possuiRepetidos = true;
                } else {
                    elementos.add(matriz[i][j]);
                }

                // Verifica pares e ímpares
                if (matriz[i][j] % 2 == 0) {
                    pares++;
                } else {
                    impares++;
                }
            }
        }

        // Exibindo os resultados
        StringBuilder resultado = new StringBuilder();
        resultado.append("Possui elementos repetidos? ").append(possuiRepetidos ? "Sim" : "Não").append("\n");
        resultado.append("Quantidade de números pares: ").append(pares).append("\n");
        resultado.append("Quantidade de números ímpares: ").append(impares).append("\n");

        JOptionPane.showMessageDialog(null, resultado.toString());
    }
}
