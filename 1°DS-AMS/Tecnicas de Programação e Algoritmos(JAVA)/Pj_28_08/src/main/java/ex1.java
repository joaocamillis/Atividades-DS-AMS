/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex1 {
    public static void main(String[] args) {
        int It=0,Ft=0;
        
        It = Integer.parseInt(JOptionPane.showInputDialog("Digite que horas você começa seu turno: "));       
        Ft = Integer.parseInt(JOptionPane.showInputDialog("Digite que horas você acaba seu turno: "));
        
        if(It>=5 && Ft<=13)
        {
    JOptionPane.showMessageDialog(null, "Você trabalha no turno da manhã");
        }
    
     if(It>=13 && Ft<=21)
     {
    JOptionPane.showMessageDialog(null, "Você trabalha no turno da Tarde");
     }
       if(It>=21 && Ft<=5)
     {
    JOptionPane.showMessageDialog(null, "Você trabalha no turno da Noite");
     }

    }
}
