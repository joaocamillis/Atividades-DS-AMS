/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author Thiago
 */
public class Exercicio5 {
    public static void main(String[] args) {
       double base, altura;
       base = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da base do triangulo em metros "));
       altura = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da altura do triangulo em metros "));
       double result = hipotenusa(base, altura);
       double resultado = Math.sqrt(result);
       
       JOptionPane.showMessageDialog(null, "Valor da Hipotenusa ao quadrado : " +result+"\n"+" Valor da raiz quadrada da Hipotenusa: "+resultado);
    }
    
    public static double hipotenusa(double base, double altura){
    double hipote;
        return hipote = (base*base) + (altura*altura);
    }
}
