
package poo.PL2.Clases;


public class CarritoManager {
    private static Carrito carritoActual;
    
    public static Carrito getCarrito(Cliente cliente) {
        if (carritoActual == null || 
            (carritoActual.getCliente() != null && 
             !carritoActual.getCliente().getCorreo().equals(cliente.getCorreo()))) {
            carritoActual = new Carrito(cliente);
        }
        return carritoActual;
    }
    
    public static void vaciarCarrito() {
        if (carritoActual != null) {
            carritoActual.vaciar();
        }
    }
    
    public static void resetCarrito() {
        carritoActual = null;
    }
}
