
package e2ejer3;

import java.time.LocalDate;
import java.util.ArrayList;


public class E2ejer3 {

    
    public static void main(String[] args) throws CloneNotSupportedException {
       Licencia l1 = new LicenciaTemporal("juan@um.es", "https://api.um.es/disco/v1/", LocalDate.now().plusMonths(1));
       Licencia l2 = new LicenciaLimitada("pepe@um.es", "https://api.um.es/disco/v1/", 3);
       Licencia l3 = new Licenciadiaria("pepe@um.es", "https://api.um.es/disco/v1/", 4,1);
       ArrayList<Licencia> licencias = new ArrayList<Licencia>();
       ArrayList<Licencia> copias = new ArrayList<Licencia>();
       licencias.add(l1);
       licencias.add(l2);
       licencias.add(l3);
       System.out.println("------------------recorrido 1-----------------------------------");
       for (Licencia licencia : licencias) {
            System.out.println(licencia.toString());
       }
       l1.revocar();
       licencias.set(0,l1);
       System.out.println("------------------FIN recorrido 1-----------------------------------");
       System.out.println("------------------Recorrido 2 4 autorizaciones-----------------------------------");
       for (Licencia licencia : licencias) {
            System.out.println("Cuanto autorizaciones: \n");
            for (int i = 0; i < 4; i++) {
               String devolucion =licencia.ObtenerAutorizacion();
               if(devolucion== null){
                   devolucion = "No autorizado";
               }
               System.out.println(devolucion);
           }
           System.out.println("\n");
       }
       System.out.println("------------------FIN Recorrido 2 4 autorizaciones-----------------------------------");
       System.out.println("------------------recorrido 3-----------------------------------");
       for (Licencia licencia : licencias) {
            System.out.println(licencia.toString());
       }
       System.out.println("------------------FIN recorrido 3-----------------------------------");
        for (int i = 0; i < licencias.size(); i++) {
            
            switch(i){
                case(0):
                    copias.add((LicenciaTemporal)licencias.get(i).clone());
                    break;
                case(1):
                    copias.add((LicenciaLimitada)licencias.get(i).clone());
                    break;
                default:
                    copias.add((Licenciadiaria)licencias.get(i).clone());
                    break;   
            }
        }
        System.out.println("------------------recorrido 4-----------------------------------");
       for (Licencia licencia : copias) {
            System.out.println(licencia.toString());
       }
       System.out.println("------------------FIN recorrido 4-----------------------------------");
    }
    
}
