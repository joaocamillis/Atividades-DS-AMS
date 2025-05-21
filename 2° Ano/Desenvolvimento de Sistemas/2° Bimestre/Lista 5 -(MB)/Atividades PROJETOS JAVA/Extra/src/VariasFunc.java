/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JOptionPane;
/**
 *
 * @author yarar
 */
public class VariasFunc {
    private double v1;
    private double v2;
    private double res;
    
    public VariasFunc(){
    this(0.0,0.0,0.0);
    }

    public VariasFunc(double v1, double v2, double res) {
        this.v1 = v1;
        this.v2 = v2;
        this.res = res;
    }

    public double getV1() {
        return v1;
    }

    public double getV2() {
        return v2;
    }

    public double getRes() {
        return res;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }

    public void setV2(double v2) {
        this.v2 = v2;
    }

    public void setRes(double res) {
        this.res = res;
    }
    
    //------------------------primeira Função media de duas notas-------------------------------
    public double lerNota(String mensagem) {
    
         String input = JOptionPane.showInputDialog(mensagem);
                 return Double.parseDouble(input);
    }
    
     public double calcularMedia(double n1, double n2)
     {
         this.setV1(n1);
         this.setV2(n2);
         setRes((getV1() + getV2()) /2);
        return this.getRes();
         
     }
     
     //------------------------segunda função diferença entre dois numeros----------------------------------
     public void Diferenca (double v1, double v2)
    {
        setV1(v1);
        setV2(v2);
    
        if(getV1() > getV2())
        { 
             this.setRes(getV1() - getV2());
          JOptionPane.showMessageDialog(null,"O numero: " +getV1()+ " é maior que o numero: " +getV2()+ "\n" + "A diferença do maior numero para o menor é:  " +getRes() );
        }
      
      
         if(getV1() < getV2())
        { 
            setRes(getV2() - getV1());
          JOptionPane.showMessageDialog(null,"O numero: " +getV2()+ " é maior que o numero: " +getV1()+ "\n" + "A diferença do maior numero para o menor é:  " +getRes() );
        }
        
         if(getV1() == getV2())
        { 
            setRes(getV1() - getV2());
          JOptionPane.showMessageDialog(null,"O numero: " +getV1()+ " é igual ao numero: " +getV2()+ "\n" + "A diferença do maior numero para o menor é:  " +getRes() );
        }
     
    }
     
     //---------------------------------terceira função--------------------------------
      public double calcularPesoIdeal(String sexo, double h) {
          setV1(h);
        if (sexo.equals("M")) {
            setRes((72.7 * getV1())  - 58);
            return getRes();
            
        } else if (sexo.equals("F")) {
            setRes((62.1 * getV1()) - 44.7);
            return getRes();

        } else {
            JOptionPane.showMessageDialog(null, "Sexo não permitido, use (M) ou (F)");
            return 0;
            
        }

    }
      
      //----------------------------quarta e ultima função-------------------------------------------
       public double hipotenusa(double base, double altura){
    setV1(base);
    setV2(altura);
    setRes ((getV1()*getV1()) + (getV2()*getV2()));
        return getRes();
    }
}
