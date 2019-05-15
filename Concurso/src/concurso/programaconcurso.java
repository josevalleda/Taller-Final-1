/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurso;

import java.util.ArrayList;

/**
 *
 * @author aechavarria
 */
public class programaconcurso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Concurso> Lista = new ArrayList<Concurso>();
        //inicializamos los concursos
        ConcursoLimitado c1 = new ConcursoLimitado("Sesion 1", 2, 5, 1);
        c1.AñadirSolucion("23","15");
        Concursocompetitivo c2 = new Concursocompetitivo("Sesion 2", 3, 15);
        c2.AñadirSolucion("AACTG","null","[13,98]");
        ConcuersoSecuencial c3 =new ConcuersoSecuencial("Sesion 3", 3, 30);
        c3.AñadirSolucion("aa","[0,3]","AAA");
        //agregamos a la lista
        Lista.add(c1);
        Lista.add(c2);
        Lista.add(c3);
        for (Concurso concurso : Lista) {
            concurso.iniciar();
            concurso.añadirequipo("Equipo 1","Equipo 2","Equipo 3");
            for (int i = 0; i < concurso.getNumeroproblema(); i++) {
                for (int j = 0; j < concurso.listaequipo.size(); j++) {
                    concurso.Registrarenvio(concurso.listaequipo.get(j), i, "aa");
                }
            }
        }
        System.out.println("Estado del Concurso:");
        c3 = (ConcuersoSecuencial) Lista.get(2);
        for (int j = 0; j < c3.listaequipo.size(); j++) {
            System.out.println("\t --"+c3.listaequipo.get(j)+" -> Problema Actual "+c3.ProblemaActual(c3.listaequipo.get(j)));
        }
    }
    
}
