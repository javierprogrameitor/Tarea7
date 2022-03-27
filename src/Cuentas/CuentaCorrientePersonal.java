package Cuentas;

public class CuentaCorrientePersonal extends CuentaCorriente {//ClaseCuentaCorrientePersonal que hereda de la Clase Abstracta CuentaCorriente.

    float com_mant ; // Su nuevo atributo

    public CuentaCorrientePersonal(double saldo, String CCC, Persona titular, float com_mant) { // El constructor
        super(saldo, CCC, titular); //herencia
        this.com_mant= com_mant;   // nuevo atributo
    }

    public float getCom_mant() {
        return com_mant;
    }

    @Override
    public String toString() {
        return "  CuentaCorrientePersonal{" + "com_mant=" + com_mant + '}';
    }

    @Override
    public void devolverInfoString() {
    }
    
}