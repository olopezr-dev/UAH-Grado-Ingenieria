
package poo.PL2.Clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;


public class FacturaUtilidades {
    
    
    public static void generarFactura(Reserva reserva) {
        String nombreArchivo = "data/facturas/factura_" + reserva.getCodigoFactura() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(reserva.generarFactura());
            writer.newLine();
            writer.write("Dirección del evento: " + reserva.getEvento().getDireccion().toString());
            writer.newLine();
            writer.write("¡Gracias por su compra!");
        } catch (IOException e) {
            System.err.println("Error al generar factura: " + e.getMessage());
        }
    }
    
    public static String generarCodigoFactura(Reserva reserva) {
        return reserva.getCliente().getCorreo().hashCode() + 
               "_" + 
               System.currentTimeMillis();
    }
}
