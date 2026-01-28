
package poo.PL2.Clases;


public class Sesion {
    private static Cliente clienteActual;
    private static DataBase baseDeDatos;

    public static void iniciarSesion(Cliente cliente, DataBase db) {
        clienteActual = cliente;
        baseDeDatos = db;
    }

    public static Cliente getClienteActual() {
        return clienteActual;
    }

    public static DataBase getBaseDeDatos() {
        return baseDeDatos;
    }
}
