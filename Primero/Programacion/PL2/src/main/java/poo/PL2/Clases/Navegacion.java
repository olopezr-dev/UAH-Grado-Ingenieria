
package poo.PL2.Clases;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import poo.PL2.Interface.InicioSesion;
import poo.PL2.Interface.NuevaCuentaTarjetaCredito;


public class Navegacion {
    
    // Abre la ventana destino y cierra la ventana actual.
    public static void cambiarVentana(javax.swing.JFrame actual, javax.swing.JFrame destino) {
        destino.setVisible(true);
        actual.dispose();
        
        if (actual instanceof InicioSesion || actual instanceof NuevaCuentaTarjetaCredito) {
        CarritoManager.resetCarrito();
        }
    }

    public static void ponerLogo(JLabel jLabel1, JLabel jLabel2) {
    // Usar Navegacion.class para obtener el recurso en contexto estático
    ImageIcon icono = new ImageIcon(Navegacion.class.getResource("/imagenes/javaEventsLogo.png"));
    
    if (icono.getImage() == null) {
        System.err.println("¡Error: No se pudo cargar la imagen!");
        return;
    }
    
    Image imagenEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
    jLabel1.setIcon(new ImageIcon(imagenEscalada));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto dentro del JLabel
    jLabel2.setIcon(new ImageIcon(imagenEscalada));
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto dentro del JLabel
    }
}
