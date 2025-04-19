/*
 * 
 * 
 */
package pj_1202;
import javax.swing.JOptionPane;

public class OrganizarNumero {
    public static void main(String[] args) {
        int n1 = 0, n2 = 0, n3 = 0;

        String input1 = JOptionPane.showInputDialog("Digite o primeiro numero inteiro");
        n1 = Integer.parseInt(input1);

        String input2 = JOptionPane.showInputDialog("Digite o segundo numero inteiro");
        n2 = Integer.parseInt(input2);

        String input3 = JOptionPane.showInputDialog("Digite o terceiro numero inteiro");
        n3 = Integer.parseInt(input3);
        //recebe o valor das int variaveis dadas pelo usuario
        
        //abaixo, vejo qual numero é o maior, do meio e o menor e então apresento eles de acordo com cada if e else

        if (n1 >= n2 && n1 >= n3 && n2 >= n3) {
            JOptionPane.showMessageDialog(null, "Os números em ordem decrescente são: " + n1 + " " + n2 + " " + n3);
        }

       else if (n2 >= n1 && n2 >= n3 && n1 >= n3) {
            JOptionPane.showMessageDialog(null, "Os números em ordem decrescente são: " + n2 + " " + n1 + " " + n3);
        }

       else if (n3 >= n2 && n3 >= n1 && n2 >= n1) {
            JOptionPane.showMessageDialog(null, "Os números em ordem decrescente são: " + n3 + " " + n2 + " " + n1);
        }
    }
}
