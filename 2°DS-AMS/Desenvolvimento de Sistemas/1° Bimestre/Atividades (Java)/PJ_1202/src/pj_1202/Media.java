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
public class Media {
    public static void main(String[] args) {
        
        int m1=0, m2=0, m3=0, m4=0, nf = 0;
        
        Scanner ler = new Scanner(System.in);
        
        System.out.println("informe o seu nome: ");
        
        System.out.println("Digite a primeira nota: ");
        m1 = ler.nextInt();
        
        System.out.println("Digite a segunda nota: ");
        m2 = ler.nextInt();
        
        System.out.println("Digite a terceira nota: ");
        m3 = ler.nextInt();
        
        System.out.println("Digite a quarta nota: ");
        m4 = ler.nextInt();
        
        
        nf = (m1 + m2 + m3 + m4)/4;
        
        System.out.println("a sua nota final em matematica sera: " +nf);
    }
}
