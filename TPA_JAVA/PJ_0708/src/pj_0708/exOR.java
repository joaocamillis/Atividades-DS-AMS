/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_0708;
import java.util.Scanner;
/**
 *
 * @author FATEC ZONA LESTE
 */
public class exOR {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        
         System.out.println("Você ja jogou algum campeonato ? (DIGITE TRUE OU FALSE)");
        boolean per = scanner.nextBoolean();
        
        System.out.println("digite a sua idade: ");
        int id = scanner.nextInt();
        
        if (id >= 18 && id <= 29 || per)
            System.out.println("você atende a pelo menos um dos requisitos");
        else{
        
            System.out.println("Você não atende aos requisitos");
        
        }
       
    }
}
