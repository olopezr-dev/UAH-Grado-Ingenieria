package poo.PL2.Clases;

import java.awt.Font;
import javax.swing.*;

public class SesionErrorHandler {
    
    // DECLARACIÓN DE LOS ERRORES
    public enum ErrorTipo {
        // Generales
        CORREO_VACIO,
        USUARIO_VACIO,
        CONTRASENA_VACIA,
        CORREO_NO_VALIDO,
        PRECIO_NO_VALIDO,
        FECHA_NO_VALIDA,
        
        
        // Crear una cuenta
        USUARIO_EXISTENTE,
        USUARIO_NO_VALIDO,
        CONTRASENA_NO_VALIDA,
        CONTRASENA_NO_SEGURA,
        CONTRASENA_MAL_REESCRITA,
        CAMPO_OBLIGATORIO_VACIO,
        TELEFONO_NO_VALIDO,
        TELEFONO_VACIO,
        CODIGO_POSTAL_NO_VALIDO,
        NUMERO_NO_VALIDO,
        DIGITOS_NO_VALIDOS,
        FECHA_CADUCIDAD_NO_VALIDA,
        
        // Iniciar sesión
        CREDENCIALES_NO_VALIDAS;
    
    
    }
    
    
    // ABRIMOS UNA VENTANA DIFERENTE DEPENDIENDO DEL ERROR
    public static void mostrarError(ErrorTipo tipo) {
        switch(tipo) {
            // General
            case CORREO_VACIO:
                mostrarVentana("El campo de correo está vacío");
                break;
            case USUARIO_VACIO:
                mostrarVentana("El campo de usuario está vacío.");
                break;
            case CONTRASENA_VACIA:
                mostrarVentana("El campo de contraseña está vacío.");
                break;
            case CORREO_NO_VALIDO:
                mostrarVentana("El correo no posee una estructura valida");
                break;
            case PRECIO_NO_VALIDO:
                mostrarVentana("El numero introducido como precio no es valido");
                break; 
            case FECHA_NO_VALIDA:
                mostrarVentana("La fecha introducida no es válida");
                break;
            // Crear una cuenta
            case USUARIO_EXISTENTE:
                mostrarVentana("El usuario escrito ya existe.");
                break;
            case USUARIO_NO_VALIDO:
                mostrarVentana("El usuario escrito no es válido.");
                break;
            case CONTRASENA_NO_VALIDA:
                mostrarVentana("La contraseña utilizada no es válida.");
                break;
            case CONTRASENA_MAL_REESCRITA:
                mostrarVentana("La contraseña ha sido mal reescrita.");
                break;
            case CAMPO_OBLIGATORIO_VACIO:
                mostrarVentana("Campo obligatorio vacío");
                break;
            case TELEFONO_NO_VALIDO:
                mostrarVentana("El telefono introducido no es válido");
                break;
            case TELEFONO_VACIO:
                mostrarVentana("El campo telefono está vacío");
                break;
            case CONTRASENA_NO_SEGURA:
                mostrarVentana("La contraseña introducida no es segura");
                break;
            case CODIGO_POSTAL_NO_VALIDO:
                mostrarVentana("El código postal introducido no es válido");
                break;
            case NUMERO_NO_VALIDO:
                mostrarVentana("El campo numero introducido no es válido");
                break;
            case DIGITOS_NO_VALIDOS:
                mostrarVentana("Los digitos introducidos no son válidos");
                break; 
            case FECHA_CADUCIDAD_NO_VALIDA:
                mostrarVentana("La fecha de caducidad introducida no es válida");
                break;
            // Iniciar sesión
            case CREDENCIALES_NO_VALIDAS:
                mostrarVentana("Las credenciales no son validas.");
                break;
        }
    }

    
    public static void mostrarVentana(String mensaje) {
        // VOID ENCARGADO DE CREAR LA VENTANA
        JFrame ventana = new JFrame("Error de Inicio de Sesión");
        JLabel label = new JLabel(mensaje, SwingConstants.CENTER);
        
        // Cambiar el tipo de letra y ponerla en "negrita"
        label.setFont(new Font("Arial", Font.BOLD, 18));
        
        ventana.add(label);
        ventana.setSize(400, 250);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);
    }

}


