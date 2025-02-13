/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex3 {
    public static void main(String[] args) {
        int opcao;
         opcao = Integer.parseInt(JOptionPane.showInputDialog("coloque um numero de 1 a 5: "));       
        
        String lanche;
        switch (opcao) {
            case 1:
                lanche = "BigMac";
                break;
            case 2:
                lanche = "Quarteirão";
                break;
            case 3:
                lanche = "MacChicken";
                break;
            case 4:
                lanche = "Cheddar";
                break;
            case 5:
                lanche = "Cheese Burger";
                break;
            default:
                lanche = "Opção inválida.";
                break;
        }
        
     JOptionPane.showMessageDialog(null, "Você escolheu: " + lanche);

               
 
           
    }
        
        
    }

