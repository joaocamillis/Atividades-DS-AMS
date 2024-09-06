/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex7 {
    public static void main(String[] args) {
       int dia1=0,mes1=0,ano1=0,dia2=0,mes2=0,ano2=0;
       
               dia1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro dia: "));       
                mes1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro mes: "));  
                 ano1 = Integer.parseInt(JOptionPane.showInputDialog("Digite o primeiro ano: "));   
                 
                  dia2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo dia: "));  
                  mes2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo mes: "));  
                  ano2 = Integer.parseInt(JOptionPane.showInputDialog("Digite o segundo ano: "));   
                  
                  if(dia1>dia2 && mes1>mes2 && ano1>ano2){
                    JOptionPane.showMessageDialog(null, "a primeira data é maior que a segunda:"  + ano1 +"-"+ ano2 +"-"+ dia1 +"-"+ dia2 +"-"+ mes1 +"-"+ mes2);
                  }
                  
                   if(dia2>dia1 && mes2>mes1 && ano2>ano1){
                    JOptionPane.showMessageDialog(null, "a segunda data é maior que a primeira:"  + ano2 +"-"+ ano1 +"-"+ dia2 +"-"+ dia1 +"-"+ mes2 +"-"+ mes1);
                  }
                   
                   if (dia2==dia1 && mes2==mes1 && ano2==ano1) {
                            JOptionPane.showMessageDialog(null, "as datas são iguais"); 
                           }

        
    }
}
