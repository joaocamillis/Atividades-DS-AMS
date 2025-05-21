import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.swing.*;

public class CalculadoraForm extends JFrame {
    JFormattedTextField txt1, txt2;
    JLabel lblResult, lblTituloResult;
    JButton btnSoma, btnSub, btnMult, btnDiv, btnLimpar, btnHabilitar, btnDesabilitar, btnOcultar, btnExibir, btnSair;
    public CalculadoraForm(CalculadoraPrincipal principal) {
        super ("Calculadora");
        ImageIcon icone = new ImageIcon("./imagem/icone.png"); 
       setIconImage(icone.getImage());
        Container tela = getContentPane(); 
        setLayout(null);
        tela.setBackground(new Color(54,54,54));
        JLabel v1 = new JLabel("primeiro valor:");
        JLabel v2 = new JLabel("segundo valor:");
       
        
         txt1 = new JFormattedTextField();
        txt2 = new JFormattedTextField();
        
        
        
        //tamanho dos txt de valor
        v1.setBounds(125, 20, 100, 30);
        v2.setBounds(255, 20, 100, 30);
        
        v1.setForeground(Color.white);
        v2.setForeground(Color.white);
        
        //tamanho das caixas de numero
        txt1.setBounds(120, 50, 100, 30);
        txt2.setBounds(250, 50, 100, 30);
        add(txt1); add(txt2);add(v1);add(v2);

        //tamanho das caixas e txts de resultado
        lblTituloResult = new JLabel("Resultado:");
        lblTituloResult.setBounds(50, 80, 100, 30);
        lblResult = new JLabel("Escolha uma operação matematica: ");
        lblResult.setBounds(150, 80, 200, 30);
        
        lblResult.setForeground(Color.white);
        lblTituloResult.setForeground(Color.white);
        
        add(lblTituloResult); add(lblResult);

        
        // conteudos das caixas de operações
        btnSoma = new JButton(" + ");
        
        btnSub = new JButton(" - ");
        
        btnMult = new JButton(" * ");
        
        btnDiv = new JButton(" / ");
        
        btnLimpar = new JButton("Limpar");
        
        btnDesabilitar = new JButton("Desabilitar");

        btnHabilitar = new JButton("Habilitar");
        
               
        btnOcultar = new JButton("Ocultar");
        
        btnExibir = new JButton("Exibir");
        
        btnSair = new JButton("Sair");
        
        //atalhos com ALT 
        btnSoma.setMnemonic(KeyEvent.VK_Q);      
        btnSub.setMnemonic(KeyEvent.VK_W);   
        btnMult.setMnemonic(KeyEvent.VK_A);
        btnDiv.setMnemonic(KeyEvent.VK_S);
        btnSair.setMnemonic(KeyEvent.VK_E);
        
        //tamanho das caixas de operações

        btnSoma.setBounds(50, 130, 60, 30);
        
        btnSub.setBounds(120, 130, 60, 30);
        
        btnMult.setBounds(190, 130, 60, 30);
        
        btnDiv.setBounds(260, 130, 60, 30);
        
        btnLimpar.setBounds(330, 130, 100, 30);
        
        btnDesabilitar.setBounds(30, 180, 120, 30);
 
        btnHabilitar.setBounds(140, 180, 100, 30);
        
        btnOcultar.setBounds(270, 180, 100, 30);
        
        btnExibir.setBounds(360, 180, 80, 30);
        
        btnSair.setBounds(180, 230, 100, 30);
        
        
        
        //estilo
        
        btnSoma.setBackground(Color.black);
        
        btnSub.setBackground(Color.black);
        
        btnMult.setBackground(Color.black);
        
        btnDiv.setBackground(Color.black);
        
       btnLimpar.setBackground(Color.black);
        
        btnDesabilitar.setBackground(Color.black);
 
        btnHabilitar.setBackground(Color.black);
        
        btnOcultar.setBackground(Color.black);
        
        btnExibir.setBackground(Color.black);
        
        btnSair.setBackground(Color.black);
        
        //fonte
        
        btnSoma.setForeground(Color.white);
        
        btnSub.setForeground(Color.white);
        
        btnMult.setForeground(Color.white);
        
        btnDiv.setForeground(Color.white);
        
       btnLimpar.setForeground(Color.white);
        
        btnDesabilitar.setForeground(Color.white);
 
        btnHabilitar.setForeground(Color.white);
        
        btnOcultar.setForeground(Color.white);
        
        btnExibir.setForeground(Color.white);
        
        btnSair.setForeground(Color.white);
        
        //add bnt

        add(btnSoma); add(btnSub); add(btnMult); add(btnDiv); add(btnLimpar);
        add(btnHabilitar); add(btnDesabilitar); add(btnOcultar); add(btnExibir); add(btnSair);
        
         getRootPane().setDefaultButton(btnLimpar); 
        //nome no footer
        JLabel rodape = new JLabel("João Pedro Souza - 2DS/AMS");
        rodape.setForeground(Color.white);
        rodape.setBounds(180, 370, 480, 30);
        add(rodape);

        //atribuindo os valores que serão usados nos metodos das operações
        btnSoma.addActionListener(e -> principal.somar(txt1, txt2, lblResult));
        btnSub.addActionListener(e -> principal.subtrair(txt1, txt2, lblResult));
        btnMult.addActionListener(e -> principal.multiplicar(txt1, txt2, lblResult));
        btnDiv.addActionListener(e -> principal.dividir(txt1, txt2, lblResult));

        btnLimpar.addActionListener(e -> {
            txt1.setText(""); txt2.setText(""); lblResult.setText("Escolha uma operação matematica: ");
        });

        //atribuindo funções para esses btns
        btnHabilitar.addActionListener(e -> { lblTituloResult.setEnabled(true); lblResult.setVisible(true); });
        
        btnDesabilitar.addActionListener(e -> { lblTituloResult.setEnabled(false); lblResult.setVisible(false); });
        
        btnOcultar.addActionListener(e -> { lblTituloResult.setVisible(false); lblResult.setVisible(false); });
        
        btnExibir.addActionListener(e -> { lblTituloResult.setVisible(true); lblResult.setVisible(true); });
        
        btnSair.addActionListener(e -> System.exit(0));

        
        //colocando a dimensão, visibilidade e saida da janela
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
