
package poo.PL2.Clases;

import java.util.ArrayList;
import java.util.List;


public class ProcesadorReservas {
    
    public static List<Reserva> procesar(Carrito carrito) {
        List<Reserva> reservas = new ArrayList<>();
        Cliente cliente = carrito.getCliente();

        if (carrito.getItems().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        for (ItemCarrito item : carrito.getItems()) {
            Reserva reserva = crearReserva(cliente, item);
            reservas.add(reserva);
            
            // Generar factura y guardar reserva
            FacturaUtilidades.generarFactura(reserva);
            DataBase.getInstance().addReserva(reserva);
        }

        carrito.vaciar();
        return reservas;
    }
    
    private static Reserva crearReserva(Cliente cliente, ItemCarrito item) {
        double precioBase = item.getEvento().getPrecio();
        int entradas = item.getEntradas();
        double total = precioBase * entradas;
        int descuento = 0;

        if (cliente.isVip()) {
            descuento = 10;
            total *= 0.9;
        }

        Reserva reserva = new Reserva(cliente, item.getEvento(), 
                                    item.getFechaEvento(), entradas, total);
        reserva.setDescuentoAplicado(descuento);
        
        return reserva;
    }
}
