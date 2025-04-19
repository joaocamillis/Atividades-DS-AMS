/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago
 */
public class Exercicio4 {

    public static void main(String[] args) {
        int opcao;

        do {
            String menu = "Escolha uma opção: \n"
                    + "1 - Soma\n"
                    + "2 - Subtração\n"
                    + "3 - Divisão\n"
                    + "4 - Multiplicação\n"
                    + "5 - Resto da Divisão\n"
                    + "6 - Dobro\n"
                    + "7 - Quadrado\n"
                    + "8 - Cubo\n"
                    + "9 - Raíz Qudrada\n"
                    + "0 - Sair";

            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            if (opcao != 0) {
                operacao(opcao);
            }
        } while (opcao != 0);
    }

    public static void operacao(int opcao) {
        double num1, num2, resultado;

        switch(opcao)
        {

case 1: //soma
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo numero"));
            resultado = num1 + num2;
            
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
            break;
            
            case 2: //subtração
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo numero"));
            resultado = num1 - num2;
            
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
            break;
            
            case 3: //divisão
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo numero"));
            if (num2 == 0){
        JOptionPane.showMessageDialog(null, "Erro: não é permitido divisão por zero!!! ");
        }
            
            else {
        resultado = num1 / num2;
        JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
           
        }
             break;
            
             case 4: //multiplicação
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo numero"));
            resultado = num1 * num2;
            
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
            break;
            
            
               case 5: //resto da divisão
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            num2 = Double.parseDouble(JOptionPane.showInputDialog("Digite o segundo numero"));
            if (num2 == 0){
        JOptionPane.showMessageDialog(null, "Erro: não é permitido divisão por zero!!! ");
        }
            
            else {
        resultado = num1 % num2;
        JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
           
        }
             break;
            
            
               case 6: //dobro
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            resultado = num1 * 2;
            
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
            break;
            
            
              case 7: //Quadrado
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            resultado = num1 * num1;
            
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
            break;
            
            
             case 8: //Cubo
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            resultado = num1 * num1 * num1;
            
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
            break;
            
            
            
              case 9: //raiz quadrada
            num1 = Double.parseDouble(JOptionPane.showInputDialog("Digite o primeiro numero"));
            
            if(num1 < 0){
            JOptionPane.showMessageDialog(null, "Erro: Raiz quadrada de numero negativo!!!");
        }
         
            else {
        resultado = Math.sqrt(num1);
            JOptionPane.showMessageDialog(null, "Resultado: " +resultado);
        }
            break;
            
            default:
            JOptionPane.showMessageDialog(null, "Opção invalida!");
            
    }
    }
}
