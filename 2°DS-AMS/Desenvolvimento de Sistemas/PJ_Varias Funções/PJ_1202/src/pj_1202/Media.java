/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_1202;
import javax.swing.JOptionPane;

public class Media {
    public static void main(String[] args) {
        
        int m1 = 0, m2 = 0, m3 = 0, m4 = 0, nf = 0;
        
        String nome = JOptionPane.showInputDialog("Informe o seu nome: ");
        
        String input1 = JOptionPane.showInputDialog("Digite a primeira nota: ");
        m1 = Integer.parseInt(input1);
        
        String input2 = JOptionPane.showInputDialog("Digite a segunda nota: ");
        m2 = Integer.parseInt(input2);
        
        String input3 = JOptionPane.showInputDialog("Digite a terceira nota: ");
        m3 = Integer.parseInt(input3);
        
        String input4 = JOptionPane.showInputDialog("Digite a quarta nota: ");
        m4 = Integer.parseInt(input4);
        
        nf = (m1 + m2 + m3 + m4) / 4;
        
        JOptionPane.showMessageDialog(null, "A sua nota final em matemática será: " + nf);
    }
}
