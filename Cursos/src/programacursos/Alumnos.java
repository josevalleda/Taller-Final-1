package programacursos;


import java.util.ArrayList;


public class Alumnos {
    
    private String nombre;
    private String dni;
    private double credito;
    private ArrayList<Curso> ListaCurso;
    
    public Alumnos(String nombre, String dni, double credito) {
        this.nombre = nombre;
        this.dni = dni;
        // verificamos si se envio algun paramentro de credito
        if(credito == 0){
             this.credito = 100;
        }else{
            this.credito = credito;
        }
        ListaCurso = new ArrayList<Curso>();
    }

    public double getCredito() {
        return credito;
    }

    public void IncremetarCredito(double credito) {
        this.credito += credito;
    }
    public void DecrementarCredito(double credito) {
        this.credito -= credito;
    }
    public void AñadirCurso(Curso cu){
        ListaCurso.add(cu);
    }
    public ArrayList<Curso> GetListacurso(){
        return ListaCurso;
    }
    public String toString (){
        return "Nombre:"+nombre+"--dni:"+dni+"--credito:"+credito;
    }

    public String getNombre() {
        return nombre;
    }
    
}
