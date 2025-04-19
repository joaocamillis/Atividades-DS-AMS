
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thiago
 */
public class Exercicio1 {
    public static void main(String[] args) {
      String nome = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ");
     Double nota1 = lerNota("digite a primeira nota");
      Double nota2 = lerNota("digite a segunda nota");
      
      double media = calcularMedia(nota1, nota2);
      
      if(media >= 7.0 && media <=10.0 ){
         JOptionPane.showMessageDialog(null,"Aluno: " +nome+ "\n" + "Media: "+ media+ "\n" +"Aprovado");
      }
      
      else{
        JOptionPane.showMessageDialog(null,"Aluno: " +nome+ "\n" + "Media: "+ media+ "\n" +"Reprovado");
      }

      
    }
    
     public static double lerNota(String mensagem) {
    
         String input = JOptionPane.showInputDialog(mensagem);
                 return Double.parseDouble(input);
    }
     
     public static double calcularMedia(double n1, double n2)
     {
         
        return (n1 + n2) /2;
         
     }
}
