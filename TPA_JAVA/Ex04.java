package pj_0409;

import java.util.Scanner;

public class Ex04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o valor inicial: ");
        int valorInicial = scanner.nextInt();

        System.out.print("Informe o valor final: ");
        int valorFinal = scanner.nextInt();

        int soma = 0;

        if (valorInicial > valorFinal) {
            int temp = valorInicial;
            valorInicial = valorFinal;
            valorFinal = temp;
        }

        if (valorInicial / 2 != 0) {
            valorInicial++;
        }

        for (int i = valorInicial; i <= valorFinal; i += 2) {
            soma += i;
        }

        System.out.println("A soma dos números pares no intervalo é: " + soma);

    }
}
