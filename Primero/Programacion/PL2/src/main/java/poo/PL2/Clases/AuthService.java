
package poo.PL2.Clases;

import java.io.IOException;
import java.util.Map;

public class AuthService {
    
    private DataBase dataBase;

    public AuthService() {
        this.dataBase = DataBase.getInstance();
    }

    // Registro: Verifica correo único y añade cliente
    public boolean registrarCliente(Cliente cliente) throws IOException {
        Map<String, Cliente> clientes = dataBase.getClientes();
        
        // Validación de correo único (O(1) gracias al HashMap)
        if (clientes.containsKey(cliente.getCorreo())) {
            return false;  // Correo ya registrado
        }

        // Guardar cliente
        dataBase.getClientes().put(cliente.getCorreo(), cliente);
        return true;
    }

    // Login: Busca usuario( tanto cliente como admin ) por correo y contraseña
    public Usuario iniciarSesion(String correo, String contrasena) {
        // 1. Verificar si es el admin
        if (Administrador.esAdmin(correo, contrasena)) {
            return new Administrador(); // Devuelve instancia de Administrador
        }

        // 2. Buscar cliente normal
        Cliente cliente = dataBase.buscarClientePorCorreo(correo);
        if (cliente != null && cliente.getContrasena().equals(contrasena)) {
            return cliente;
        }

        return null; // Credenciales inválidas
    }
    
    public void actualizarCliente(String correoOriginal, Cliente nuevosDatos) {
        // Validación crítica 1: El cliente debe existir
        if (!dataBase.existeCliente(correoOriginal)) {
            throw new IllegalStateException("[ERROR] Cliente no registrado. Posible ataque o bug.");
        }

        // Validación crítica 2: El correo no fue modificado (inmutable)
        if (!correoOriginal.equals(nuevosDatos.getCorreo())) {
            throw new SecurityException("[ERROR] Intento ilegal de cambiar correo.");
        }

        dataBase.actualizarCliente(nuevosDatos);
    }
}
