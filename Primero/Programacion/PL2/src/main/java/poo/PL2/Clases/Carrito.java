
package poo.PL2.Clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Carrito implements Serializable {
    private Cliente cliente;
    private List<ItemCarrito> items = new ArrayList<>();
    private String clienteEmail;
    

    public Carrito(Cliente cliente) {
        this.cliente = cliente;
        this.clienteEmail = cliente.getCorreo();
    }

    public void agregarItem(Evento evento, LocalDateTime fechaEvento, int numEntradas) {
        items.add(new ItemCarrito(evento, fechaEvento, numEntradas));
    }

    public void eliminarItem(ItemCarrito item) {
        items.remove(item);
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public void vaciar() {
        items.clear();
    }

    public Cliente getCliente() {
        if (cliente == null && clienteEmail != null) {
            cliente = DataBase.getInstance().getCliente(clienteEmail);
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public double calcularTotal() {
        return items.stream()
                   .mapToDouble(item -> {
                       double precio = item.getEvento().getPrecio() * item.getEntradas();
                       return cliente.isVip() ? precio * 0.9 : precio;
                   })
                   .sum();
    }
    
    
    
}
