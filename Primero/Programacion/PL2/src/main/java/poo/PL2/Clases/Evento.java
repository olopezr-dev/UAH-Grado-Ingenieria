
package poo.PL2.Clases;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Evento implements Serializable {
    
    private String titulo;
    private String tipo;
    private ArrayList<LocalDateTime> fechas;
    private Direccion direccion;
    private double calificacion;
    private double precio;
    private String rutaPortada;
    private ArrayList<Resena> resenas;
    
    
    public Evento(String titulo, String tipo, ArrayList<LocalDateTime> fechas, Direccion direccion, double calificacion, 
                        double precio, String rutaPortada, ArrayList<Resena> resenas) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.fechas = fechas;
        this.direccion = direccion;
        this.calificacion = calificacion;
        this.precio = precio;
        this.rutaPortada = rutaPortada;
        this.resenas = resenas;
    }
    
    public Evento(String titulo, String tipo, ArrayList<LocalDateTime> fechas, Direccion direccion,
                        double precio, String rutaPortada) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.fechas = fechas;
        this.direccion = direccion;
        this.calificacion = 0.0;
        this.precio = precio;
        this.rutaPortada = rutaPortada;
        this.resenas = new ArrayList<Resena>();
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }
    
    
    public ArrayList<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(ArrayList<Resena> resenas) {
        this.resenas = resenas;
    }
   
    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
  
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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

     public ImageIcon getPortada() {
        if (rutaPortada == null || rutaPortada.isEmpty()) {
            return null;
        }
        return new ImageIcon(rutaPortada); // Carga desde ruta del sistema de archivos
    }
     
    /**
     * Carga y devuelve la imagen de portada como ImageIcon
     */
    public ImageIcon cargarImagenPortada() {
        if (this.rutaPortada == null) {
            return null;
        }

        try {
            String rutaAbsoluta = this.obtenerRutaAbsolutaPortada();
            File archivoImagen = new File(rutaAbsoluta);

            if (archivoImagen.exists()) {
                Image imagen = ImageIO.read(archivoImagen);
                return new ImageIcon(imagen);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar imagen: " + e.getMessage());
        }
        return null;
    }

    /**
     * Versión con redimensionamiento
     */
    public ImageIcon cargarImagenPortada(int ancho, int alto) {
        ImageIcon icono = this.cargarImagenPortada();
        if (icono != null) {
            return new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));
        }
        return null;
    }
    
    public String obtenerRutaAbsolutaPortada() {
        if (this.rutaPortada == null || this.rutaPortada.isEmpty()) {
            return null;
        }
        return System.getProperty("user.dir") + File.separator + this.rutaPortada.replace("/", File.separator);
    }
}
