package pj_0409;

import java.util.Scanner;

public class Ex02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o número para calcular a tabuada: ");
        int numero = scanner.nextInt();

        System.out.println("Tabuada do " + numero + ":");

        for (int i = 1; i <= 10; i++) {
            int resultado = numero * i;
            System.out.println(numero + " x " + i + " = " + resultado);
        }

    }
}
