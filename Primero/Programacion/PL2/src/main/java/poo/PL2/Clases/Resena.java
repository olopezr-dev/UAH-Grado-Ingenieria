
package poo.PL2.Clases;


public class Resena {
    
    private int calificacion;
    private String comentario;
    private Cliente cliente;

    public Resena(int calificacion, String comentario, Cliente cliente) {
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.cliente = cliente;
    }
    
    public Resena(){  
    }
 
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

}
