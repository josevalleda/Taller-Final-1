
package e2ejer4;

import java.time.LocalDate;

public class Turno {
  
    private LocalDate fecha;
    private String franja;
    
    Turno(LocalDate f, String fr){
        fecha = f;
        franja = fr;
    }

    public LocalDate Getfecha(){
        return fecha;
    }
    public String Getfranja(){
        return franja;
    }
    
    public boolean equals(Turno t2){

        if(this.Getfecha().equals(t2.Getfecha()) && this.Getfranja().equals(t2.Getfranja())){return true;}
        return false;
    }
}
