/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_1202;
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class TrocaNumero {
    public static void main(String[] args) {
        
        String valorA = JOptionPane.showInputDialog("Digite o primeiro numero(NumA): ");
        int NumA = Integer.parseInt(valorA);
        
        String valorB = JOptionPane.showInputDialog("Digte o segundo numero (NumB): ");
         int  NumB = Integer.parseInt(valorB);
         
         //recebe os numeros numa variavel int
         
         JOptionPane.showConfirmDialog(null, "Antes da troca, o NumA é: " +NumA+ " e o NumB é: "+NumB);
        //apresenta eles antes da troca 
         
         int troca = NumA;
         NumA = NumB;
         NumB = troca;
         
         //troca a variavel NumA pela NumB e cria uma variavel Int que Contem o valor do NumB 
         //troca o valor de NumB com a variavel "troca"
         
         JOptionPane.showConfirmDialog(null, "agora após a troca, o (NumA) é igual a: "+NumA+ " e o (NumB) é igual a: "+troca); 
         //apresenta a troca dos valores das variaveis para o usuario
    }
    
    
}
