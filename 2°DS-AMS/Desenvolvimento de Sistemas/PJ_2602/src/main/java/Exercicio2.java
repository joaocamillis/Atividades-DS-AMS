/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author Thiago
 */
public class Exercicio2 {
    public static void main(String[] args) {
        double V1=0, V2=0;
        
        
      String valor1 =  JOptionPane.showInputDialog("Digite o primeiro valor");
     V1 = Double.parseDouble(valor1);
        
       String valor2 = JOptionPane.showInputDialog("Digite o segundo valor");
          V2 = Double.parseDouble(valor2);
          
          Diferenca(V1,V2);
    }
    
    public static void Diferenca (double v1, double v2)
    {
    double result = 0;
      
       
        if(v1 > v2)
        { 
            result = v1 - v2;
          JOptionPane.showMessageDialog(null,"O numero: " +v1+ " é maior que o numero: " +v2+ "\n" + "A diferença do maior numero para o menor é:  " +result );
        }
      
      
         if(v1 < v2)
        { 
            result = v2 - v1;
          JOptionPane.showMessageDialog(null,"O numero: " +v2+ " é maior que o numero: " +v1+ "\n" + "A diferença do maior numero para o menor é:  " +result );
        }
        
         if(v1 == v2)
        { 
            result = v1 - v2;
          JOptionPane.showMessageDialog(null,"O numero: " +v1+ " é igual ao numero: " +v2+ "\n" + "A diferença do maior numero para o menor é:  " +result );
        }
     
    }
}
