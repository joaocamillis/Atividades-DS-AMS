/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class Funcionario {
   private String nome;
   private String telefone;
   private String email;
   private String CPF;
   private String RG;
   private Double salario;
   private char sexo;
   
   public Funcionario(){
   this("","","","","",0.0,'\0');
   }
   
   public Funcionario(String nome,String telefone, String email, String CPF, String RG, double salario, char sexo ){
   this.nome = nome;
   this.telefone = telefone;
   this.email = email;
   this.CPF = CPF;
   this.RG = RG;
   this.salario = salario;
   this.sexo = sexo;
   }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getCPF() {
        return CPF;
    }

    public String getRG() {
        return RG;
    }

    public Double getSalario() {
        return salario;
    }

    public char getSexo() {
        return sexo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
   
   
    public void InserirDados(){
    int sexoEs;
    setNome(JOptionPane.showInputDialog(null, "Digite o seu nome: "));
    setTelefone(JOptionPane.showInputDialog(null, "Digite o seu numero de telefone: "));
    setEmail (  JOptionPane.showInputDialog(null,"Digite o seu email: "));
    setCPF( JOptionPane.showInputDialog(null, "Digite o seu CPF: ")) ;
    setRG ( JOptionPane.showInputDialog(null, "Digite o seu RG: "));
    setSalario(Double.parseDouble(JOptionPane.showInputDialog(null, "Digite o seu salario: ")));
  do{
    sexoEs = Integer.parseInt( JOptionPane.showInputDialog(null,
            "Digite o seu sexo: " + 
            "\n se for homem digite (1) " +
             "\n se for mulher digite (2) "));
    
   switch(sexoEs){
       case 1:
           setSexo('M');
           break;
       case 2:
           setSexo('F');
           break;
       default:
           JOptionPane.showMessageDialog(null, "Erro! Digite um numero valido");
   }
   
    }while(sexoEs > 2 || sexoEs < 1);
  
  setSexo ( (sexoEs == 1) ? 'M' : 'F');
  }
    
    public void ApresentarDados(){
    String mensagem = "Dados do funcionario: \n ";
    
    mensagem += "O nome do funcionario é: " +getNome()+"\n";
    mensagem += "O numero de telefone do funcionario é: " +getTelefone()+"\n";
    mensagem += "O email do funcionario é: " +getEmail()+"\n";
    mensagem += "O CPF do funcionario é: " +getCPF()+"\n";
    mensagem += "O RG do funcionario é: " +getRG()+"\n";
    mensagem += "O salario do funcionario é: " +getSalario()+"\n";
    if (getSexo() == 'M'){
    mensagem+= " O sexo do funcionario é: Masculino"+"("+getSexo()+")";
    } 
    else if(getSexo() == 'F'){
    mensagem+= " O sexo do funcionario é: Feminino"+"("+getSexo()+")";
    } 
    
    JOptionPane.showMessageDialog(null,mensagem);
    
    }
    
}


