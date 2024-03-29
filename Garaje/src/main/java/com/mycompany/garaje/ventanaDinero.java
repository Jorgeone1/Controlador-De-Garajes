/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.garaje;

import static com.mycompany.garaje.panel.CambiarColor;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.*;

/**
 *Clase que es la ventana donde se dara los datos a pagar
 * @author Jorge Wang Wang
 */
public class ventanaDinero extends JFrame {

    private Conectar c1 = new Conectar();
    private Usuario usuarios;
    private int posicion;
    private JLabel[] listaL;
    private JPanel[] paneles;
    public ventanaDinero(Usuario usuarios, int parkings,JLabel[] j,JPanel[] paneles) throws HeadlessException {
        this.usuarios = usuarios;
        this.posicion = parkings;
        this.listaL = j;
        this.paneles = paneles;
        //ajustes del JFrame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
        double width = screenSize.getWidth();//los guardo en variables
        double height = screenSize.getHeight();
        setBounds((int) (width / 3), (int) (height / 3), 450, 400);
        setResizable(false);
        setLayout(new GridLayout(9, 2, 15, 15));
        //componentes
        // Crear un JSpinner para la hora
        SpinnerDateModel spinnerModel = new SpinnerDateModel();
        JSpinner timeSpinner = new JSpinner(spinnerModel);
            //configura los JSpinner
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
        jf1.setEditable(false);
        jf2.setEditable(false);
        jf4.setEditable(false);
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
        jf5.setText(usuarios.toString());
        //acciones del boton 1
        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pattern = "[0-9]{8}[A-Za-z]{1}";//regex para comprobar el dni
                Pattern pattern1 = Pattern.compile(pattern);
                
                //recoger los datos de los textfield
                String DNI = jf1.getText();
                String numero_tarjeta = jf2.getText();
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
                //convierte en date las dos horas inicio y final
                Date horaInicio = (Date) timeSpinner.getValue();
                String shi = formatoHora.format(horaInicio);

                Date horaFinal = (Date) timeSpinner2.getValue();
                String shf = formatoHora.format(horaFinal);
                double dineroCliente =0;
                //comprueba que no este vacio y contenga caracteres
                try{
                dineroCliente = Double.parseDouble(jf4.getText());
                }catch(NumberFormatException es){
                    
                }
                
                double dineroApagar= Double.parseDouble(jf3.getText());
                double resto = 0;
                Matcher m = pattern1.matcher(DNI);
                //comprueba que esten bien los datos
                if(m.matches()){
                    if(dineroCliente>=dineroApagar){//Comprueba que el dinero que da la persona es mayor al importe
                        int elegir =JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea continuar con esta acción?");
                        if(elegir==JOptionPane.YES_OPTION){
                        Statement stm = c1.Conectar();
                        
                        try {
                            //calcula el resto y pone los decimales a 2
                            resto = dineroCliente-dineroApagar;
                            resto = resto*100;
                            resto = Math.round(resto);
                            resto = resto/100;
                            numero_tarjeta = numero_tarjeta.replace(" ","");
                            if("".equals(numero_tarjeta) ||numero_tarjeta==null){
                                numero_tarjeta = "Efectivo";
                            }
                            //ejecuta los inserts y update
                            stm.executeUpdate("insert into tiempoestancia(dni,numero_tarjeta,dia,horainicio,horafinal,importe,pagado,resto,numero_usuario) values ('"+DNI+"','"+numero_tarjeta+"',now(),'"+shi+"','"+shf+"',"+dineroApagar+","+dineroCliente+","+resto+","+usuarios.getNumero_usuario()+")");
                            stm.executeUpdate("Update plazas_garaje set tipodeplaza = 'libre', onuse = false,matricula = null,numero_usuario = null where numero = " + parkings);
                            //actualiza el menu para que se vean los cambios
                            listaL[parkings - 1].setText("libre");
                            CambiarColor(paneles, listaL[parkings - 1], parkings - 1);
                            ResultSet rs = stm.executeQuery("SELECT id FROM `tiempoestancia` order by id desc limit 1 ");
                            int indice = 0;
                            while(rs.next()){
                                indice = rs.getInt("id");
                            }
                            //imprime la factura, una vez que le de a confirmar se cierra el JFrame
                            JOptionPane.showMessageDialog(null, "FACTURA\nLe sobra "+resto+"€\nSi quiere saber mas detalles de la factura\nmirelo en el menui principal en opcional arriba poniendo el codigo\nEl codigo de la factura es "+indice);
                            dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(ventanaDinero.class.getName()).log(Level.SEVERE, null, ex);
                        }finally{
                            try {
                                stm.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(ventanaDinero.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El dinero que aportas es menor que el dinero que debes");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"El dni no tiene el formato correcto.");
                }
            }
        });
        //hace los calculos necesarios 
        boton2.addActionListener((ActionEvent e) -> {
            try {
                //guarda el formato del modo de hora
                SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

                Date horaInicio = (Date) timeSpinner.getValue();
                String shi = formatoHora.format(horaInicio);

                Date horaFinal = (Date) timeSpinner2.getValue();
                String shf = formatoHora.format(horaFinal);
                //cambia el hora a localTime porque es mas manejable y tiene mas metodos
                LocalTime lhi = dateToLocalTime(horaInicio);
                LocalTime lhf = dateToLocalTime(horaFinal);

                double dinero = 0;
                if (shi.equals(shf)) {//si es igual
                    dinero = dinero(24 * 60);//cobra las 24 horas
                } else if (lhi.isBefore(lhf)) {//si la hora inicial es anterior a la hora final
                    // Calcular la diferencia entre las dos horas
                    Duration diferencia = Duration.between(lhi, lhf);//calcula la diferencia
                    long minutosDiferencia = diferencia.toMinutes();// lo convierte en minutos
                    dinero = dinero(minutosDiferencia);//calcula el dinero
                } else {//si es posterior,
                    Duration diferencia = Duration.between(lhi, lhf);//recoge la diferencia en negativo
                    long minutosDiferencia = diferencia.toMinutes();//lo convierte en minutos
                    minutosDiferencia += 24 * 60;//y le resta 24 horas
                    dinero = dinero(minutosDiferencia);//calcula el dinero

                }
                //redondea 2 decimales
                dinero = Math.round(dinero * 100.0) / 100.0;
                jf3.setText(dinero + "");//imprime el texto en el textField
                //activa los botones
                timeSpinner.setEnabled(false);
                timeSpinner2.setEnabled(false);
                boton1.setEnabled(true);
                jf1.setEditable(true);
                jf2.setEditable(true);
                jf4.setEditable(true);
        
            } catch (Exception io) {
                JOptionPane.showMessageDialog(null,"Error en el formato o el contenido de las horas");
            }
        });
        //vuelve a la posicion de poner fecha por si se equivoca el cliente
        boton3.addActionListener((ActionEvent e) -> {

            timeSpinner.setEnabled(true);
            timeSpinner2.setEnabled(true);
            jf3.setText("");
            boton1.setEnabled(false);
            jf1.setEditable(false);
            jf2.setEditable(false);
            jf4.setEditable(false);
            jf4.setText("");
        
        });

    }
    /**
     * Calcula el dinero del tiempo que ha estado en el garaje.
     * @param minutos cantidad de minutos que estuvo el coche
     * @return devuelve un double con el dinero
     */
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
