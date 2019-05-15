
package programacursos;

import java.time.LocalDate;

public class ProgramaCursos {

 
    public static void main(String[] args) {
       Alumnos a1 = new Alumnos("Pepe", "34678904", 0);
       Alumnos a2 = new Alumnos("Andrea", "17679456", 125);
       Cursopresencial c2 = new Cursopresencial("diseño de bases de datos", LocalDate.of(2014,5,5), LocalDate.of(2014, 5, 6), 1, 50, 20, 1);
       CursoOnline c1 = new CursoOnline("administracion de bases de datos", LocalDate.of(2014,5,12), LocalDate.of(2014, 5, 16), 5, 25, 4, c2);
       c2.Matricular(a2);
       c2.Matricular(a1);
       c2.registrardia(1, a1);
       c2.calificar(a2);
       c2.calificar(a1);
       c1.Matricular(a2);
       c1.Matricular(a1);
       System.out.println("Matriculados en el curso  "+c1.getTitulo()+" : \n"+c1.listamatriculados.toString());
       c1.superarnivel(a1);
       c1.calificar(a1);
       System.out.println("Lista de aptos "+c1.getTitulo()+" : \n"+c1.getListaaptos().toString());
       
    }
    
}
