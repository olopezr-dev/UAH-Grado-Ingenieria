
package poo.PL2.Clases;

import java.io.Serializable;
import java.time.YearMonth;


public class TarjetaCredito implements Serializable {
    
    
    private String titular;
    private String digitos;  
    private YearMonth fechaCaducidad;

    public TarjetaCredito(String titular, String digitos, YearMonth fechaCaducidad) {
        this.titular = titular;
        this.digitos = digitos;
        this.fechaCaducidad = fechaCaducidad;
    }
    
    
    
    public YearMonth getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(YearMonth fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }


    public String getDigitos() {
        return digitos;
    }

    public void setDigitos(String digitos) {
        this.digitos = digitos;
    }

    
    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

}
