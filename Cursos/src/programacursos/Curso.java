package programacursos;


import programacursos.Alumnos;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


abstract class Curso {
    protected String titulo;
    protected LocalDate finicio;
    protected LocalDate ffinalizacion;
    protected int numerodias;
    protected double precio;
    protected ArrayList<Alumnos> listamatriculados;
    protected ArrayList<Alumnos> listaaptos;
    protected int nalumnos;

    public Curso(String titulo, LocalDate finicio, LocalDate ffinalizacion, int numerodias, double precio) {
        this.titulo = titulo;
        this.finicio = finicio;
        this.ffinalizacion = ffinalizacion;
        this.numerodias = numerodias;
        this.precio = precio;
        listamatriculados = new ArrayList<Alumnos>();
        listaaptos = new ArrayList<Alumnos>();     
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDate getFinicio() {
        return finicio;
    }

    public LocalDate getFfinalizacion() {
        return ffinalizacion;
    }

    public int getNumerodias() {
        return numerodias;
    }

    public ArrayList<Alumnos> getListamatriculados() {
        return listamatriculados;
    }

    public ArrayList<Alumnos> getListaaptos() {
        return listaaptos;
    }

    public int getNalumnos() {
        return nalumnos;
    }
    
    public boolean haterminado(LocalDate dia){
        if(dia.isAfter(ffinalizacion)){
            return true;
        }
        return false;
    }
    public boolean alumnoapto(Alumnos al){
         return listaaptos.contains(al);
     }
    public boolean Matricular(Alumnos al){
         if(al.getCredito()<precio){
             return false;
         }
         return Matricularseguntipo(al);
     }
    abstract boolean Matricularseguntipo(Alumnos al) ;
    abstract boolean calificar(Alumnos al);
}
class CursoOnline extends Curso{
    private int nivel;
    private Map<Alumnos, Integer > mapa;
    private ArrayList<Curso> Curprevios;
    
    public CursoOnline(String titulo, LocalDate finicio, LocalDate ffinalizacion, int numerodias, double precio, int nivel, Curso ... cuprevios) {
        super(titulo, finicio, ffinalizacion, numerodias, precio);
        this.nivel = nivel;
        mapa = new HashMap<Alumnos ,Integer >();
        Curprevios = new ArrayList<Curso>();
        for (Curso cuprevio : cuprevios) {
            this.Curprevios.add(cuprevio);
        }
    }
    
    public int consultarnivelalumno(Alumnos al){
        int auxint = -1;
        if(!listamatriculados.contains(al)){
             return auxint;
        }
        if(mapa.containsKey(al)){
            auxint = mapa.get(al)+1;
        }else{
            auxint = 0;
        }
        return auxint;
    }
    
    public boolean superarnivel(Alumnos al){
        boolean auxboolean = false;
        int auxint = consultarnivelalumno(al);
        if(auxint!=-1){
            if(auxint!=0){
                mapa.remove(al);
            }
            mapa.put(al, auxint);
        }
        return auxboolean;
    }
    boolean Matricularseguntipo(Alumnos al) {
        boolean auxbool = true;
        
        // recorremos todos los cursos previos
        for (Curso Curprevio : Curprevios) {
            //validamos que se encuentre matriculado al curso
            if(al.GetListacurso().contains(Curprevio)){
                //validamos si paso el curso
                if(!Curprevio.alumnoapto(al)){
                    return false;
                }
            }else{
                return false;
            }
        }
        //validamos si ya se encuentra matriculado
        if(listamatriculados.contains(al)){return false;}
        al.DecrementarCredito(precio);
        al.AñadirCurso(this);
        listamatriculados.add(al);
        return auxbool;
    }

    boolean calificar(Alumnos al) {
        boolean auxbool = false;
        //verificamos que este matriculado
        if(!listamatriculados.contains(al)){
            return auxbool;
        }
        if(mapa.containsKey(al)){
            //verificamos que cumpla con el requisito para ser apto
            if(mapa.get(al)+1>(nivel-(nivel%2))/2){
                auxbool = true;
                listaaptos.add(al);
            }
        }
        
        return auxbool;
    }
}
class Cursopresencial extends Curso{
    private int cupo;
    private int numerominimodia;
    private Map<Alumnos, Integer[] > mapa;

    public Cursopresencial(String titulo, LocalDate finicio, LocalDate ffinalizacion, int numerodias, double precio, int cu, int nume) {
        super(titulo, finicio, ffinalizacion, numerodias, precio);
        cupo = cu;
        numerominimodia = nume;
        mapa = new HashMap<Alumnos ,Integer[] >();
    }
    
    
    public int plazalibres(){
        return cupo - listamatriculados.size();
    }
    
    boolean Matricularseguntipo(Alumnos al) {
        boolean auxboolean = false;
        
        // verificamos la condicion particula de curso presencial para matricularse
        if((plazalibres())>0){
            listamatriculados.add(al);
            al.DecrementarCredito(this.precio);
            al.AñadirCurso(this);
            auxboolean = true;
        }
        return auxboolean;
    }

    boolean calificar(Alumnos al) {
        boolean auxboolean = false;
        // verificamos que este matriculado
        if(listamatriculados.contains(al)){
            //verificamos si ha asistido asi sea una vez
            if(mapa.containsKey(al)){
                //verificamos que haya cumplido los requisito minimo
                 if( consultanumerodia(al) >= numerominimodia){
                     auxboolean = true;
                     listaaptos.add(al);
                 }
            }
        }
        //System.out.println("-------------- "+al.GetListacurso().get(0).listaaptos.toString()+" :"+al.getNombre()+" \n"+2);
        return auxboolean;
    }
    
    public boolean registrardia(int dia,Alumnos al){
        boolean auxboolean = false;
        Integer[] aux2 = new Integer[1];
        //verificamos que este dentro de los dias y que el alumno este matriculado
        if(dia < numerodias && listamatriculados.contains(al)){
            //verificamos si es el primer dia que se ingresa 
            if(mapa.containsKey(al)){
                aux2 = mapa.get(al);
                aux2 = Arrays.copyOf(aux2, aux2.length+1);
            }else{
                aux2[0] = dia;
                mapa.put(al, aux2);
            }
        }
        return auxboolean;
    }
    public int consultanumerodia(Alumnos al){
        int auxint=0;
        if(mapa.containsKey(al)){
            auxint = mapa.get(al).length;
        }
        return auxint;
    }
}