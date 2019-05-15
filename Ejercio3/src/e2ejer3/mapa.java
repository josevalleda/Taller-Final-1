
package e2ejer3;

import java.time.LocalDate;

public class Mapa {
    
    private LocalDate fecha;
    private int transacciones;
    
    Mapa(LocalDate f){
        fecha = f;
        transacciones = 0;
    }
    
    public void aumentarTransaccion (){
        transacciones += 1;
    }
    public LocalDate GetFecha(){
        return  fecha;
    }
    public int Gettransacciones(){
        return transacciones;
    }
}
