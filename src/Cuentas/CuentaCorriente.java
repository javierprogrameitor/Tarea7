package Cuentas;


public abstract class CuentaCorriente extends CuentaBancaria {

   // private static final long serialVersionUID = 1L;

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String getCCC() {
        return CCC;
    }

    @Override
    public void setCCC(String CCC) {
        this.CCC = CCC;
    }

    @Override
    public Persona getTitular() {
        return titular;
    }

    @Override
    public void setTitular(Persona titular) {
        this.titular = titular;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('}');
        return sb.toString();
    }

    @Override
    public abstract void devolverInfoString();
    
     
       

    public CuentaCorriente(double saldo, String CCC, Persona titular) {
        super(saldo, CCC, titular);
     
    }



}