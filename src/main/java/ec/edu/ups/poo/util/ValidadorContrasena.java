package ec.edu.ups.poo.util;

/**
 * Clase utilitaria para validar la fortaleza de contraseñas
 * según reglas específicas de formato.
 */
public class ValidadorContrasena {

    /**
     * Valida si una contraseña cumple con los siguientes requisitos:
     * - No sea nula.
     * - Tenga al menos 6 caracteres.
     * - Contenga al menos una letra mayúscula.
     * - Contenga al menos una letra minúscula.
     * - Contenga al menos uno de los caracteres especiales: '@', '_', '-'.
     *
     * @param contrasena Cadena que representa la contraseña a validar.
     * @return true si la contraseña cumple los requisitos, false en caso contrario.
     */
    public static boolean esContrasenaValida(String contrasena) {
        if (contrasena == null || contrasena.length() < 6) return false;
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneEspecial = false;
        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) tieneMayuscula = true;
            else if (Character.isLowerCase(c)) tieneMinuscula = true;
            else if (c == '@' || c == '_' || c == '-') tieneEspecial = true;
        }
        return tieneMayuscula && tieneMinuscula && tieneEspecial;
    }
}