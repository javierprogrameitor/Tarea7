
package Cuentas;

//Clase que hereda de CuentaBancaria, tiene interés como atributo propio

public class CuentaAhorro extends CuentaBancaria {
   
    float interes;

    public CuentaAhorro(double saldo, String CCC, Persona titular, float interes) {
        super(saldo, CCC, titular);
        this.interes=interes;
    }

  //Tiene sus getters y setters además del String y el Info del Imprimible

    @Override
    public String toString() {
        return "  CuentaAhorro{" + "interes=" + interes + '}';
    }


    
    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    @Override
    public void setTitular(Persona titular) {
        super.setTitular(titular); 
    }

    @Override
    public Persona getTitular() {
        return super.getTitular(); 
    }

    @Override
    public void setCCC(String CCC) {
        super.setCCC(CCC); 
    }
    @Override
    public String getCCC() {
        return super.getCCC(); 
    }

    @Override
    public void setSaldo(double saldo) {
        super.setSaldo(saldo); 
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    @Override
    public void devolverInfoString() {
       
    }
 
}