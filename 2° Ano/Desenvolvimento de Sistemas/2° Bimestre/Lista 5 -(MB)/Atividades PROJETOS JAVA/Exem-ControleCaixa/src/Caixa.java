/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class Caixa {
   private double saldo;
   
   public Caixa(){
   this(0);
   }

    public Caixa(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
   
   public void entrar(){
   double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de entrada: "));
   
  setSaldo(getSaldo() + valor);
   }
   
   public void Retirar(){
   double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor de saida: "));
   setSaldo(getSaldo() - valor);
   }
   
}
