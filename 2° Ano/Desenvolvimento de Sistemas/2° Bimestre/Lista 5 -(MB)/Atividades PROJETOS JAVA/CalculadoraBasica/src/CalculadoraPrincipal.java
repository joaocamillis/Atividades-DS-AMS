/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class CalculadoraPrincipal {
    public static void main(String[] args) {
    Calculadora calculadora = new Calculadora();
    int op;
    
    do{
    op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1 - Somar" +"\n2 - Subtrair \n3 - Multiplicar \n4 - Dividir \n0 - Sair"));
    
   
   
    switch (op){
    
        case 1:
            calculadora.somar();
            break;
            
        case 2:
            double x= Double.parseDouble( JOptionPane.showInputDialog("Digite o primeiro valor:"));
            double y = Double.parseDouble( JOptionPane.showInputDialog("Digite o segundo valor:"));
            calculadora.subtrair(x,y);
            
           break;
           
        case 3: 
            double mult = calculadora.multiplicar();
             JOptionPane.showMessageDialog(null,"O resultado da divisão é: " +mult);
            break;
            
        case 4: 
             double valorA = Double.parseDouble( JOptionPane.showInputDialog("Digite o primeiro valor:"));
             double valorB = Double.parseDouble( JOptionPane.showInputDialog("Digite o segundo valor:"));
             double resultdiv = calculadora.dividir(valorA, valorB);
             
             if(valorB != 0){
    JOptionPane.showMessageDialog(null,"O resultado da divisão é: " +resultdiv);
           
    }
             else { 
                 JOptionPane.showMessageDialog(null,"Erro! Divisão por zero");
             }
            
            break;
             case 0:
                
                JOptionPane.showMessageDialog(null, "Finalizando programa!");
                break;
            
        default:
            JOptionPane.showMessageDialog(null, "Opção invalida!");
            }
    
    
    } while (op != 0);
  
    }
}
