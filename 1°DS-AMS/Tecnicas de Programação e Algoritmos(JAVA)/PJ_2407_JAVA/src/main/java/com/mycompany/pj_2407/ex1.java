/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pj_2407;

import javax.swing.JOptionPane;

/**
 *
 * @author yarar
 */
public class ex1 {

    public static void main(String[] args) {
        int mc1 = 0, mc2 = 0, mc3 = 0, mc4 = 0, me = 0;

        mc1 = Integer.parseInt(JOptionPane.showInputDialog("Digite a primeira Menção"));

        mc2 = Integer.parseInt(JOptionPane.showInputDialog("Digite a segunda Menção"));

        mc3 = Integer.parseInt(JOptionPane.showInputDialog("Digite a terceira Menção"));

        mc4 = Integer.parseInt(JOptionPane.showInputDialog("Digite a quarta Menção"));

        me = (mc1 + mc2 + mc3 + mc4) / 4;

        JOptionPane.showMessageDialog(null, "sua media é: " + me);
        
        if(me>5){
            JOptionPane.showMessageDialog(null,"você foi aprovado");
        } else {
    JOptionPane.showMessageDialog(null,"você foi reprovado");
    }
    }
}
