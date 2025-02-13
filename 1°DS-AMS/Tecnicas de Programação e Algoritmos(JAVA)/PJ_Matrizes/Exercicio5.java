/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarar
 */
import javax.swing.JOptionPane;

public class Exercicio5 {
    public static void main(String[] args) {
        // Matriz para Figura A
        char[][] figuraA = {
            {'*', '*', '*', '*', '*'},
            {'*', '*', '*', '*', '*'},
            {'*', '*', '*', '*', '*'},
            {'*', '*', '*', '*', '*'}
        };

        // Matriz para Figura B
        char[][] figuraB = {
            {'*', '*', '*', '*'},
            {'*', ' ', ' ', '*'},
            {'*', ' ', ' ', '*'},
            {'*', '*', '*', '*'}
        };

        // Matriz para Figura C
        char[][] figuraC = {
            {'*', ' ', ' ', ' '},
            {'*', '*', ' ', ' '},
            {'*', '*', '*', ' ',},
            {'*', '*', '*', '*'}
        };

        // Exibir Figuras
        StringBuilder figuras = new StringBuilder();

        figuras.append("Figura A:\n");
        for (char[] linha : figuraA) {
            for (char elemento : linha) {
                figuras.append(elemento).append(" ");
            }
            figuras.append("\n");
        }

        figuras.append("\nFigura B:\n");
        for (char[] linha : figuraB) {
            for (char elemento : linha) {
                figuras.append(elemento).append(" ");
            }
            figuras.append("\n");
        }

        figuras.append("\nFigura C:\n");
        for (char[] linha : figuraC) {
            for (char elemento : linha) {
                figuras.append(elemento).append(" ");
            }
            figuras.append("\n");
        }

        // Exibir em JOptionPane
        JOptionPane.showMessageDialog(null, figuras.toString());
    }
}
