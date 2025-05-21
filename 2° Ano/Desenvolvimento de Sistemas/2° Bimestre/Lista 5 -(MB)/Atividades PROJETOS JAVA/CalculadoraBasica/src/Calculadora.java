/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class Calculadora {
    private double n1;
    private double n2;
    private double r;
    
    public Calculadora(){
    this(0.0,0.0,0.0);
    }

    public Calculadora(double n1, double n2, double r) {
        this.n1 = n1;
        this.n2 = n2;
        this.r = r;
    }

    public double getN1() {
        return n1;
    }

    public double getN2() {
        return n2;
    }

    public double getR() {
        return r;
    }

    public void setN1(double n1) {
        this.n1 = n1;
    }

    public void setN2(double n2) {
        this.n2 = n2;
    }

    public void setR(double r) {
        this.r = r;
    }
    
    public void somar(){
    setN1 (  Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro valor: ")));
  
    setN2(  Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo valor: ")));
    
    setR(getN1() + getN2());
    JOptionPane.showMessageDialog(null, "O resultado da soma é: " +getR());
    }
    
    public void subtrair( double a, double b){
        
     setN1(a);
     setN2(b);
    setR(getN1() - getN2());
    JOptionPane.showMessageDialog(null, "O resultado da subtração é: " +getR());
    }
    
    public double multiplicar (){
       
    setN1 (  Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro valor: ")));
    setN2 (  Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo valor: ")));
    setR(getN1() * getN2());
    
    
    return getR();
    }
    
    public double dividir(double a, double b){
        
   setN1(a);
   setN2(b);
   
      
       setR(getN1() / getN2());
    return getR();

    }
}


