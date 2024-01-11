/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.garaje;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author Alumno
 */
public class ventanaDinero extends JFrame {

    Conectar c1 = new Conectar();
    ArrayList<Usuario> usuarios;
    int posicion;

    public ventanaDinero(ArrayList<Usuario> usuarios, int parkings) throws HeadlessException {
        this.usuarios = usuarios;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
        double width = screenSize.getWidth();//los guardo en variables
        double height = screenSize.getHeight();
        setBounds((int) (width / 3), (int) (height / 3), 450, 400);
        setResizable(false);
        setLayout(new GridLayout(9, 2, 15, 15));

        // Crear un JSpinner para la hora
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(spinnerModel);
        SpinnerDateModel spinnerModel2 = new SpinnerDateModel();
        JSpinner timeSpinner2 = new JSpinner(spinnerModel2);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(timeSpinner2, "HH:mm:ss");
        timeSpinner2.setEditor(timeEditor2);
        JTextField jf1 = new JTextField();
        JTextField jf2 = new JTextField();
        JTextField jf3 = new JTextField();
        JTextField jf4 = new JTextField();
        JTextField jf5 = new JTextField();
        jf5.setEditable(false);
        jf3.setEditable(false);
        JButton boton1 = new JButton("Confirmar");
        JButton boton2 = new JButton("Calcular Dinero");
        JButton boton3 = new JButton("Reescribir fecha");
        add(new JLabel("Datos Usuario"));
        add(jf5);
        add(new JLabel("Hora Entrada"));
        add(timeSpinner);
        add(new JLabel("Hora Salida"));
        add(timeSpinner2);
        add(new JLabel("DNI"));
        add(jf1);
        add(new JLabel("Numero Tarjeta"));
        add(jf2);
        add(new JLabel("Importe a pagar"));
        add(jf3);
        add(new JLabel("Cantidad a pagar"));
        add(jf4);
        add(boton1);
        add(boton2);
        add(boton3);
        boton1.setEnabled(false);
        boton1.addActionListener((ActionEvent e) -> {

            String DNI = jf1.getText();
            String numero_tarjeta = jf2.getText();

        });
        boton2.addActionListener((ActionEvent e) -> {
            try {

                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

                Date horaInicio = (Date) timeSpinner.getValue();
                String shi = formatoHora.format(horaInicio);

                Date horaFinal = (Date) timeSpinner2.getValue();
                String shf = formatoHora.format(horaFinal);

                LocalTime lhi = dateToLocalTime(horaInicio);
                LocalTime lhf = dateToLocalTime(horaFinal);

                double dinero = 0;
                if (shi.equals(shf)) {
                    dinero = dinero(24 * 60);
                } else if (lhi.isBefore(lhf)) {
                    // Calcular la diferencia entre las dos horas
                    Duration diferencia = Duration.between(lhi, lhf);
                    long minutosDiferencia = diferencia.toMinutes();
                    System.out.println(minutosDiferencia);
                    dinero = dinero(minutosDiferencia);
                } else {
                    Duration diferencia = Duration.between(lhi, lhf);
                    long minutosDiferencia = diferencia.toMinutes();
                    minutosDiferencia += 24 * 60;
                    dinero = dinero(minutosDiferencia);

                }
                dinero = Math.round(dinero * 100.0) / 100.0;
                jf3.setText(dinero + "");
                timeSpinner.setEnabled(false);
                timeSpinner2.setEnabled(false);
                boton1.setEnabled(true);
            } catch (Exception io) {
                JOptionPane.showMessageDialog(null,"Error en el formato o el contenido de las horas");
            }
        });
        boton3.addActionListener((ActionEvent e) -> {

            timeSpinner.setEnabled(true);
            timeSpinner2.setEnabled(true);
            jf3.setText("");
            boton1.setEnabled(false);
        });

    }

    public double dinero(long minutos) {
        double dinero = 0;
        if (minutos <= 30) {
            return dinero = minutos * 0.0425;
        } else {
            dinero += 30 * 0.0425;
            if (30 < minutos && minutos <= 90) {
                return dinero += (minutos - 30) * 0.0382;
            } else {
                dinero += 60 * 0.0382;
                if (minutos > 90 && minutos < 660) {
                    return dinero += (minutos - 90) * 0.0508;
                } else {
                    return 32.50;
                }
            }
        }
    }

    public static LocalTime dateToLocalTime(Date date) {
        // Obtener un Instant a partir del Date
        java.time.Instant instant = date.toInstant();

        // Obtener la zona horaria predeterminada del sistema
        ZoneId zoneId = ZoneId.systemDefault();

        // Convertir el Instant a LocalTime en la zona horaria
        return instant.atZone(zoneId).toLocalTime();
    }

    public boolean confirmar() {
        return true;
    }
}
