/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.garaje;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Instant;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
/**
 *
 * @author jww11
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario y Hora");

        // Crear un JDateChooser para el calendario
        JDateChooser dateChooser = new JDateChooser();

        // Crear un JSpinner para la hora
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);

        // Crear un panel y agregar los componentes
        JPanel panel = new JPanel();
        panel.add(new JLabel("Fecha:"));
        panel.add(dateChooser);
        panel.add(new JLabel("Hora:"));
        panel.add(timeSpinner);

        // Agregar el panel al marco
        frame.add(panel);

        // Configuración de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
}
