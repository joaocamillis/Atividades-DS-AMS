

import javax.swing.JOptionPane;

public class Pessoa {
    private String nome;
    private String endereco;
    private Double salario;
    private String telefone;
    private String email;
    
    public Pessoa(){
    this("", "",0.0,"","");
    }
    
    public Pessoa(String nome, String endereco, Double salario, String telefone, String email ){
    this.nome = nome;
    this.endereco = endereco;
    this.salario = salario;
    this.telefone = telefone;
    this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public Double getSalario() {
        return salario;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void InserirDadosPessoa() {
        
        setNome (JOptionPane.showInputDialog("Digite o seu nome:"));
        setEndereco(JOptionPane.showInputDialog("Digite o seu endereco:"));
        
      
        String salarioStr = JOptionPane.showInputDialog("Digite o seu salario:");
        setSalario (Double.parseDouble(salarioStr));
        
        setTelefone (JOptionPane.showInputDialog("Digite o seu telefone:"));
        setEmail (JOptionPane.showInputDialog("Digite o seu email:"));
    }

    public void apresentarPessoa() {
        
        String mensagem = "Dados da Pessoa: \n";
        mensagem += "Nome: " + getNome() + "\n";
        mensagem += "Endereco: " + getEndereco() + "\n";
        mensagem += "Salario: " + getSalario() + "\n";
        mensagem += "Telefone: " + getTelefone() + "\n";
        mensagem += "Email: " + getEmail() + "\n";

      
        JOptionPane.showMessageDialog(null, mensagem);
    }
}

