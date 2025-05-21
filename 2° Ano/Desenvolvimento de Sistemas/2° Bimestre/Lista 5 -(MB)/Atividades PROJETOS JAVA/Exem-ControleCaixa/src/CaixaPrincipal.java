/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class CaixaPrincipal {
    public static void main(String[] args) {
        Caixa cx1 = new Caixa();
        int op;
        
        do{
        op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1 - Entrada" + "\n2 - Retirada \n3 - Consultar saldo \n0 - Sair" ));
        
        switch (op){
        
            case 1:
                cx1.entrar();
                break;
                
            case 2: 
                cx1.Retirar();
                break;
                
            case 3: 
                JOptionPane.showMessageDialog(null, "Saldo atual: " +cx1.getSaldo());
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
