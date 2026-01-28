
package poo.PL2.Clases;

public class RegistroTemporal {
    
    private String correo;
    private String contrasena;
    private String nombre;
    private String telefono;
    private Direccion direccion;
    private TarjetaCredito tarjetaCredito;
    private boolean vip;

    public Cliente crearCliente() {
        return new Cliente(
                this.nombre,
                this.telefono,
                this.direccion,
                this.tarjetaCredito,
                this.vip,
                this.correo,
                this.contrasena);
        }

    
    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }


    public TarjetaCredito getTarjetaCredito() {
        return tarjetaCredito;
    }

    public void setTarjetaCredito(TarjetaCredito tarjetaCredito) {
        this.tarjetaCredito = tarjetaCredito;
    }


    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
