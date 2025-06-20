/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clinica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL7050
 */
public class MedicoUI extends javax.swing.JFrame {

    /**
     * Creates new form MedicoUI
     */
    public MedicoUI() {
        initComponents();
        setTitle("Panel Médico");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnRegistrar = new JButton("Registrar Paciente");
        btnRegistrar.setBounds(100, 50, 200, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(e -> registrarPaciente());
        
        JButton btnConsulta = new JButton("Registrar Consulta");
        btnConsulta.setBounds(100, 100, 200, 30);
        add(btnConsulta);

        btnConsulta.addActionListener(e -> registrarConsulta());
        
        JButton btnHistorial = new JButton("Ver Historial Paciente");
        btnHistorial.setBounds(100, 150, 200, 30);
        add(btnHistorial);

        btnHistorial.addActionListener(e -> mostrarHistorial());
        
        JButton btnEditarConsulta = new JButton("Editar Consulta");
        btnEditarConsulta.setBounds(100, 200, 200, 30);
        add(btnEditarConsulta);

        btnEditarConsulta.addActionListener(e -> editarConsulta());

        JButton btnEliminarConsulta = new JButton("Eliminar Consulta");
        btnEliminarConsulta.setBounds(100, 250, 200, 30);
        add(btnEliminarConsulta);

        btnEliminarConsulta.addActionListener(e -> eliminarConsulta());
        
        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setBounds(100, 300, 200, 30);
        add(btnVolver);

        btnVolver.addActionListener(e -> {
           dispose(); // Cierra esta ventana
           new MenuPrincipalUI(); // Abre el menú principal
        });

        setVisible(true);
    }
    private void registrarPaciente() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre:");
        String dni = JOptionPane.showInputDialog(this, "DNI (8 dígitos):");
        if (!Validaciones.validarDNI(dni)) {
            JOptionPane.showMessageDialog(this, "DNI inválido.");
            return;
        }
        String alergias = JOptionPane.showInputDialog(this, "Alergias:");
        Paciente nuevo = new Paciente(nombre, dni, alergias);
        BaseDatos.pacientes.add(nuevo);
        JOptionPane.showMessageDialog(this, "Paciente registrado correctamente.");
    }
    private void registrarConsulta() {
        String dni = JOptionPane.showInputDialog(this, "DNI del paciente:");
        Paciente paciente = BaseDatos.buscarPorDNI(dni);

        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente no registrado.");
            return;
        }

        String doctor = JOptionPane.showInputDialog(this, "Nombre del médico:");
        String diagnostico = JOptionPane.showInputDialog(this, "Diagnóstico:");

        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se puede registrar una consulta sin diagnóstico.");
            return;
        }

        Consulta consulta = new Consulta(paciente, diagnostico, doctor);
        BaseDatos.consultas.add(consulta);
        JOptionPane.showMessageDialog(this, "Consulta registrada:\n" + consulta.resumen());
    }
    private void mostrarHistorial() {
        String dni = JOptionPane.showInputDialog(this, "DNI del paciente:");
        Paciente paciente = BaseDatos.buscarPorDNI(dni);

        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente no registrado.");
            return;
        }

        StringBuilder historial = new StringBuilder("Historial de " + paciente.getNombre() + ":\n\n");

        boolean encontrado = false;
        for (Consulta c : BaseDatos.consultas) {
            if (c.getPaciente().getDni().equals(dni)) {
                historial.append(c.resumen()).append("\n\n");
                encontrado = true;
            }
        }

        if (!encontrado) {
            historial.append("No tiene consultas registradas.");
        }

        JOptionPane.showMessageDialog(this, historial.toString());
    }
    private void editarConsulta() {
        String dni = JOptionPane.showInputDialog(this, "DNI del paciente:");
        Paciente paciente = BaseDatos.buscarPorDNI(dni);
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            return;
        }

        List<Consulta> consultasPaciente = new ArrayList<>();
        for (Consulta c : BaseDatos.consultas) {
            if (c.getPaciente().getDni().equals(dni)) {
                consultasPaciente.add(c);
            }
        }

        if (consultasPaciente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay consultas para editar.");
            return;
        }

        String[] opciones = new String[consultasPaciente.size()];
        for (int i = 0; i < opciones.length; i++) {
            Consulta c = consultasPaciente.get(i);
            opciones[i] = "Consulta del " + c.getFechaHora().toLocalDate() + " con Dr. " + c.getDoctor();
        }

        String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona la consulta a editar:",
            "Editar Consulta", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == null) return;

        int index = Arrays.asList(opciones).indexOf(seleccion);
        Consulta consultaSeleccionada = consultasPaciente.get(index);

        String nuevoDiag = JOptionPane.showInputDialog(this, "Nuevo diagnóstico:");
        if (nuevoDiag == null || nuevoDiag.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se puede dejar vacío el diagnóstico.");
            return;
        }

        consultaSeleccionada.setCalificacion(null); 
        consultaSeleccionada.setDiagnostico(nuevoDiag);
        Consulta nueva = new Consulta(paciente, nuevoDiag, consultaSeleccionada.getDoctor());
        BaseDatos.consultas.set(BaseDatos.consultas.indexOf(consultaSeleccionada), nueva);

        JOptionPane.showMessageDialog(this, "Consulta actualizada correctamente.");
    }
    private void eliminarConsulta() {
        String dni = JOptionPane.showInputDialog(this, "DNI del paciente:");
        Paciente paciente = BaseDatos.buscarPorDNI(dni);
        if (paciente == null) {
            JOptionPane.showMessageDialog(this, "Paciente no encontrado.");
            return;
        }

        List<Consulta> consultasPaciente = new ArrayList<>();
            for (Consulta c : BaseDatos.consultas) {
                if (c.getPaciente().getDni().equals(dni)) {
                consultasPaciente.add(c);
            }
        }

        if (consultasPaciente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay consultas para eliminar.");
            return;
        }

        String[] opciones = new String[consultasPaciente.size()];
        for (int i = 0; i < opciones.length; i++) {
            Consulta c = consultasPaciente.get(i);
            opciones[i] = "Consulta del " + c.getFechaHora().toLocalDate() + " con Dr. " + c.getDoctor();
        }

        String seleccion = (String) JOptionPane.showInputDialog(this, "Selecciona la consulta a eliminar:",
               "Eliminar Consulta", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (seleccion == null) return;

        int index = Arrays.asList(opciones).indexOf(seleccion);
        Consulta consultaSeleccionada = consultasPaciente.get(index);

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar esta consulta?",
            "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            BaseDatos.consultas.remove(consultaSeleccionada);
            JOptionPane.showMessageDialog(this, "Consulta eliminada.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MedicoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MedicoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MedicoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MedicoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MedicoUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
