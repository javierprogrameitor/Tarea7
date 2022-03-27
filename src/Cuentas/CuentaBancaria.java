package Cuentas;

//Clase abstracta con sus atributos heredables para las demás clases.
 public abstract class CuentaBancaria implements Imprimible{
 
   double saldo;
   String CCC;
   Persona titular;

    public CuentaBancaria(double saldo, String CCC, Persona titular) {
        this.saldo = saldo;
        this.CCC = CCC;
        this.titular = titular;
       
    }
    
    @Override
    public String toString() {
        return "CuentaBancaria{" + "saldo=" + saldo + ", CCC=" + CCC + ", titular=" + titular + '}';
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCCC() {
        return CCC;
    }

    public void setCCC(String CCC) {
        this.CCC = CCC;
    }

    public Persona getTitular() {
        return titular;
    }

    public void setTitular(Persona titular) {
        this.titular = titular;
    }

}