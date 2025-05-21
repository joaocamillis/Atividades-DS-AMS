/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;

/**
 *
 * @author yarar
 */
public class VariasFuncPrincipal {
    public static void main(String[] args) {
        VariasFunc func = new VariasFunc();
        int op;
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n1 - media de duas notas" +"\n2"
                    + " - diferença entre dois numeros \n3 - calcular Peso Ideal \n4 - achar valor da hipotenusa \n0 - Sair"));
            
             switch (op){
    
        case 1:
           String nome = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ");
     Double nota1 = func.lerNota("digite a primeira nota");
      Double nota2 = func.lerNota("digite a segunda nota");
      
      double media = func.calcularMedia(nota1, nota2);
      
      if(media >= 7.0 && media <=10.0 ){
         JOptionPane.showMessageDialog(null,"Aluno: " +nome+ "\n" + "Media: "+ media+ "\n" +"Aprovado");
      }
      
      else{
        JOptionPane.showMessageDialog(null,"Aluno: " +nome+ "\n" + "Media: "+ media+ "\n" +"Reprovado");
      }

            break;
            
        case 2:
            double valorA = Double.parseDouble( JOptionPane.showInputDialog("Digite o primeiro valor:"));
             double valorB = Double.parseDouble( JOptionPane.showInputDialog("Digite o segundo valor:"));
             func.Diferenca(valorA,valorB);
           break;
           
        case 3: 
             String sexo = JOptionPane.showInputDialog(null, "Informe o seu Sexo " + "\n" + "para sexo Feminino digite (F)" + "\n" + "para o sexo masculino digite (M)");
              double h = Double.parseDouble(JOptionPane.showInputDialog("Digite a sua altura em metros"));
              double resultsex = func.calcularPesoIdeal(sexo, h);
               double peso = func.calcularPesoIdeal(sexo.toUpperCase(), h);
        
        JOptionPane.showMessageDialog(null, "O seu peso ideal é: " +peso+ " KG");
            break;
            
        case 4: 
       double base = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da base do triangulo em metros "));
       double altura = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da altura do triangulo em metros "));
       double result = func.hipotenusa(base, altura);  
       double resultado = Math.sqrt(result);
       
       JOptionPane.showMessageDialog(null, "Valor da Hipotenusa ao quadrado : " +result+"\n"+""
               + " Valor da raiz quadrada da Hipotenusa: "+resultado);
       break;
       
        case 0:
                
                JOptionPane.showMessageDialog(null, "Finalizando programa!");
                break;
           
        default:
            JOptionPane.showMessageDialog(null, "Opção invalida!");
            }
    
            
        
        }while(op != 0);
    }
}
