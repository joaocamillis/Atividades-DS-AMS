package pj_0409;

import java.util.Scanner;

public class Ex05 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        int numero1 = scanner.nextInt();

        System.out.print("Digite o segundo número: ");
        int numero2 = scanner.nextInt();

        if (numero1 > numero2) {
            int temp = numero1;
            numero1 = numero2;
            numero2 = temp;
        }

        int inicio = (numero2 % 2 == 0) ? numero2 : numero2 - 1;

        for (int i = inicio; i >= numero1; i -= 2) {
            System.out.println(i);
        }

    }
}
