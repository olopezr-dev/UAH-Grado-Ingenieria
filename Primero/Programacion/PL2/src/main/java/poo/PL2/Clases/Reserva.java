
package poo.PL2.Clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Reserva implements Serializable {
    private Cliente cliente;
    private Evento evento;
    private LocalDate fechaReserva;
    private LocalDateTime fechaEvento;
    private int numeroEntradas;
    private double precioFinal;
    private int descuentoAplicado;
    private String codigoFactura;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Reserva(Cliente cliente, Evento evento, LocalDateTime fechaEvento, 
                  int numeroEntradas, double precioFinal) {
        this.cliente = cliente;
        this.evento = evento;
        this.fechaReserva = LocalDate.now();
        this.fechaEvento = fechaEvento;
        this.numeroEntradas = numeroEntradas;
        this.precioFinal = precioFinal;
        this.codigoFactura = FacturaUtilidades.generarCodigoFactura(this);
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }
    
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDateTime fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public int getNumeroEntradas() {
        return numeroEntradas;
    }

    public void setNumeroEntradas(int numeroEntradas) {
        this.numeroEntradas = numeroEntradas;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(int descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public String generarFactura() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== FACTURA DE RESERVA =====\n");
        sb.append("Código: ").append(codigoFactura).append("\n");
        sb.append("Fecha de reserva: ").append(fechaReserva.format(DATE_FORMATTER)).append("\n");
        sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
        sb.append("Correo: ").append(cliente.getCorreo()).append("\n");
        sb.append("Teléfono: ").append(cliente.getTelefono()).append("\n");
        sb.append("\nEvento: ").append(evento.getTitulo()).append("\n");
        sb.append("Tipo: ").append(evento.getTipo()).append("\n");
        sb.append("Fecha del evento: ").append(fechaEvento.format(DATETIME_FORMATTER)).append("\n");
        sb.append("Entradas: ").append(numeroEntradas).append("\n");
        sb.append("Precio unitario: ").append(evento.getPrecio()).append("€\n");
        if (descuentoAplicado > 0) {
            sb.append("Descuento aplicado: ").append(descuentoAplicado).append("%\n");
        }
        sb.append("Precio final: ").append(String.format("%.2f", precioFinal)).append("€\n");
        return sb.toString();
    }
    
    

}
