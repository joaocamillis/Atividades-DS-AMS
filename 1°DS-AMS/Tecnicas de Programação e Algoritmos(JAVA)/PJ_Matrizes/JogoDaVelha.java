/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yarar
 */
import javax.swing.JOptionPane;

public class JogoDaVelha {
    public static void main(String[] args) {
        char[][] tabuleiro = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        char jogadorAtual = 'X';
        boolean vencedorEncontrado = false;

        for (int jogadas = 0; jogadas < 9; jogadas++) {
            // Exibe o tabuleiro atual
            StringBuilder estadoTabuleiro = new StringBuilder("Tabuleiro atual:\n");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    estadoTabuleiro.append("[").append(tabuleiro[i][j]).append("] ");
                }
                estadoTabuleiro.append("\n");
            }

            // Solicita a jogada do jogador atual
            estadoTabuleiro.append("\nVez do jogador ").append(jogadorAtual).append("\n");
            JOptionPane.showMessageDialog(null, estadoTabuleiro.toString());

            int linha = Integer.parseInt(JOptionPane.showInputDialog("Jogador " + jogadorAtual + ", escolha a linha (0, 1 ou 2):"));
            int coluna = Integer.parseInt(JOptionPane.showInputDialog("Jogador " + jogadorAtual + ", escolha a coluna (0, 1 ou 2):"));

            // Verifica se a posição está disponível
            if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2 || tabuleiro[linha][coluna] != ' ') {
                JOptionPane.showMessageDialog(null, "Jogada inválida! Tente novamente.");
                jogadas--; // Não conta como jogada válida
                continue;
            }

            // Atualiza o tabuleiro
            tabuleiro[linha][coluna] = jogadorAtual;

            // Verifica se há um vencedor
            if (verificarVencedor(tabuleiro, jogadorAtual)) {
                vencedorEncontrado = true;
                break;
            }

            // Troca o jogador
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        // Exibe o resultado final
        StringBuilder resultadoFinal = new StringBuilder("Tabuleiro final:\n");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                resultadoFinal.append("[").append(tabuleiro[i][j]).append("] ");
            }
            resultadoFinal.append("\n");
        }

        if (vencedorEncontrado) {
            resultadoFinal.append("\nO jogador ").append(jogadorAtual).append(" venceu!");
        } else {
            resultadoFinal.append("\nO jogo terminou em empate!");
        }

        JOptionPane.showMessageDialog(null, resultadoFinal.toString());
    }

    // Método para verificar se há um vencedor
    private static boolean verificarVencedor(char[][] tabuleiro, char jogador) {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) || 
                (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador)) {
                return true;
            }
        }

        // Verifica diagonais
        if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) || 
            (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) {
            return true;
        }

        return false;
    }
}
