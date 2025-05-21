/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;

public class CalculadoraPrincipal {
    private Calculadora calculadora = new Calculadora();

    public static void main(String[] args) {
        new CalculadoraPrincipal();
    }

    public CalculadoraPrincipal() {
        new CalculadoraForm(this);
    }

    public void somar(JTextField t1, JTextField t2, JLabel lbl) {
        try {
            calculadora.setN1(Double.parseDouble(t1.getText()));
            calculadora.setN2(Double.parseDouble(t2.getText()));
            calculadora.somar(); 
            lbl.setText(String.valueOf(calculadora.getR()));
        } catch (NumberFormatException e) {
            lbl.setText("Erro: Digite apenas numeros!");
        }
    }

    public void subtrair(JTextField t1, JTextField t2, JLabel lbl) {
        try {
            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            calculadora.subtrair(a, b); 
            lbl.setText(String.valueOf(calculadora.getR()));
        } catch (NumberFormatException e) {
            lbl.setText("Erro: Digite apenas numeros!");
        }
    }

    public void multiplicar(JTextField t1, JTextField t2, JLabel lbl) {
        try {
            calculadora.setN1(Double.parseDouble(t1.getText()));
            calculadora.setN2(Double.parseDouble(t2.getText()));
            double r = calculadora.multiplicar(); 
            lbl.setText(String.valueOf(r));
        } catch (NumberFormatException e) {
            lbl.setText("Erro: Digite apenas numeros!");
        }
    }

    public void dividir(JTextField t1, JTextField t2, JLabel lbl) {
        try {
            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            double r = calculadora.dividir(a, b);
            if (b != 0) {
                lbl.setText(String.valueOf(r));
            } else{
                 lbl.setText("Erro: Divisão por zero");
            }
        } catch (NumberFormatException e) {
            lbl.setText("Erro: Digite apenas numeros!");
        }
    }
}
