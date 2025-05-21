/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author dti
 */
public class DadosPessoais {
    private String nome;
    private int idade;
    private String sexo;
    private String interesses;
    private String estadoCivil;
    
    
    
    public DadosPessoais(){
this("",0,"","","");
}

public DadosPessoais(String nome, int idade, String sexo, String interesses, String estadoCivil){
    
    this.nome = nome;
    this.idade = idade;
    this.sexo = sexo;
    this.interesses = interesses;
    this.estadoCivil = estadoCivil;
    
}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setInteresses(String interesses) {
        this.interesses = interesses;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public String getInteresses() {
        return interesses;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }



}


