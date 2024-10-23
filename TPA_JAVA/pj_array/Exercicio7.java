/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pj_array;

/**
 *
 * @author FATEC ZONA LESTE
 */
import java.util.ArrayList;

public class Exercicio7 {
    public static void main(String[] args) {
        ArrayList<String> tabelaClassificacao = new ArrayList<>();
        tabelaClassificacao.add("Botafogo - 61 pontos");
        tabelaClassificacao.add("Palmeiras - 58 pontos");
        tabelaClassificacao.add("Flamengo - 57 pontos");

        System.out.println("Tabela de Classificação:");
        for (String time : tabelaClassificacao) {
            System.out.println(time);
        }
    }
}
