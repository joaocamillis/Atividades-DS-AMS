/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;

public class Interface extends JFrame {

    JFormattedTextField nome, idade;
    JButton bntLimpar, bntEnviar;
    JCheckBox Inte;
    ButtonGroup grupo;
    JLabel result;
    JRadioButton masc, fem;
   
    
    public Interface(DadosPrincipal principal) {
    super ("Interface");
        ImageIcon icone = new ImageIcon("./imagem/icone.png"); 
       setIconImage(icone.getImage());
        Container tela = getContentPane(); 
        setLayout(null);
        setLocationRelativeTo(null);
        tela.setBackground(Color.white);
        JLabel LNome = new JLabel("Nome: ");
        JLabel LIdade = new JLabel("Idade: ");
        
        //de texto-----------------------------
        nome = new JFormattedTextField("Insira aqui o seu nome...");
        idade = new JFormattedTextField();
        
        
         LNome.setBounds(125, 20, 100, 30);
        LIdade.setBounds(255, 20, 100, 30);
        
        
        nome.setBounds(120, 50, 100, 30);
        idade.setBounds(250, 50, 100, 30);
        add(nome); add(idade);add(LIdade);add(LNome);
        
        //de sexo----------------------------------------
         JLabel Lsexo = new JLabel("Sexo: ");
          Lsexo.setBounds(50, 80, 100, 30);
          
          masc = new JRadioButton ("Masculino");
          fem = new JRadioButton ("Feminino");
          
          masc.setBounds(80, 80, 100, 30);
          fem.setBounds(100, 80, 100, 30);
          
          grupo = new ButtonGroup();
          grupo.add(masc);
          grupo.add(fem);
          
          
          //resultado
          JLabel result = new JLabel();
          
          
         
          //acões
           bntEnviar.addActionListener((ActionEvent e) -> {
              if(masc.isSelected()== true)
                  result.setText("O genero escolhido foi: " +result.getText());
              
              if(fem.isSelected() ==true)
                  result.setText("O genero escolhido foi: " +result.getText());
    });
          
          
           //colocando a dimensão, visibilidade e saida da janela
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
}
