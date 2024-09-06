/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex6 {
    public static void main(String[] args) {
        int id = 0;
        id = Integer.parseInt(JOptionPane.showInputDialog("Digite a sua idade: ")); 
        
        
         if(id>=5 && id<=7 ){
                JOptionPane.showMessageDialog(null,"você é infantil A");
               }
         
           if(id>=8 && id<=10 ){
                JOptionPane.showMessageDialog(null,"você é infantil B");
               }
           
             if(id>=11 && id<=13 ){
                JOptionPane.showMessageDialog(null,"você é juvenil A");
               }
             
               if(id>=14 && id<=17 ){
                JOptionPane.showMessageDialog(null,"você é juvenil B");
               }
               
                 if(id>=18){
                JOptionPane.showMessageDialog(null,"você é Senior");
               }
    }
}
