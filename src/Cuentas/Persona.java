package Cuentas;

//Clase persona que implementa imprimible con sus atributos
// específicos,constructores,setters y getters, toString y InfoString
public class Persona implements Imprimible {

  private  String nombre;
   private  String apellidos;
    private String fecha_nacimiento;

    Persona() {
               //Constructor vacio para el el main
    }

    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellidos=" + apellidos + ", fecha_nacimiento=" + fecha_nacimiento + '}';
    }


    public Persona(String nombre, String apellidos, String fecha_nacimiento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public void devolverInfoString() {

    }
}
