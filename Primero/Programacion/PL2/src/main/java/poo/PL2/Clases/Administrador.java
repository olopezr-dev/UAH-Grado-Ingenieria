
package poo.PL2.Clases;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Administrador extends Usuario {

    private static final String ADMIN_EMAIL = "admin@javaevents.com";
    private static final String ADMIN_PASSWORD = "admin";
    private DataBase dataBase;

    public Administrador() {
        super(ADMIN_EMAIL, ADMIN_PASSWORD);
        this.dataBase = DataBase.getInstance();
    }
    
    public static boolean esAdmin(String correo, String contrasena) {
        return ADMIN_EMAIL.equals(correo) && ADMIN_PASSWORD.equals(contrasena);
    }

    // MÉTODOS EXCLUSIVOS DE ADMINISTRADOR
    //Gestion de eventos
        
    public void registrarEvento(String titulo, String tipo, ArrayList<LocalDateTime> fechas, String calle, String numero, String ciudad, String codigoPostal, 
                        double precio, String rutaPortada){
        
        Direccion direccion = new Direccion(calle,numero,ciudad,codigoPostal);
        Evento evento = new Evento(titulo, tipo, fechas, direccion, precio, rutaPortada);
              
        dataBase.guardarEvento(evento);
        
    }
}
