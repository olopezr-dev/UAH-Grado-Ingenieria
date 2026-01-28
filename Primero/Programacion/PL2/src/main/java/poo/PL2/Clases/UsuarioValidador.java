
package poo.PL2.Clases;

import java.util.regex.Pattern;



public class UsuarioValidador {
    
    public static boolean EsNombreValido(String nombre){
        return nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñüÜ\\s]+$");
    }
    
    public static boolean EsTelefonoValido(String telefono){
        return telefono.matches("^\\d{9}$");
    }
    
    public static boolean EsDireccionValido(String direccion){
        return ValidadorUtilidades.noEsVacio(direccion);
    }
    
    public static boolean EsTarjetaValida(String numeroTarjeta) {
        // Paso 1: Limpieza (elimina espacios y guiones)
        String numeroLimpio = numeroTarjeta.replaceAll("[\\s-]", "");
        
        // Paso 2: Validar longitud y dígitos (13-19 dígitos)
        if (!Pattern.matches("^\\d{13,19}$", numeroLimpio)) {
            return false;
        }
        
        // Paso 3: Validar algoritmo de Luhn
        return validarAlgoritmoLuhn(numeroLimpio);
    }

    private static boolean validarAlgoritmoLuhn(String numero) {
        int suma = 0;
        boolean alternar = false;
        
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            
            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito = (digito % 10) + 1; // Suma los dígitos si el resultado es >9 (ej: 16 → 1+6=7)
                }
            }
            
            suma += digito;
            alternar = !alternar;
        }
        
        return (suma % 10 == 0);
    }

    
    public static boolean EsFechaCaducidadValida(String fechaCaducidad){
        return fechaCaducidad != null && fechaCaducidad.matches("^(0[1-9]|1[0-2])/\\d{2}$");
    }    
}
