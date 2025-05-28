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

    private DadosPrincipal principal;

    JFormattedTextField nome, idade;
    JButton btnLimpar, btnEnviar;
    JCheckBox CAuto, CBarco, CAviao;
    ButtonGroup grupo;
    JLabel result, inte, Ec, Lsexo, erro;
    JRadioButton masc, fem;
    JComboBox lista;
    String civil[] = {"Solteiro(a)", "Casado(a)"};

    public Interface(DadosPrincipal principal) {
        super("Interface");
        this.principal = principal;
        ImageIcon icone = new ImageIcon("./imagem/icone.png");
        setIconImage(icone.getImage());
        Container tela = getContentPane();
        tela.setBackground(Color.white);

        //--------------------de texto-----------------------------
        nome = new JFormattedTextField("Insira aqui o seu nome...");

        idade = new JFormattedTextField();

        nome.setBounds(80, 30, 150, 30);
        idade.setBounds(80, 80, 100, 30);

        JLabel erroIdade = new JLabel(); 
        erroIdade.setForeground(Color.RED);
        erroIdade.setBounds(190, 80, 200, 30);
        add(erroIdade);

        JLabel LNome = new JLabel("Nome: ");
        JLabel LIdade = new JLabel("Idade: ");

        LNome.setBounds(20, 30, 150, 30);
        LIdade.setBounds(20, 80, 100, 30);

        add(nome);
        add(idade);
        add(LIdade);
        add(LNome);

        //------------------radio button sexo----------------------------------------
        Lsexo = new JLabel("Sexo: ");
        Lsexo.setBounds(20, 120, 100, 20);

        masc = new JRadioButton("Masculino");
        fem = new JRadioButton("Feminino");

        masc.setBounds(80, 120, 100, 20);
        fem.setBounds(180, 120, 100, 20);

        grupo = new ButtonGroup();
        grupo.add(masc);
        grupo.add(fem);

        add(masc);
        add(fem);
        add(Lsexo);

        //------------------checkBox-----------------------------------------------------
        inte = new JLabel("Interesses:");
        inte.setBounds(20, 160, 100, 20);

        CAuto = new JCheckBox("Automoveis");
        CAuto.setBounds(20, 190, 100, 20);

        CBarco = new JCheckBox("Barcos");
        CBarco.setBounds(20, 220, 100, 20);

        CAviao = new JCheckBox("Aviões");
        CAviao.setBounds(20, 250, 100, 20);

        add(inte);
        add(CAuto);
        add(CBarco);
        add(CAviao);

        //----------------Lista--------------------------------
        Ec = new JLabel("Estado Civil:");
        Ec.setBounds(20, 300, 100, 20);

        lista = new JComboBox(civil);

        lista.setMaximumRowCount(2);

        lista.setBounds(150, 300, 150, 20);

        add(Ec);
        add(lista);

        //-------------resultado-------------------------------------------
        result = new JLabel();

        result.setBounds(500, 1, 200, 400);
        add(result);

        //-------------botões----------------------------------
        btnEnviar = new JButton("Enviar");
        btnLimpar = new JButton("Limpar");

        //atalhos bnt
        btnEnviar.setMnemonic(KeyEvent.VK_E);
        btnLimpar.setMnemonic(KeyEvent.VK_Q);

        btnLimpar.setBounds(50, 500, 100, 30);
        btnEnviar.setBounds(180, 500, 80, 30);

        add(btnLimpar);
        add(btnEnviar);

        //--------------------acões-----------------------------------
        btnEnviar.addActionListener((ActionEvent e) -> {
            DadosPessoais dados = new DadosPessoais();

            dados.setNome(nome.getText());

            try {
                dados.setIdade(Integer.parseInt(idade.getText()));
                erroIdade.setVisible(false);
            } catch (NumberFormatException ex) {
                erroIdade.setText("Erro: Apenas numeros!");
                erroIdade.setVisible(true);
                return;
            }

            //-----------------JRadioBox--------------------------------------
            if (masc.isSelected()) {
                dados.setSexo("Masculino");
            } else if (fem.isSelected()) {
                dados.setSexo("Feminino");
            } else {
                dados.setSexo("Não informado");
            }

            String interesses = "\n";

            if (CAuto.isSelected()) {
                interesses += " Automoveis <br>";
            }
            if (CBarco.isSelected()) {
                interesses += " Barcos <br> ";
            }
            if (CAviao.isSelected()) {
                interesses += " Aviões <br> ";
            }
            dados.setInteresses(interesses.trim());

            //------------------------JCheckBox----------------------------
            dados.setEstadoCivil(lista.getSelectedItem().toString());
            
            //--------------usando html basico para organização de resultados JAVA--------------:]---------

            String txt = "<html>"
                    + "Nome: " + dados.getNome() + "<hr>" + "<br>"
                    + "<br> Idade: " + dados.getIdade() + "<hr>" + "<br>"
                    + "<br> Sexo:  " + dados.getSexo() + "<hr>" + "<br>"
                    + "<br>Interesses: <br> <hr>" + dados.getInteresses()+ "<hr>" + "<br>"
                    + "Estado Civil: " + dados.getEstadoCivil() + "<hr>"
                    + "<html>";

            result.setText(txt);

            this.principal.dados = dados;

        });
        
        
        nome.addFocusListener(new FocusAdapter()  {
            public void focusGained(FocusEvent e) {
        if (nome.getText().equals("Insira aqui o seu nome...")) {
            nome.setText("");
        
        }
            }
     
      
         public void focusLost(FocusEvent e) {
        if (nome.getText().isEmpty()) {
            nome.setText("Insira aqui o seu nome...");
        }
    }
        
        
            });
        
        JLabel rodape = new JLabel("João Pedro Souza - 2DS/AMS");
        rodape.setForeground(new Color(46,139,87));
        rodape.setBounds(300, 550, 480, 30);
        add(rodape);
        
        
        btnLimpar.addActionListener(e -> {
            nome.setText("");
            idade.setText("");
            grupo.clearSelection();
            CAuto.setSelected(false);
            CBarco.setSelected(false);
            CAviao.setSelected(false);
            lista.setSelectedIndex(0);
            result.setText("");
        });
         //=====================estilo dos Label========================================
          LNome.setForeground(new Color(46,139,87));
          LIdade.setForeground(new Color(46,139,87));
          Lsexo.setForeground(new Color(46,139,87));
          inte.setForeground(new Color(46,139,87));        
          Ec.setForeground(new Color(46,139,87));
          
          //------------------------estilo dos buttons--------------------------------
          btnEnviar.setBackground(new Color(46,139,87));
          btnLimpar.setBackground(new Color(46,139,87));
          
           //------------------------estilo dos JRadio--------------------------------
         masc.setBackground(Color.white);
         fem.setBackground(Color.white);
          //------------------------estilo dos JCheckBox--------------------------------
          CAuto.setBackground(Color.white);
          CBarco.setBackground(Color.white);
          CAviao.setBackground(Color.white);      
          //------------------------estilo da lista--------------------------------
           lista.setBackground(Color.white);
          //------------------------estilo do resultado--------------------------------
            result.setForeground(new Color(46,139,87));
        //-----colocando a dimensão, visibilidade e saida da janela-------------
        setSize(800, 700);
         tela.setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
