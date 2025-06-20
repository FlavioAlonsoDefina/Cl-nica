/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

/**
 *
 * @author DELL7050
 */
public class Validaciones {
    //Validacion de Dni (8digitos)
    public static boolean validarDNI(String dni) {
        return dni != null && dni.matches("\\d{8}");
    }
    // Validacion de diagnostico
    public static boolean validarDiagnostico(String diag) {
        return diag != null && !diag.trim().isEmpty();
    }
}
