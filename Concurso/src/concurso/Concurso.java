
package concurso;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class Concurso {
    
    protected String nombre;
    protected int numeroproblema;
    protected ArrayList<String> listaequipo;
    protected int tiempo;
    protected double fin;
    protected ArrayList<String> soluciones;
    protected ArrayList<Envio> listaenvio;
    protected boolean iniciado;
    protected boolean marcha;
    protected boolean finalizado;
    
    
    Concurso(String nom,int num,int tiem){
        nombre = nom;
        numeroproblema = num;
        tiempo = tiem;
        listaequipo = new ArrayList<String>();
        soluciones = new ArrayList<String>();
        iniciado = false;
        marcha = false;
        finalizado = false;
        listaenvio = new ArrayList<Envio>();
        
    }

    public int getNumeroproblema() {
        return numeroproblema;
    }
    //metodo para añadir equipo recibiendo argumento variable
    public void añadirequipo(String ... equipos){
        for (String equipo : equipos) {
            listaequipo.add(equipo);
        }
    }
    public void AñadirSolucion(String ... solu){
        for (String string : solu) {
            soluciones.add(string);
        }
    }
    public boolean eliminarequipo(String e){
        return false;
    }
    public String Consultarsolucion(){
        return null;
    }
    @Override
    public Concurso clone() throws CloneNotSupportedException 
    { 
        Concurso aux = (Concurso)super.clone();
        aux.iniciado = false;
        aux.listaenvio.removeAll(null);
        return aux; 
    } 
    public boolean preparado(){
        if(soluciones.size()>= numeroproblema){return true;}
        return false;
    }
    public boolean iniciar(){
        //verificamos que este preparado
        if(!preparado()){return false;}
        fin = (int) (tiempo*60000+System.currentTimeMillis());
        marcha = true;
        return true;
    }
    public Envio Registrarenvio(String equi,int indice, String re){
        //validamos las primeras condiciones

        if( !listaequipo.contains(equi) || (indice >= numeroproblema) || re.isEmpty() || !marcha){
            return null;
        }
        
        //dejamos indicado que registrarasegun el tipo
        return RegistrarSegunTipo( equi, indice,  re);
    }
    abstract Envio RegistrarSegunTipo(String equi,int indice, String re);
    public ArrayList<Envio> Consultarenvios(){
        return listaenvio;
    }
}

class ConcursoLimitado extends Concurso{
    private int numerointento;    
    public ConcursoLimitado(String nom, int num, int tiem,int intentos) {
        super(nom, num, tiem);
        numerointento = intentos;
    }
//registramos segun concurso limitado
    Envio RegistrarSegunTipo(String equi, int indice, String re) {
        int auxint = 0;
        Envio aux = null;
        //verificamos Cuantos intento a echo
        for (Envio envio : listaenvio) {
            if(equi.equals(envio.getNombreequipo()) && indice == envio.getNumeroproblema()){
                auxint += 1;
            }
        }
        if(auxint >= numerointento){return aux;}
        // si la respuesta es aceptada o rechazada
        if(re.equals(soluciones.get(indice))){
            aux = new Envio(equi, indice, re, Envio.Evaluacion.Aceptado);
        }else{
            aux = new Envio(equi, indice, re, Envio.Evaluacion.Rechazado);
        }
        listaenvio.add(aux);
        return aux;
    }
     public ConcursoLimitado clone() throws CloneNotSupportedException 
    { 
        ConcursoLimitado aux = (ConcursoLimitado) super.clone();
        return  aux;
    } 

}

class Concursocompetitivo extends Concurso{

    private Map<Integer , String> mapa;
    public Concursocompetitivo(String nom, int num, int tiem) {
        super(nom, num, tiem);
        mapa = new HashMap<Integer, String>();
        
    }
     public Concursocompetitivo clone() throws CloneNotSupportedException 
    { 
        Concursocompetitivo aux = (Concursocompetitivo) super.clone();
        aux.iniciado = false;
        aux.listaenvio.removeAll(null);
        return  aux;
    } 
    Envio RegistrarSegunTipo(String equi, int indice, String re) {
        Envio auxenvio = null;
        if(!mapa.containsKey(indice)){
            if(re.equals(soluciones.get(indice))){
               auxenvio = new Envio(equi, indice, re, Envio.Evaluacion.Aceptado);
               mapa.put(indice, re);
            }else{
               auxenvio = new Envio(equi, indice, re, Envio.Evaluacion.Rechazado);
            }
            listaenvio.add(auxenvio);
        }
        return auxenvio;
    }

}

class ConcuersoSecuencial extends Concurso{
    
    private Map<String,Integer > mapa;
    public ConcuersoSecuencial(String nom, int num, int tiem) {
        super(nom, num, tiem);
        mapa = new HashMap<String ,Integer >();
    }
     public ConcuersoSecuencial clone() throws CloneNotSupportedException 
    { 
        ConcuersoSecuencial aux = (ConcuersoSecuencial) super.clone();
        aux.iniciado = false;
        aux.listaenvio.removeAll(null);
        return  aux;
    } 

    Envio RegistrarSegunTipo(String equi, int indice, String re) {
        Envio auxenvio = null;
                
        //verificamos que este la respuesta anterior y que no halla respondido aun el problema y ademas si es la primera respuesta
        if( mapa.containsKey(equi) || indice == 0){
            if (mapa.containsKey(equi)){
                if(mapa.get(equi) == indice || mapa.get(equi) != (indice-1)){
                    return auxenvio;
                }
            }
            if(re.equals(soluciones.get(indice))){
                auxenvio = new Envio(equi, indice, re, Envio.Evaluacion.Aceptado);
                mapa.remove(equi);
                mapa.put(equi, indice);
                
            }else{
                auxenvio = new Envio(equi, indice, re, Envio.Evaluacion.Rechazado);
            }
            listaenvio.add(auxenvio);
        }
        return auxenvio;
    }
    String ProblemaActual(String equi){
        String aux = "1";
        int auxint = 0;
        if(mapa.containsKey(equi)){
            auxint = mapa.get(equi)+2;
            aux = ""+auxint;
        }
        
        return aux;
    }

}