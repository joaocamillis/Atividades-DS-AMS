/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
/**
 *
 * @author dti
 */
public class DadosPrincipal {
    private DadosPessoais dados = new DadosPessoais();
    public static void main(String[] args) {
        new DadosPrincipal();
    }
    
    public DadosPrincipal(){
    new Interface(this);
    }
        
}
