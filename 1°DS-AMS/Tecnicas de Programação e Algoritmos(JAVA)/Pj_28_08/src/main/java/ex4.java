/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class ex4 {
    public static void main(String[] args) {
          
         int opcao = Integer.parseInt(JOptionPane.showInputDialog("coloque um numero de 1 a 12: "));       
        
        String mes;
        switch (opcao) {
            case 1:
                mes = "Janeiro";
                break;
            case 2:
                mes = "Fevereiro";
                break;
            case 3:
                mes = "março";
                break;
            case 4:
                mes = "abril";
                break;
            case 5:
                mes = "maio";
                break;
                case 6:
                mes = "Junho";
                break;
            case 7:
                mes = "julho";
                break;
            case 8:
                mes = "agosto";
                break;
            case 9:
                mes = "setembro";
                break;
            case 10:
                mes = "outubro";
                break;
                 case 11:
                mes = "novembro";
                break;
            case 12:
                mes = "dezembro";
                break;
            default:
                mes = "Opção inválida.";
                break;
        }
        
     JOptionPane.showMessageDialog(null, "Você escolheu: " + mes);

               
    }
}
