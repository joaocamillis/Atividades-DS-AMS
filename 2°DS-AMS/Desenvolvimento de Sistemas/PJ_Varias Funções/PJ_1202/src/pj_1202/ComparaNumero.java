/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_1202;
import javax.swing.JOptionPane;

public class ComparaNumero {
    public static void main(String[] args) {
        int n1 = 0, n2 = 0;

        // Solicita o primeiro número
        String input1 = JOptionPane.showInputDialog("Digite o primeiro numero inteiro:");
        n1 = Integer.parseInt(input1);

        // Solicita o segundo número
        String input2 = JOptionPane.showInputDialog("Digite o segundo numero inteiro:");
        n2 = Integer.parseInt(input2);

        // Verifica se os números são iguais
        if (n1 == n2) {
            JOptionPane.showMessageDialog(null, "Os números são iguais");
        }

        // Verifica se o primeiro número é maior que o segundo
        if (n1 > n2) {
            JOptionPane.showMessageDialog(null, "Os números são diferentes. O número maior é: " + n1 + " e o menor é: " + n2);
        }

        // Verifica se o segundo número é maior que o primeiro
        if (n2 > n1) {
            JOptionPane.showMessageDialog(null, "Os números são diferentes. O número maior é: " + n2 + " e o menor é: " + n1);
        }
    }
}
