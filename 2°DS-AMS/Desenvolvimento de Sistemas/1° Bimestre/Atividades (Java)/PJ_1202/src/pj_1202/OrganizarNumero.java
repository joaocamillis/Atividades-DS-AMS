/*
 * 
 * 
 */
package pj_1202;
import java.util.Scanner;
/**
 *
 * @author dti
 */
public class OrganizarNumero {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int n1=0, n2=0, n3=0;
        
        System.out.println("Digite o primeiro numero inteiro");
        n1 = ler.nextInt();
        
         System.out.println("Digite o segundo numero inteiro");
         n2 = ler.nextInt();
         
          System.out.println("Digite o terceiro numero inteiro");   
          n3 = ler.nextInt();
          
     if(n1 >= n2 && n1 >= n3 && n2 >= n3){
         System.out.println("os numeoros em ordem decrescente são: " + n1 + " " + n2 + " " + n3);
         
    }
      if(n2 >= n1 && n2 >= n3 && n1 >= n3){
         System.out.println("os numeoros em ordem decrescente são: " + n2 + " " + n1 + " " + n3);
       
         
    }
      
       if(n3 >= n2 && n3 >= n1 && n2 >= n1){
         System.out.println("os numeoros em ordem decrescente são: " + n3 +" " + n2 + " " + n1);
         
         
    }
}
}
