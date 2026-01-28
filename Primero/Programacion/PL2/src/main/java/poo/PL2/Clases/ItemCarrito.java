
package poo.PL2.Clases;

import java.time.LocalDateTime;


public class ItemCarrito {
    
    private Evento evento;
    private LocalDateTime fechaEvento;
    private int entradas;

    public ItemCarrito(Evento evento, LocalDateTime fechaEvento, int entradas) {
        this.evento = evento;
        this.fechaEvento = fechaEvento;
        this.entradas = entradas;
    }
    
    
    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDateTime fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
    if (entradas <= 0) {
        throw new IllegalArgumentException("El número de entradas debe ser positivo");
    }
    this.entradas = entradas;
}

    
}
