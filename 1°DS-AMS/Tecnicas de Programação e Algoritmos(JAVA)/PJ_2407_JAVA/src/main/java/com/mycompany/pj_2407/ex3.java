/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pj_2407;
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex3 {
    public static void main(String[] args) {
        
        int  dia1 = 0, dia2 = 0, mes1=0, mes2=0, ano1 = 0, ano2 = 0, ano3 = 0;
        
   JOptionPane.showMessageDialog(null, "AVISO! Escreva as informações apenas com numeros..."); 
     
    dia1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu dia de nascimento: "));
    
    mes1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu mes de nascimento: "));
    
    ano1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o seu ano de nascimento: "));
    
    
    dia2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o dia de atual: "));
    
   mes2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o mes de atual: "));
   
  ano2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o ano de atual: "));
  
  
  ano3 = ano2 - ano1;
  
  
  if(ano3>=18 && mes1>=mes2 && dia1>=dia2 && ano3<60) {
  
 
      JOptionPane.showMessageDialog(null, "você atingiu a maioridade");
      
  }if(ano3<18) {
  
  JOptionPane.showMessageDialog(null, "você é menor de idade");
  }
  
  if(ano3>=60 && mes1>=mes2 && dia1>=dia2){
      
    JOptionPane.showMessageDialog(null, "você é idoso");
  
      
  
  }
        
       
       
     
    }
}
