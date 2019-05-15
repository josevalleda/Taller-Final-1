

package e2ejer4;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class E2ejer4 {

   
    public static void main(String[] args) {
        ArrayList<Turno> auxturnos = new ArrayList<Turno>();
        Turno auxturno;
        Agendabasica a1 = new Agendabasica("Enrique", "Turtorial");
        Agendabalanceada a2 = new Agendabalanceada("Enrique", "Revision de Examen");
        a1.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 12), "09:30 a 10:00");
        a1.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 12), "10:30 a 11:00");
        a1.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 13), "10:30 a 11:00");
        a2.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 12), "12:00 a 12:30");
        a2.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 12), "13:30 a 14:00");
        a2.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 13), "11:00 a 11:30");
        a2.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 13), "12:30 a 13:00");
        a2.AñadirTurno(LocalDate.of(2018, Month.DECEMBER, 13), "13:00 a 13:30");
        ArrayList<Agenda> Lista = new ArrayList<Agenda>();
        Lista.add(a1);
        Lista.add(a2);
        
        for (Agenda agenda : Lista) {
            System.out.println("------------Inicio Agenda-----------------------");
            System.out.println("Descripcion: "+agenda.descripcion);
            System.out.println("Numero de  turnos 13 dic: "+agenda.Consultadia(LocalDate.of(2018, Month.DECEMBER, 13)).size());
            agenda.reservar("Juan");
            agenda.reservar("Juan");
            auxturnos = agenda.MostrarTurnoDias(LocalDate.of(2018, Month.DECEMBER, 13));
            if(auxturnos.size() == 0){
                System.out.println("No hay turnos reservados ");
            }else{
                System.out.println(auxturnos.toString());
            }
            auxturno = agenda.cancelarreserva("Juan");
            if(auxturnos == null){
                System.out.println("No hay turnos reservados ");
            }else{
                System.out.println(auxturno.toString());
            }
        }
    }
    
}
