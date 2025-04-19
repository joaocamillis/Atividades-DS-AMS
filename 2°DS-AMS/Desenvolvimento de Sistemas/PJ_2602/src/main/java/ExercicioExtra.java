/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author Thiago
 */
public class ExercicioExtra {
    public static void main(String[] args) {
        int opcao;
    
    do
    {
    String menu = "Escolha um sabor de Pizza e veja os ingredientes: \n"
  + "1 - Mussarela: 38 reais\n"
  + "2 - Calabresa: 45 reais\n"   
  + "0 - Sair";      
            
            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
           if(opcao != 0){
           ingre(opcao);
           }  
    }
    
   while (opcao != 0);
}

public static void ingre (int opcao){
String resultado;

switch(opcao){
case 1: 

resultado = "500 g de queijo de mussarela.\n" +
"Molho de tomate a gosto.\n" +
"Tomate em rodelas a gosto.\n" +
"Orégano a gosto.";
JOptionPane.showMessageDialog(null, "Ingredientes:\n" +resultado);

break;

case 2:

resultado = "Molho de tomate \n"+
            "calabresa \n"+
            "cebola\n"+
            "oregano";
JOptionPane.showMessageDialog(null, "Ingredientes:\n" +resultado);

break;
}
    }
}
