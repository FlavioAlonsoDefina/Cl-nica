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
public class Consulta {
    private Paciente paciente;
    private String diagnostico;
    private String doctor;
    private LocalDateTime fechaHora;
    private Integer calificacion = null;

    public Consulta(Paciente paciente, String diagnostico, String doctor) {
        this.paciente = paciente;
        this.diagnostico = diagnostico;
        this.doctor = doctor;
        this.fechaHora = LocalDateTime.now();
    }
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    public Integer getCalificacion() {
        return calificacion;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getDoctor() {
        return doctor;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String resumen() {
        String base = "Consulta de " + paciente.getNombre() + " con el Dr. " + doctor +
                "\nDiagnóstico: " + diagnostico +
                "\nFecha: " + fechaHora;
        
        if (calificacion != null) {           
            base += "\nCalificación del paciente: " + calificacion + "/5";
            } else {
                    base += "\nSin calificación aún.";
                }
        return base;
    }
}
