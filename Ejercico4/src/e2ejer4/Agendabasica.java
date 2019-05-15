
package e2ejer4;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


abstract class Agenda {
    
    protected String propietario;
    protected String descripcion;
    protected ArrayList<Turno> turnos;
    protected Map<Turno, String> map;
            
    Agenda(String p, String d){
        propietario = p;
        descripcion = d;
        turnos = new ArrayList<Turno>();
        map = new HashMap<Turno, String>();
    }
    abstract boolean AñadirTurno(LocalDate dia, String fran);
    
    void AjustarDias(int i){
        for (Turno turno : turnos) {
            if(i<0){
                turno.Getfecha().plusDays(i);
            }else{
                turno.Getfecha().minusDays(i);
            }
        }
    }
    ArrayList<Turno> Consultadia(LocalDate dia){
        ArrayList<Turno> aux = new ArrayList<Turno>();
       for (Turno turno : turnos) {
            if(turno.Getfecha().equals(dia)){
                aux.add(turno);
            }
        }
       return aux;
    }
    String ConsultausuarioTurno(Turno t){
       String aux = map.get(t);
       return aux;
    }
    boolean ConsultaEstadoTurno(Turno t){
       return map.containsKey(t);
    }
    boolean cancelarReserva(Turno t,String u){
      if(map.get(t).equals(u)){
          map.remove(t);
          return true;
      }  
      return false;
    }
    abstract Turno reservar(String u);
    // retorna los turnos reservado de un dia especificado en el parametro
    ArrayList<Turno> MostrarTurnoDias(LocalDate dia){
        ArrayList<Turno> aux = new ArrayList<Turno>();
        for (Turno turno : turnos) {
            if(map.containsKey(turno) && dia.equals(turno.Getfecha())){
                aux.add(turno);
            }
        }
        return aux;
    }
    Turno cancelarreserva(String u){
        Turno aux = null;
        if(map.containsValue(u)){
            for (Map.Entry<Turno, String> entry : map.entrySet()) {
                String value = entry.getValue();
                if(value.equals(u)){
                   aux = entry.getKey();
                }
            };
            map.remove(aux,u);
        }
        return aux;
    }
}

class Agendabasica extends Agenda{
    
    private ArrayList<String> ListaUsuario;

    public Agendabasica(String p, String d) {
        super(p, d);
        ListaUsuario = new ArrayList<String>();
    }

    Turno reservar( String u) {
        for (String usuario : ListaUsuario) {
           if(u.equals(usuario)){
               return null;
           }
        }
        for (Turno turno : turnos) {
            if(!map.containsKey(turno)){
                map.put(turno, u);
                ListaUsuario.add(u);
                return turno;
            }
        }
        return null;
    }

    boolean AñadirTurno(LocalDate dia, String fran) {
        Turno aux = new Turno(dia, fran);
        for (Turno turno : turnos) {
            if(turno.equals(aux)){
                return false;
            }
        }
        turnos.add(aux);
        return true;
    }

}
class Agendabalanceada extends Agenda{

    private Map<LocalDate, Integer> mapcontador;
    public Agendabalanceada(String p, String d) {
        super(p, d);
        mapcontador = new HashMap<LocalDate, Integer>(); 
    }

    Turno reservar( String u) {
        Integer auxint = 0;
        for (Turno turno : turnos) {
            if(!map.containsKey(turno)){
                map.put(turno, u);
                if(mapcontador.containsKey(turno.Getfecha())){
                    auxint = mapcontador.get(turno.Getfecha()) - 1;
                    mapcontador.remove(turno.Getfecha());
                    mapcontador.put(turno.Getfecha(), auxint); 
                }
                return turno;
            }
        }
        return null;
    }

    boolean AñadirTurno(LocalDate dia, String fran) {
        boolean bandera = false;
        Integer auxint = 0;
        Turno aux = new Turno(dia, fran);
        for (Turno turno : turnos) {
            if(turno.equals(aux)){
                bandera = true;
            }
        }
        if(bandera){return false;}
        if(mapcontador.containsKey(dia)){
            auxint = mapcontador.get(dia) + 1;
            mapcontador.remove(dia);
        }
        mapcontador.put(dia, auxint);
        turnos.add(aux);
        return true;
    }

}
