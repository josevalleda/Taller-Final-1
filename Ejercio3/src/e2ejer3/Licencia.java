

package e2ejer3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

abstract class Licencia implements Cloneable {
    protected  String email;
    protected  LocalDate fechacreacion;
    protected  String codigo;
    protected  ArrayList<String> transacciones;
    protected  boolean revocada;
    protected  String cadena;  
    Licencia(String em,String cade){
        email = em;
        fechacreacion = LocalDate.now();
        codigo = UUID.randomUUID().toString();
        transacciones = new ArrayList<String>();
        cadena = cade;
        revocada = false;
 //       caducada = false;
    }   
    abstract void revocar();
    abstract String ObtenerAutorizacion();
    @Override
    public Object clone() throws
                   CloneNotSupportedException 
    { 
        return super.clone(); 
    } 
}

class LicenciaTemporal extends Licencia{
            
    protected  LocalDate fcaducidad;
    protected  boolean caducada;

    public LicenciaTemporal(String em, String cade, LocalDate fcadu) {
        super(em, cade);
        if(fcadu == null){
            fcaducidad = fechacreacion.plusMonths(3);
        }else{
            fcaducidad = fcadu;
        }
        caducada = false;
    }
    void revocar() {
        revocada = true;
    }

    String ObtenerAutorizacion() {
        if(caducada || revocada){
           return null; 
        }
        String transaccion = "nueva"+transacciones.size();
        transacciones.add(transaccion);
        return transaccion;
    }
 
}
class LicenciaLimitada extends Licencia{

    private int numerotransacciones;
    public LicenciaLimitada(String em, String cade , int i) {
        super(em, cade);
        numerotransacciones = i;
    }

    void revocar() {
        revocada = true;
    }

    String ObtenerAutorizacion() {
        verificarrevoca();
        if(revocada){
           return null; 
        }
        String transaccion = "nueva"+transacciones.size();
        transacciones.add(transaccion);
        return transaccion;
    }
    public int Transaccionesrestante(){
        return numerotransacciones - transacciones.size();
    }
    void verificarrevoca(){
        if(Transaccionesrestante() < 1){
           revocar();
        }    
    }
}
class Licenciadiaria extends Licencia{
    
    private ArrayList<Mapa> mapas;
    private int Limitediario;
    private int numerotransacciones;
    
    public Licenciadiaria(String em, String cade,int lim, int limite) {
        super(em, cade);
        mapas = new ArrayList<Mapa>();
        numerotransacciones = lim;
        Limitediario = limite;
    }

    void revocar() {
        
    }

    public String ObtenerAutorizacion() {
        LocalDate fecha = LocalDate.now();
        boolean bandera = false;
        for ( Mapa mapa: mapas) {
            if(mapa.GetFecha().toString().equals(fecha.toString())){
                if(mapa.Gettransacciones()< Limitediario-1){
                   mapa.aumentarTransaccion();
                   bandera = true;
                    break;
                }else{return null;}
            }
        }
        if(!bandera){
           mapas.add(new Mapa(fecha));
        }
        String transaccion = "nueva"+transacciones.size();
        transacciones.add(transaccion);
        return transaccion;
    }

}

