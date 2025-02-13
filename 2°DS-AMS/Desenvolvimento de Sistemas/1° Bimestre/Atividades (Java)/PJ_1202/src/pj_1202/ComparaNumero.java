/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_1202;
import java.util.Scanner;
/**
 *
 * @author dti
 */
public class ComparaNumero {
    public static void main(String[] args) {
        int n1=0, n2=0;
        
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Digite o primeiro numero inteiro: ");
        n1 = ler.nextInt();
        
        System.out.println("Digite o primeiro numero inteiro: ");
        n2 = ler.nextInt();
        
        if( n1 == n2 ){
            System.out.println("os numeros são iguais");
            
        }
        
        if( n1 > n2 ){
            System.out.println("os numeros são difentes sendo o numero maior: " + n1 + " e o menor: " +n2);
            
        }
        
         if( n2 > n1 ){
            System.out.println("os numeros são difentes sendo o numero maior: " + n2 + " e o menor: " +n1);
            
        }
    }
}
