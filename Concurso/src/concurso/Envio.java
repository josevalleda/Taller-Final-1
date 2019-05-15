
package concurso;

//clase envio del ejercicio
class Envio {


    private String nombreequipo;
    private int numeroproblema;
    private String repuesta;
    private Evaluacion evaluacion;
    
    Envio(String nom, int num,String re, Evaluacion eva){
        nombreequipo = nom;
        numeroproblema = num;
        repuesta = re;
        evaluacion = eva;
    }
    
        public String getNombreequipo() {
        return nombreequipo;
    }

    public int getNumeroproblema() {
        return numeroproblema;
    }

    public String getRepuesta() {
        return repuesta;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }
    enum Evaluacion{Aceptado,Rechazado}
}
