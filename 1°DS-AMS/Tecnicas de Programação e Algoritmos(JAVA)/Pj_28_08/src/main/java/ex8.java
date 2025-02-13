/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex8 {
    public static void main(String[] args) {
         String aString = JOptionPane.showInputDialog(null, "Digite o comprimento do lado A (em cm):");
        String bString = JOptionPane.showInputDialog(null, "Digite o comprimento do lado B (em cm):");
        String cString = JOptionPane.showInputDialog(null, "Digite o comprimento do lado C (em cm):");


        double a = Double.parseDouble(aString);
        double b = Double.parseDouble(bString);
        double c = Double.parseDouble(cString);
        
        double d = (a + b) ;
          double e = (b + c) ;
            double f = (a + c) ;
        
        if(a==b && a==c || b==a && b==c || c==b && c==a && a<e && b<f && c<d ){
         JOptionPane.showMessageDialog(null, "O triangulo é equilatero");
                 
        }
         
        if(a!=b && a!=c && b!=a && b!=c && c!=a && c!=b && a<e && b<f && c<d){
         JOptionPane.showMessageDialog(null, "O triangulo é escaleno");
                 
        }
         
        if(a==b && a!=c && b!=c && a==c && c!=b && a!=b && b==c && b!=a && c!=a && a<e && b<f && c<d){
         JOptionPane.showMessageDialog(null, "O triangulo é isóceles");
                 
        }
        
        else if (c>=d) {
        JOptionPane.showMessageDialog(null, "não é um triangulo");
        }
        else if(a>=e) {
        JOptionPane.showMessageDialog(null, "não é um triangulo");
        }
        
        else if(b>=f) {
        JOptionPane.showMessageDialog(null, "não é um triangulo");
        }
    }
}