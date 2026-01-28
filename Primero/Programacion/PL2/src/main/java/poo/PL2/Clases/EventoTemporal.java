
package poo.PL2.Clases;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventoTemporal {   
    
    private String titulo;
    private String tipo;
    private ArrayList<LocalDateTime> fechas;
    private Direccion direccion;
    private double precio;
    private String rutaPortada;
    
    
    public Evento crearEvento() {
        return new Evento(
                this.titulo,
                this.tipo,
                this.fechas,
                this.direccion,
                this.precio,
                this.rutaPortada);
        }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<LocalDateTime> getFechas() {
        return fechas;
    }

    public void setFechas(ArrayList<LocalDateTime> fechas) {
        this.fechas = fechas;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }
    
    
}
