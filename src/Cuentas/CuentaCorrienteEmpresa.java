package Cuentas;

public class CuentaCorrienteEmpresa extends CuentaCorriente  { //ClaseCuentaCorrienteEmpresa que hereda de la Clase Abstracta CuentaCorriente.

   float interes_desc;
   float maximo_desc;  // Sus atributos de clase
   float comisi_desc;

    public CuentaCorrienteEmpresa(double saldo, String CCC, Persona titular, float interes_desc, float maximo_desc, float comisi_desc) {
        super(saldo, CCC, titular );
        this.interes_desc = interes_desc;
        this.maximo_desc = maximo_desc;
        this.comisi_desc = comisi_desc;
    }

   
    public float getInteres_desc() {
        return interes_desc;
    }
    public void setInteres_desc(float interes_desc){
      this.interes_desc = interes_desc;
    }   
    public float getMaximo_desc() {
        return maximo_desc;
    }
    public void setMaximo_desc(float maximo_desc){
        this.maximo_desc=maximo_desc;
    }
     public float getComisi_desc() {
        return comisi_desc;
    }
    public void setComisi_desc(float comisi_desc){
        this.comisi_desc=comisi_desc;
    }
    @Override
    public String toString() {
        return " CuentaCorrienteEmpresa{" + "interes_desc=" + interes_desc + ", maximo_desc=" + maximo_desc + '}';
    }
    @Override
    public void devolverInfoString() {
  
    }
}