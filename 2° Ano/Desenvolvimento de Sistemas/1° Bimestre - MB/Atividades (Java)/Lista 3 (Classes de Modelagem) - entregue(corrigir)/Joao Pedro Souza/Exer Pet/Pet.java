/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;

/**
 *
 * @author yarar
 */
public class Pet {

    private String nome;
    private String raça;
    private int idade;
    private String cor;
    
    public Pet(){
    this("","",0,"");
    }
    
    public Pet(String nome, String raça, int idade, String cor){
    this.nome = nome;
    this.raça = raça;
    this.idade = idade;
    this.cor = cor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    

    public String getNome() {
        return nome;
    }

    public String getRaça() {
        return raça;
    }

    public int getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public void InserirdadosPet() {

        setNome(  JOptionPane.showInputDialog(null, "Digite o nome do seu pet: "));

        setRaça ( JOptionPane.showInputDialog(null, "Digite a raça do seu pet: "));

        setCor(  JOptionPane.showInputDialog(null, "Digite a cor do seu pet: "));

        String Idade = JOptionPane.showInputDialog(null, "Digite a idade do seu pet: ");
        setIdade(  Integer.parseInt(Idade));
    }

    public void ApresentardadosPet() {
 
        String mensagem = "Dados do Pet: \n";
        
        
       mensagem += "O nome do seu Pet é: " + getNome() + "\n";

        mensagem += "A raça do seu Pet é: " + getRaça() + "\n";

        mensagem += "A cor do seu Pet é: " + getCor() + "\n";

        mensagem += "A idade do seu Pet é: " + getIdade() + "\n";
        
        JOptionPane.showMessageDialog(null, mensagem);
    }
}


