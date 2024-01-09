/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.garaje;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 * Recuerda ejecutar el script de sql antes de usar el progarama, esta dentro de
 * la carpeta de java.
 *
 * @author Jorge Wang Wang
 */
public class Garaje {

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }
}

class Ventana extends JFrame {

    public Ventana() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cierra el programa al salir
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
        double width = screenSize.getWidth();//los guardo en variables
        double height = screenSize.getHeight();
        setBounds((int) (width / 4), (int) (height / 4), 1000, 600);
        setResizable(false);
        panel panelo = new panel();
        add(panelo);
//y siempre apareceran en el medio

    }
}

class panel extends JPanel {

    public panel() {
        setLayout(new BorderLayout());
        //creacion del menu
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Primera Parte");
        JMenuItem item1 = new JMenuItem("Ibiza");
        JMenuItem item2 = new JMenuItem("Jonas");
        menu.add(item1);
        menu.add(item2);
        bar.add(menu);
        //Para centrarlo
        JMenu menu2 = new JMenu("Segunda Parte");
        JMenuItem item3 = new JMenuItem("Mostrar Plazas Libres");
        menu2.add(item3);
        bar.add(menu2);
        JPanel arriba = new JPanel(new FlowLayout(FlowLayout.CENTER));
        arriba.add(bar);

        //creamos el text area con su scrollpane
        JTextArea ta = new JTextArea();
        ta.setEnabled(true);
        JScrollPane sp = new JScrollPane(ta);
        ta.setEditable(false);
        //añadimos ambos objetos al menu

        JButton boton = new JButton("Introducir Coche");
        JButton boton1 = new JButton("Introducir Usuario");
        JButton boton2 = new JButton("Introducir Plaza de Garaje");
        FlowLayout fw = new FlowLayout(FlowLayout.CENTER);
        JPanel es = new JPanel(fw);
        es.add(boton);
        es.add(boton1);
        es.add(boton2);
        add(es, BorderLayout.SOUTH);
        add(arriba, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        String url = "jdbc:mysql://localhost:3306/Garaje";
        String username = "root";
        String password = "";
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame();

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
                double width = screenSize.getWidth();//los guardo en variables
                double height = screenSize.getHeight();
                jf.setBounds((int) (width / 3), (int) (height / 3), 500, 300);
                jf.setResizable(false);
                JPanel panelCoches = new JPanel(new GridLayout(6, 2, 10, 10));
                JLabel j1 = new JLabel("Matricula:");

                JTextField jf1 = new JTextField();
                JButton AyudaFormatos = new JButton("?");
                AyudaFormatos.setToolTipText("Formatos Permitidos");
                JPanel ayudaCoche = new JPanel(new GridLayout(1, 2));
                ayudaCoche.add(jf1);
                ayudaCoche.add(AyudaFormatos);
                JLabel j2 = new JLabel("Marca:");
                JTextField jf2 = new JTextField();
                JLabel j3 = new JLabel("Modelo");
                JTextField jf3 = new JTextField();
                JLabel j4 = new JLabel("año");
                JTextField jf4 = new JTextField();
                JButton botonConfirmar = new JButton("Confirmar");
                JButton botonBorrar = new JButton("Borrar");
                panelCoches.setBorder(new EmptyBorder(10, 10, 10, 10));
                panelCoches.add(j1);
                panelCoches.add(ayudaCoche);
                panelCoches.add(j2);
                panelCoches.add(jf2);
                panelCoches.add(j3);
                panelCoches.add(jf3);
                panelCoches.add(j4);
                panelCoches.add(jf4);
                panelCoches.add(botonConfirmar);
                panelCoches.add(botonBorrar);
                jf.add(panelCoches);
                jf.setVisible(true);
                botonConfirmar.addActionListener((ActionEvent e1) -> {
                    String numeroCoche = jf1.getText();
                    numeroCoche = numeroCoche.replace(" ", "");
                    numeroCoche = numeroCoche.toUpperCase();
                    String numeroMarca = jf2.getText();
                    String numeroModelo = jf3.getText();
                    String numeroAnyo = jf4.getText();
                    String regex = "[0-9]{4}[A-Z]{3}";
                    String regex2 = "[A-Z]{1}[0-9]{4}[A-Z]{2}";
                    String regex3 = "[A-Z]{1}[0-9]{6}";
                    String regex4 = "[A-Z]{1}[0-9]{4}[A-Z]{3}";
                    String regex5 = "[A-Z]{2}[0-9]{6}";
                    Pattern pattern1 = Pattern.compile(regex);
                    Pattern pattern2 = Pattern.compile(regex2);
                    Pattern pattern3 = Pattern.compile(regex3);
                    Pattern pattern4 = Pattern.compile(regex4);
                    Pattern pattern5 = Pattern.compile(regex5);
                    Matcher match1 = pattern1.matcher(numeroCoche);
                    Matcher match2 = pattern2.matcher(numeroCoche);
                    Matcher match3 = pattern3.matcher(numeroCoche);
                    Matcher match4 = pattern4.matcher(numeroCoche);
                    Matcher match5 = pattern5.matcher(numeroCoche);
                    if (match1.matches() || match2.matches() || match3.matches() || match4.matches() || match5.matches()) {
                        if (!"".equals(numeroMarca) || numeroMarca == null) {
                            if (!"".equals(numeroModelo) || numeroModelo == null) {
                                if (!"".equals(numeroAnyo) || numeroAnyo == null) {
                                    try {
                                        Connection c = DriverManager.getConnection(url, username, password);
                                        Statement stm = c.createStatement();
                                        stm.executeUpdate("Insert into Coches(Matricula,marca,modelo,anyo) values('" + numeroCoche + "','" + numeroMarca + "','" + numeroModelo + "'," + numeroAnyo + ")");
                                        JOptionPane.showMessageDialog(null, "Se introducio correctamente el coche");
                                        jf.setVisible(false);
                                        jf1.setText("");
                                        jf2.setText("");
                                        jf3.setText("");
                                        jf4.setText("");
                                        stm.close();
                                        c.close();
                                    } catch (SQLIntegrityConstraintViolationException ex) {
                                        JOptionPane.showMessageDialog(null, "Ya existe este coche");
                                    } catch (SQLException Es) {
                                        System.out.println("Error SQL");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error no puede estar vacio Año");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error no puede estar vacio Modelo");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error no puede estar vacio Marca");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El formato debe ser NNNNLLL O LNNNNLL");
                    }
                });
                botonBorrar.addActionListener((ActionEvent e1) -> {
                    jf2.setText("");
                    jf3.setText("");
                    jf4.setText("");
                });
                AyudaFormatos.addActionListener((ActionEvent e2) -> {
                    JOptionPane.showMessageDialog(null, "Formatos Permitidos:\nLNNNNNN\nLLNNNNNN\nNNNNLLL\nLNNNNLL\nLNNNNLLL");
                });
            }
        });
        boton1.addActionListener((ActionEvent e) -> {
            JFrame jf = new JFrame();
            Connection c1;
            ArrayList<String> lista = new ArrayList<>();
            int datos = 0;
            try {
                c1 = DriverManager.getConnection(url, username, password);
                Statement stm1 = c1.createStatement();

                ResultSet rs = stm1.executeQuery("Select numero_usuario from usuario order by numero_usuario desc limit 1");
                while (rs.next()) {
                    datos = rs.getInt("numero_usuario") + 1;
                }
                String linea = "";
                ResultSet rs1 = stm1.executeQuery("select * from coches");

                while (rs1.next()) {
                    linea = rs1.getString("Matricula") + " " + rs1.getString("marca") + " " + rs1.getString("modelo") + " " + rs1.getInt("anyo");
                    lista.add(linea);
                }
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
            double width = screenSize.getWidth();//los guardo en variables
            double height = screenSize.getHeight();
            jf.setBounds((int) (width / 3), (int) (height / 3), 500, 300);
            jf.setResizable(false);
            JPanel panelCoches = new JPanel(new GridLayout(6, 2, 20, 20));
            JLabel j1 = new JLabel("Numero de Usuario:");
            JTextField jf1 = new JTextField();
            jf1.setText(datos + "");
            jf1.setEditable(false);
            JLabel j2 = new JLabel("Nombre:");
            JTextField jf2 = new JTextField();
            JLabel j3 = new JLabel("Apellidos");
            JTextField jf3 = new JTextField();
            JLabel j4 = new JLabel("fecha nacimiento");
            JTextField jf4 = new JTextField();
            JLabel j5 = new JLabel("Matricula");
            JComboBox<String> jf5 = new JComboBox();
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jf5.getModel();
            model.addAll(lista);
            JButton botonConfirmar = new JButton("Confirmar");
            JButton botonBorrar = new JButton("Borrar");
            panelCoches.setBorder(new EmptyBorder(20, 10, 20, 10));
            panelCoches.add(j1);
            panelCoches.add(jf1);
            panelCoches.add(j2);
            panelCoches.add(jf2);
            panelCoches.add(j3);
            panelCoches.add(jf3);
            panelCoches.add(j4);
            panelCoches.add(jf4);
            panelCoches.add(j5);
            panelCoches.add(jf5);

            panelCoches.add(botonConfirmar);
            panelCoches.add(botonBorrar);
            jf.add(panelCoches);
            jf.setVisible(true);
            botonConfirmar.addActionListener((ActionEvent e1) -> {
                String numeroCoche = jf1.getText();
                String numeroNombre = jf2.getText();
                String numeroApellidos = jf3.getText();
                String numeroNacimiento = jf4.getText();

                String regex = "[0-9]+";
                String formato = "dd/mm/yyyy"; // Define el formato de fecha esperado.

                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                sdf.setLenient(false);
                Pattern pattern = Pattern.compile(regex);
                try {
                    String numeroCocheSel = lista.get(jf5.getSelectedIndex()).substring(0, 7);
                    java.util.Date fecha = sdf.parse(numeroNacimiento);
                    if (jf5.getSelectedIndex() > -1) {
                        if (!"".equals(numeroCoche) || numeroCoche == null) {
                            if (numeroNombre != "" || numeroNombre == null) {
                                if (!"".equals(numeroApellidos) || numeroApellidos == null) {
                                    if (numeroNacimiento != "" || numeroNacimiento == null) {
                                        try {
                                            Connection c = DriverManager.getConnection(url, username, password);
                                            Statement stm = c.createStatement();
                                            stm.executeUpdate("Insert into usuario(Numero_usuario,nombre,apellidos,fecha_nacimiento,Matricula) values(" + numeroCoche + ",'" + numeroNombre + "','" + numeroApellidos + "','" + numeroNacimiento + "','" + numeroCocheSel + "')");
                                            JOptionPane.showMessageDialog(null, "Se realizo con exito la introducción del usuario.");
                                            jf2.setText("");
                                            jf3.setText("");
                                            jf4.setText("");
                                            jf5.setSelectedIndex(-1);
                                            jf.setVisible(false);
                                            stm.close();
                                            c.close();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error no puede estar vacio Año");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error no puede estar vacio Modelo");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error no puede estar vacio Marca");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error no puede estar vacio Numero de Coche");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe elegir un coche");
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error el formato de la fecha tiene que ser dd/mm/yyyy");
                } catch (IndexOutOfBoundsException ez) {
                    JOptionPane.showMessageDialog(null, "Tienes que elegir una matricula");
                }

            });
            botonBorrar.addActionListener((ActionEvent e1) -> {
                jf2.setText("");
                jf3.setText("");
                jf4.setText("");
                jf5.setSelectedIndex(-1);
            });

        });
        boton2.addActionListener((ActionEvent e) -> {
            JFrame parking = new JFrame();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
            double width = screenSize.getWidth();//los guardo en variables
            double height = screenSize.getHeight();
            parking.setBounds((int) (width / 3), (int) (height / 3), 800, 300);
            parking.setResizable(false);
            parking.setLayout(new GridLayout(2, 10, 15, 15));

            String linea[] = new String[20];
            boolean[] lista = new boolean[20];
            String[] matriculalista = new String[20];
            ArrayList<Usuario> usuario = new ArrayList<>();
            try {
                Connection c1 = DriverManager.getConnection(url, username, password);
                Statement stm1 = c1.createStatement();
                ResultSet rs1 = stm1.executeQuery("Select * from Plazas_Garaje");

                int cont = 0;
                while (rs1.next()) {
                    linea[cont] = rs1.getString("TipoDePlaza");
                    lista[cont] = rs1.getBoolean("Onuse");
                    matriculalista[cont] = rs1.getString("Matricula");
                    cont++;
                }
                ResultSet rs2 = stm1.executeQuery("Select * from Usuario");
                while (rs2.next()) {
                    usuario.add(new Usuario(rs2.getInt("numero_usuario"), rs2.getString("nombre") + " " + rs2.getString("apellidos"), rs2.getString("Matricula")));
                }
                ResultSet rs3 = stm1.executeQuery("");
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            JComboBox<Usuario> jf5 = new JComboBox();
            DefaultComboBoxModel<Usuario> model = (DefaultComboBoxModel<Usuario>) jf5.getModel();
            model.addAll(usuario);
            JLabel[] l = new JLabel[20];
            JPanel[] pablo = new JPanel[20];
            ActionListener buttonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    Connection c1;
                    try {
                        c1 = DriverManager.getConnection(url, username, password);
                        Statement stm1 = c1.createStatement();
                        JButton clickedButton = (JButton) e.getSource();
                        Usuario us = (Usuario) jf5.getSelectedItem();
                        System.out.println(us);
                        int indice = Integer.parseInt(clickedButton.getText()) - 1;
                        
                        JLabel jl = l[indice];
                        if ("libre".equals(jl.getText())) {
                            int confirmar = JOptionPane.showConfirmDialog(null, "Desea confirmar que quiere seleccionar este sitio");
                            if (confirmar == JOptionPane.YES_OPTION) {
                                stm1.executeUpdate("update plazas_garaje set matricula = '" + us.getMatricula() + "', TipoDePlaza = 'ocupada', onuse = true where Numero = " + clickedButton.getText());
                                l[indice].setText("ocupada");
                                pablo[indice].setBackground(Color.red);
                                parking.setVisible(false);
                                stm1.close();
                                c1.close();
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "No esta disponible el sitio.");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };

            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);

            for (int i = 0; i < 20; i++) {
                JPanel p = new JPanel(new GridLayout(2, 1));

                JLabel label = new JLabel(linea[i]);
                JPanel ara = new JPanel(new FlowLayout(FlowLayout.CENTER));
                ara.add(label);
                l[i] = label;
                p.add(ara);

                JButton b1 = new JButton((i + 1) + "");
                b1.setBackground(Color.LIGHT_GRAY);
                b1.setOpaque(true);
                b1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                b1.addActionListener(buttonListener);
                p.setBorder(blackBorder);
                p.add(b1);
                parking.add(p);
                pablo[i] = p;
                CambiarColor(pablo, label, i);
            }
            String[] menuTexto = {"Aparcar Coche","Retirar Coche","Cancel"};
            int elegir = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Titulo",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE, null, menuTexto, menuTexto[0]);
            
            int resultado = JOptionPane.showConfirmDialog(null, jf5, "Elige una opción",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resultado == JOptionPane.OK_OPTION && resultado > -1) {

                parking.setVisible(true);
            }

        }
        );

        item1.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                //comando que enviara
                String select = "select usuario.nombre as name, usuario.apellidos as apellido from usuario inner join coches on coches.matricula = usuario.matricula where coches.matricula = '9823POO'";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(select);
                String texto = "Los usuarios que usan ibiza son:\n";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getString("name") + " " + rs.getString("apellido") + "\n";
                }
                JOptionPane.showMessageDialog(null, texto);
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item2.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                //comando que enviara
                String select = "select coches.matricula as num, coches.modelo as modelo from coches inner join usuario on  usuario.matricula = coches.matricula where coches.matricula = '1234UIO'";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(select);
                String texto = "Los usuarios que usan ibiza son:\n";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getString("num") + " " + rs.getString("modelo") + "\n";
                }
                JOptionPane.showMessageDialog(null, texto);
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item3.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                //comando que enviara
                String select = "select count(TipoDePlaza) as libre from plazas_garaje where tipodeplaza = 'libre'";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(select);
                String texto = "La cantidad de plazas libres hay es :";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getInt("libre");
                }
                JOptionPane.showMessageDialog(null, texto);
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public static void CambiarColor(JPanel[] panel, JLabel l, int i) {
        if (null != l.getText()) {
            switch (l.getText()) {
                case "libre":
                    panel[i].setBackground(Color.green);
                    break;
                case "ocupada":
                    panel[i].setBackground(Color.red);
                    break;
                case "disabled":
                    panel[i].setBackground(Color.black);
                    break;
                case "reservada":
                    panel[i].setBackground(Color.yellow);
                    break;
                default:
                    break;
            }
        }
    }
}
