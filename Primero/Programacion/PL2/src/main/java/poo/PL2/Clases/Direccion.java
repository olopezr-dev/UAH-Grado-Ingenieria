
package poo.PL2.Clases;

import java.io.Serializable;

public class Direccion implements Serializable{
    
    private String calle;
    private String numero;
    private String ciudad;
    private String codigoPostal;

    public Direccion(String calle, String numero, String ciudad, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }
    
    

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }


    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    @Override
    public String toString() {
        return "Calle: " + calle + " " + numero + "\n" +
               "Ciudad: " + ciudad + "\n" +
               "Código Postal: " + codigoPostal;
    }


}
