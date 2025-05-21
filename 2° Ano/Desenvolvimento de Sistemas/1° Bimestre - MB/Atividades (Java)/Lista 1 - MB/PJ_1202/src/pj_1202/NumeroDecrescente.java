/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_1202;
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class NumeroDecrescente {
    public static void main(String[] args) {
        
        String Str1 = JOptionPane.showInputDialog("Insira um número inteiro: ");
        int n1 = Integer.parseInt(Str1);
        //recebe o valor e converte de string para int
        
        //da um titulo á estrutura de repetição
        String mensagem = "Antessesores: \n";
        
        //usando uma simples condição de que o numero tem que ser maior que 0
        while (n1 > 0) {
            
  //abaixo ja apresentando o numero usando uma operação matematica de que n1 após ser apresentado sera subtraido por 1
            //e se repetindo de forma decrescente
            //e no começo acrescentando o "titulo" da mensagem
            mensagem += "O antessessor de " + n1 + " é: " + (n1-1) + "\n";
            n1--;
        }
       //colocando a mensagem ja com sua condição e operação feitas apenas apresentando fora do while
        JOptionPane.showMessageDialog(null, mensagem);
    }
}
