/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarar
 */
import javax.swing.JOptionPane;

public class Exercicio4 {
    public static void main(String[] args) {
        double[][] matriz = new double[4][4];
        StringBuilder diagonalPrincipal = new StringBuilder();
        StringBuilder diagonalSecundaria = new StringBuilder();

        // Preenchendo a matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Digite um número decimal para a posição [" + i + "][" + j + "]:"));
                
                // Adicionando valores às diagonais
                if (i == j) {
                    diagonalPrincipal.append(matriz[i][j]).append(" ");
                }
                if (i + j == 3) {
                    diagonalSecundaria.append(matriz[i][j]).append(" ");
                }
            }
        }

        // Exibindo os resultados
        JOptionPane.showMessageDialog(null, "Diagonal Principal: " + diagonalPrincipal.toString() + "\nDiagonal Secundária: " + diagonalSecundaria.toString());
    }
}
