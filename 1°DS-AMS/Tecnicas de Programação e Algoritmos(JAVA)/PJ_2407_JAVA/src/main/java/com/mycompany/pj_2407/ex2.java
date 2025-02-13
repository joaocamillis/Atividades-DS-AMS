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
public class ex2 { 
    public static void main(String[] args) {
       int qp = 50, qq = 0, mq = 4, mp = 0;  
       
       qq = Integer.parseInt(JOptionPane.showInputDialog("Digite quantos quilos de peixe você possui:"));
       
       
      
       
       if (qq<=qp) {
       
           JOptionPane.showMessageDialog(null, "você não foi multado");
       } else {
       
            mp = (qq - qp) * 4;
           
           JOptionPane.showMessageDialog(null, "você foi multado no valor de R$: " + mp);
       }
      
       
    }
}
