/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author DELL7050
 */
public class BaseDatos {
    public static List<Paciente> pacientes = new ArrayList<>();
    public static List<Consulta> consultas = new ArrayList<>();

    public static Paciente buscarPorDNI(String dni) {
        for (Paciente p : pacientes) {
            if (p.getDni().equals(dni)) return p;
        }
        return null;
    }

    public static List<Paciente> buscarPorNombre(String nombre) {
        List<Paciente> resultado = new ArrayList<>();
        for (Paciente p : pacientes) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}
