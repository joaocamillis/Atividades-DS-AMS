/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex5 {
    public static void main(String[] args) {
        
        
           String nt1String = JOptionPane.showInputDialog(null, "Digite a primeira nota:");
        double nt1 = Double.parseDouble(nt1String);
        
         String nt2String = JOptionPane.showInputDialog(null, "Digite a segunda nota:");
        double nt2 = Double.parseDouble(nt2String);
        
           String nt3String = JOptionPane.showInputDialog(null, "Digite a terceira nota:");
        double nt3 = Double.parseDouble(nt3String);
        
           String nt4String = JOptionPane.showInputDialog(null, "Digite a quarta nota:");
        double nt4 = Double.parseDouble(nt4String);
               
               double md = (nt1 + nt2 + nt3 + nt4) / 4;
               JOptionPane.showMessageDialog(null,"sua media é: " + md);
               
               if(md>=9){
                JOptionPane.showMessageDialog(null,"você tirou A\n você foi aprovado");
               }
               
               if(md>=7 && md<9){
                JOptionPane.showMessageDialog(null,"você tirou B\n você foi aprovado");
               }
               
                if(md>=5 && md<7){
                JOptionPane.showMessageDialog(null,"você tirou C\n você foi aprovado");
               }
                
                if(md>=2.5 && md<5){
                JOptionPane.showMessageDialog(null,"você tirou D\n você foi reprovado");
               }
                
                 
                if(md<=2.5){
                JOptionPane.showMessageDialog(null,"você tirou E\n você foi reprovado");
               }


               
               
               
               
            
        
    }
}
