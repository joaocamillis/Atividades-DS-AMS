/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class Exercicio3 {

    public static void main(String[] args) {

        String sexo = JOptionPane.showInputDialog(null, "Informe o seu Sexo " + "\n" + "para sexo Feminino digite (F)" + "\n" + "para o sexo masculino digite (M)");
        double h = Double.parseDouble(JOptionPane.showInputDialog("Digite a sua altura em metros"));

        double peso = calcularPesoIdeal(sexo.toUpperCase(), h);
        
        JOptionPane.showMessageDialog(null, "O seu peso ideal é: " +peso+ " KG");
    }

    public static double calcularPesoIdeal(String sexo, double h) {
        if (sexo.equals("M")) {
            return (72.7 * h) - 58;
            
        } else if (sexo.equals("F")) {
            return (62.1 * h) - 44.7;

        } else {
            JOptionPane.showMessageDialog(null, "Sexo não permitido, use (M) ou (F)");
            return 0;
            
        }

    }
}
