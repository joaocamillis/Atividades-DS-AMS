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
public class FeminOuMasc {
    public static void main(String[] args) {
        
        int G=0, nm=0;
        
         String escolha = JOptionPane.showInputDialog("Digite o seu nome : ");
         
         
          String MouF = JOptionPane.showInputDialog("Digite : \n 1. Se você for homem \n  2. Se você for mulher");
          G = Integer.parseInt(MouF);
           
          
          if(G == 1){
           escolha+= " você é homem";
          }
          
          if(G == 2){
          
          escolha+= " você é mulher";
          }
          
           JOptionPane.showMessageDialog(null, escolha);
    }
}
