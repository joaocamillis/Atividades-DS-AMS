package pj_1202;

import javax.swing.JOptionPane;

public class SomaMultipl {

    public static void main(String[] args) {

        int somaimpares = 0;
        long produtopares = 1;

        //coloca o tipo de variveis que vou trabalhar e os seus valores iniciais
        for (int i = 1; i <= 30; i++) //cria um "para" onde a variavel int "i" comece do numero 1 e seja apresentada em oredem crescente até 30 
        {
            //cria uma condicão para separar os numeros pares dos impares, se o resultado da condição for 0, o numero é par

            if (i % 2 == 0) {
                if (i > 0 && i != 0) {

                    produtopares *= i;//os numeros pares da variavel "i" serão multiplicados entre eles
                }

                //se a condicão não for verdadeira, o numero é impar
            } else {
                somaimpares += i; //os numeros impares da variavel "i" serão somados entre eles

            }
        }

        JOptionPane.showMessageDialog(null, "A soma dos numeros impares até 30 é: " + somaimpares);
        JOptionPane.showMessageDialog(null, "A multiplicação dos numeros pares até 30 é: " + produtopares);
//e aqui apresentará ao usuario a soma dos impares e a multiplicação dos pares
    }
}
