/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex2 {
    public static void main(String[] args) {
       
        String pesoString = JOptionPane.showInputDialog(null, "Digite seu peso (em kg):");
        double peso = Double.parseDouble(pesoString);

        String altString = JOptionPane.showInputDialog("Digite a sua altura (em metros): ");
        double alt = Double.parseDouble(altString);
         
         double imc = peso / (alt * alt);
         
         
         
          if(imc<18)
        {
    JOptionPane.showMessageDialog(null, "Você tem Magreza");
        }
          if (imc>=18.0 && imc<=24.9)
          {
               JOptionPane.showMessageDialog(null, "Você é saudavel");
          }
          if (imc>=25.0 && imc<=29.9)
          {
               JOptionPane.showMessageDialog(null, "Você tem sobrepeso");
          }
          if (imc>=30.0)
          {
               JOptionPane.showMessageDialog(null, "Você é Obeso");
          }
              }
}
