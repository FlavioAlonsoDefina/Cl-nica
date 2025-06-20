/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clinica;

import java.time.LocalDateTime;

/**
 *
 * @author DELL7050
 */
//Clase paciente con sus atributos
public class Paciente {
    private String nombre;
    private String dni;
    private String alergias;
    private LocalDateTime fechaRegistro;

    //Constructor de paciente
    public Paciente(String nombre, String dni, String alergias) {
        this.nombre = nombre;
        this.dni = dni;
        this.alergias = alergias;
        this.fechaRegistro = LocalDateTime.now();
    }

    //getters 
    public String getNombre() { 
        return nombre; 
    }
    public String getDni() { 
        return dni; 
    }
    public String getAlergias() { 
        return alergias; 
    }
    public LocalDateTime getFechaRegistro() { 
        return fechaRegistro; 
    }

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
