/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.garaje;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alumno
 */
public class panel extends JPanel {
    
    private Conectar c10 = new Conectar();
    private JPanel[] listaPaneles = new JPanel[20];
    private JLabel[] listaLineas = new JLabel[20];

    public panel() {
        try {
            // Utiliza el Look and Feel de Windows
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        //pone el layout como borderLayout
        setLayout(new BorderLayout());
        //datos para la base de datos.
        String url = "jdbc:mysql://localhost:3306/Garaje";
        String username = "root";
        String password = "";
        
        //Creacion del menu y sus items con su barra
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("Primera Parte");
        JMenuItem item1 = new JMenuItem("Ibiza");
        JMenuItem item2 = new JMenuItem("Jonas");
        menu.add(item1);
        menu.add(item2);
        bar.add(menu);
        JMenu menu2 = new JMenu("Segunda Parte");
        JMenuItem item3 = new JMenuItem("Mostrar Plazas Libres");
        menu2.add(item3);
        bar.add(menu2);
        JMenu menu3 = new JMenu("Opcional");
        JMenuItem item4 = new JMenuItem("Importe Total");
        JMenuItem item5 = new JMenuItem("Total Pagados");
        JMenuItem item6 = new JMenuItem("Factura");
        menu3.add(item4);
        menu3.add(item5);
        menu3.add(item6);
        bar.add(menu3);
        
        //panel para centrar el menu yponerlo arriba
        JPanel arriba = new JPanel(new FlowLayout(FlowLayout.CENTER));
        arriba.add(bar);
        
        //creacion del panel del medio
        JPanel medium = new JPanel(new GridLayout(2, 10, 10, 10));
        
        //arraylist para guardar los nombres
        ArrayList<String> listaNombress = new ArrayList<>();
        try {
            //select que recoge los 20 nombre de los garaje y los guarda en el arrayList
            Statement stm5 = c10.Conectar();
            ResultSet listaPlazasGaraje = stm5.executeQuery("Select * from plazas_garaje");
            while (listaPlazasGaraje.next()) {
                listaNombress.add(listaPlazasGaraje.getString("tipodeplaza"));
            }
            stm5.close();
        } catch (SQLException ex) {

        }
        //crea un actionListener para luego añadir al boton para imprimir los datos del que este aparcado
        ActionListener butnListener = (ActionEvent e) -> {
            //recoge el boton que haya pulsado
            JButton button = (JButton) e.getSource();
            int numer = Integer.parseInt(button.getText());
            //comprueba que boton ha pulsado
            switch (listaLineas[numer - 1].getText()) {
                case "libre" -> {
                    JOptionPane.showMessageDialog(null, "Este parking no contiene ningun coche");
                }
                case "disabled" -> {
                    JOptionPane.showMessageDialog(null, "Estos parkings estan deshabilitados");
                }
                case "reservada" -> {
                    JOptionPane.showMessageDialog(null, "Estos parking estan reservado por x persona");
                }
                case "ocupada" -> {
                    try {
                        //select en donde 
                        Statement stm1 = c10.Conectar();
                        ResultSet rs = stm1.executeQuery("select plazas_garaje.numero as posicion, Usuario.numero_usuario as numero_usuario,nombre, apellidos, usuario.matricula as matricula from Usuario inner join plazas_garaje on Usuario.numero_usuario = plazas_garaje.numero_usuario where plazas_garaje.numero = "+numer);
                        //Texto a imprimir con la informacion del coche y usuario ocupado
                        String texto = "";
                        while (rs.next()) {
                            texto = "PARKING "+rs.getInt("posicion")+"\nEsta ocupado por el coche con matricula " + rs.getString("matricula") + "\nEl usuario con nombre: " + rs.getString("nombre") +"\nCuyo ID es "+rs.getInt("numero_usuario");
                            
                        }
                        JOptionPane.showMessageDialog(null, texto);
                        stm1.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        };
        //mejora los bordes de los botones para que sea mas fluido
        Border blackBorder2 = BorderFactory.createLineBorder(Color.BLACK);
        for (int i = 0; i < 20; i++) {
            //crea 20 botones con informacion 
            JPanel pew = new JPanel(new GridLayout(2, 1, 5, 5));
            JPanel treh = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton b = new JButton((i + 1) + "");
            JLabel l = new JLabel(listaNombress.get(i));
            treh.add(l);
            pew.add(treh);
            //le añade el actionListener al boton y cambia el color y pone su borde
            b.addActionListener(butnListener);
            b.setBackground(Color.LIGHT_GRAY);
            b.setOpaque(true);
            b.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            pew.add(b);
            //añade a la lista y guarda la informacion de ese panel y boton
            listaPaneles[i] = pew;
            listaLineas[i] = l;
            medium.add(pew);
            pew.setBorder(blackBorder2);
            //cambia el color dependiendo del color del label del array.
            CambiarColor(listaPaneles, l, i);

        }
        //añadimos ambos objetos al menu
        JButton boton = new JButton("Introducir Coche");
        JButton boton1 = new JButton("Introducir Usuario");
        JButton boton2 = new JButton("Introducir o sacar coche");
        FlowLayout fw = new FlowLayout(FlowLayout.CENTER);
        JPanel es = new JPanel(fw);
        es.add(boton);
        es.add(boton1);
        es.add(boton2);
        //terminamos añadiendo todos los paneles
        add(es, BorderLayout.SOUTH);
        add(arriba, BorderLayout.NORTH);
        add(medium, BorderLayout.CENTER);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf = new JFrame();
                //datos del JFRame
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
                double width = screenSize.getWidth();//los guardo en variables
                double height = screenSize.getHeight();
                jf.setBounds((int) (width / 3), (int) (height / 3), 500, 300);
                jf.setResizable(false);
                
                //datos de los componentes del JFrame
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
                
                //funciones de los botones
                botonConfirmar.addActionListener((ActionEvent e1) -> {
                    //recoge el texto
                    String numeroCoche = jf1.getText();
                    numeroCoche = numeroCoche.replace(" ", "");//quita espacios y pone en mayusucula las letras
                    numeroCoche = numeroCoche.toUpperCase();
                    String numeroMarca = jf2.getText();
                    String numeroModelo = jf3.getText();
                    String numeroAnyo = jf4.getText();
                    
                    //lista de patrones del regex, ademas con el pattern y matche crean el regex
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
                    //compruebaciones
                    if (match1.matches() || match2.matches() || match3.matches() || match4.matches() || match5.matches()) {//comprueba que la matricula sea valida
                        if (!"".equals(numeroMarca) || numeroMarca == null) {//comprueba que no este vacio
                            if (!"".equals(numeroModelo) || numeroModelo == null) {//comprueba que no este vacio
                                if (!"".equals(numeroAnyo) || numeroAnyo == null) {//comprueba que no este vacio
                                    try {
                                        //ejecuta el insert
                                        Statement stm = c10.Conectar();
                                        stm.executeUpdate("Insert into Coches(Matricula,marca,modelo,anyo) values('" + numeroCoche + "','" + numeroMarca + "','" + numeroModelo + "'," + numeroAnyo + ")");
                                        JOptionPane.showMessageDialog(null, "Se introducio correctamente el coche");
                                        //cierra el JFrame secundario cuando acabe todas las funciones
                                        jf.dispose();
                                        stm.close();
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
                        JOptionPane.showMessageDialog(null, "El formato es erroneo\nUsa ? para ver los formatos disponibles");
                    }
                });
                //borra los datos y reinicia
                botonBorrar.addActionListener((ActionEvent e1) -> {
                    jf2.setText("");
                    jf3.setText("");
                    jf4.setText("");
                });
                //imrpime los formatos
                AyudaFormatos.addActionListener((ActionEvent e2) -> {
                    JOptionPane.showMessageDialog(null, "Formatos Permitidos:\nLNNNNNN\nLLNNNNNN\nNNNNLLL\nLNNNNLL\nLNNNNLLL");
                });
            }
        });
        
        boton1.addActionListener((ActionEvent e) -> {
            JFrame jf = new JFrame();
            ArrayList<Coches> lista = new ArrayList<>();
            int datos = 0;
            try {
                //recogemos el ultimo id
                Statement stm1 = c10.Conectar();
                ResultSet rs = stm1.executeQuery("Select numero_usuario from usuario order by numero_usuario desc limit 1");
                while (rs.next()) {
                    datos = rs.getInt("numero_usuario") + 1;//le suma nuevo para que el usuario sepa cual seria su id
                }
                //recogemos los datos de los coches disponibles
                ResultSet rs1 = stm1.executeQuery("select * from coches");
                while (rs1.next()) {
                    lista.add(new Coches(rs1.getString("matricula"), rs1.getString("marca"), rs1.getString("modelo"), rs1.getInt("anyo")));
                }
                stm1.close();
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            //datos del JFrame
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
            double width = screenSize.getWidth();//los guardo en variables
            double height = screenSize.getHeight();
            jf.setBounds((int) (width / 3), (int) (height / 3), 500, 300);
            jf.setResizable(false);
            
            //componentes del JFrame
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
            JComboBox<Coches> jf5 = new JComboBox();
            DefaultComboBoxModel<Coches> model = (DefaultComboBoxModel<Coches>) jf5.getModel();
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
            //Funciones de los botones
            botonConfirmar.addActionListener((ActionEvent e1) -> {
                //recoge los datos
                String numeroCoche = jf1.getText();
                String numeroNombre = jf2.getText();
                String numeroApellidos = jf3.getText();
                String numeroNacimiento = jf4.getText();
                
                String formato = "dd/MM/yyyy"; // Define el formato de fecha esperado.
                //guarda el formato en un objeto
                SimpleDateFormat sdf = new SimpleDateFormat(formato);
                sdf.setLenient(false);
                try {
                    //crea la fecha con Date si da error, va al catch
                    String numeroCocheSel = lista.get(jf5.getSelectedIndex()).getMatricula();
                    java.util.Date fecha = sdf.parse(numeroNacimiento);
                    //comprobaciones si estan bien la informacion del usuario
                    if (jf5.getSelectedIndex() > -1) {
                        if (!"".equals(numeroCoche) || numeroCoche == null) {
                            if (!"".equals(numeroNombre) || numeroNombre == null) {
                                if (!"".equals(numeroApellidos) || numeroApellidos == null) {
                                    if (!"".equals(numeroNacimiento) || numeroNacimiento == null) {
                                        try {
                                            //hacemos el insert
                                            Statement stm = c10.Conectar();
                                            stm.executeUpdate("Insert into usuario(Numero_usuario,nombre,apellidos,fecha_nacimiento,Matricula) values(" + numeroCoche + ",'" + numeroNombre + "','" + numeroApellidos + "','" + numeroNacimiento + "','" + numeroCocheSel + "')");
                                            JOptionPane.showMessageDialog(null, "Se realizo con exito la introducción del usuario.");
                                            //cerramos el JFrame secundario
                                            jf.dispose();
                                            stm.close();
                                        } catch (SQLException ex) {
                                            Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error no puede estar vacio Año");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error no puede estar vacio Apellidos");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error no puede estar vacio Nombre");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error no puede estar vacio Nombre");
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
            //borra los datos de los textfield
            botonBorrar.addActionListener((ActionEvent e1) -> {
                jf2.setText("");
                jf3.setText("");
                jf4.setText("");
                jf5.setSelectedIndex(-1);
            });

        });
        
        //boton de añadir 
        boton2.addActionListener((ActionEvent e) -> {
            JFrame parking = new JFrame();
            //ajustes del JFRame
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//comprueba el tamaño de la pantalla
            double width = screenSize.getWidth();//los guardo en variables
            double height = screenSize.getHeight();
            parking.setBounds((int) (width / 3), (int) (height / 3), 800, 300);
            parking.setResizable(false);
            parking.setLayout(new GridLayout(2, 10, 15, 15));
            //array normales y arraylist para guardar datos
            String linea[] = new String[20];
            ArrayList<Usuario> usuario = new ArrayList<>();
            ArrayList<Usuario> usuarioSinParking = new ArrayList<>();
            try {
                //guardamos los usuarios que estan en el parking y quienenes no.
                Statement stm1 = c10.Conectar();
                ResultSet rs1 = stm1.executeQuery("Select * from Plazas_Garaje");

                int cont = 0;
                while (rs1.next()) {
                    linea[cont] = rs1.getString("TipoDePlaza");
                    cont++;
                }
                ResultSet rs2 = stm1.executeQuery("select * from usuario where Matricula in (select Matricula from plazas_garaje where Matricula is not null) and numero_usuario in (select numero_usuario from plazas_garaje where numero_usuario is not null)");
                while (rs2.next()) {
                    usuarioSinParking.add(new Usuario(rs2.getInt("numero_usuario"), rs2.getString("nombre") + " " + rs2.getString("apellidos"), rs2.getString("Matricula")));
                }

                ResultSet rs3 = stm1.executeQuery("select * from usuario where Matricula not in(Select Matricula from plazas_garaje where Matricula is not null);");
                while (rs3.next()) {
                    usuario.add(new Usuario(rs3.getInt("numero_usuario"), rs3.getString("nombre") + " " + rs3.getString("apellidos"), rs3.getString("Matricula")));
                }
                stm1.close();
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //componentes del JFrame
            JComboBox<Usuario> jf5 = new JComboBox();
            DefaultComboBoxModel<Usuario> model = (DefaultComboBoxModel<Usuario>) jf5.getModel();
            model.addAll(usuario);
            JComboBox<Usuario> jf6 = new JComboBox();
            DefaultComboBoxModel<Usuario> model1 = (DefaultComboBoxModel<Usuario>) jf6.getModel();
            model1.addAll(usuarioSinParking);
            //para guardar los botones y jpanel y label
            JLabel[] l = new JLabel[20];
            JPanel[] pablo = new JPanel[20];
            //crea una accion para todos los botones iguales
            ActionListener buttonListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    
                    try {
                        //statement
                        Statement stm1 = c10.Conectar();
                        //guarda el boton que haya pulsado
                        JButton clickedButton = (JButton) e.getSource();
                        Usuario us = (Usuario) jf5.getSelectedItem();//ademas del usuario
                        int indice = Integer.parseInt(clickedButton.getText()) - 1;//coge el nombre del indice y le resta uno para manejar los array

                        JLabel jl = l[indice];
                        if ("libre".equals(jl.getText())) {
                            int confirmar = JOptionPane.showConfirmDialog(null, "Desea confirmar que quiere seleccionar este sitio");
                            if (confirmar == JOptionPane.YES_OPTION) {
                                //ejecuta el update
                                stm1.executeUpdate("update plazas_garaje set matricula = '" + us.getMatricula() + "', TipoDePlaza = 'ocupada', onuse = true,numero_usuario = " + us.getNumero_usuario() + " where Numero = " + clickedButton.getText());
                                //cambia el menu y el resto de botones del menu de aparcar como su color texto etc
                                l[indice].setText("ocupada");
                                pablo[indice].setBackground(Color.red);
                                parking.setVisible(false);
                                listaLineas[indice].setText("ocupada");
                                CambiarColor(listaPaneles, listaLineas[indice], indice);
                                stm1.close();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No esta disponible el sitio.");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };

            Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
            //for como el del menu principal para crear el mini menu de aparcar
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
            //lista para que el usuario eliga 
            String[] menuTexto = {"Aparcar Coche", "Retirar Coche", "Cancel"};
            int elegir = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Titulo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuTexto, menuTexto[0]);
            switch (elegir) {
                case 0 -> {//activa el JFrame
                    int resultado = JOptionPane.showConfirmDialog(null, jf5, "Elige una opción", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (resultado == JOptionPane.OK_OPTION && resultado > -1) {
                        parking.setVisible(true);

                    }
                }
                case 1 -> {
                    try {
                        JOptionPane.showConfirmDialog(null, jf6, "Elige una opción", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                        int resultado = jf6.getSelectedIndex();
                        //recoge los datos del statement
                        Statement stm1 = c10.Conectar();
                        ResultSet rs = stm1.executeQuery("Select numero from plazas_garaje where matricula = '" + usuarioSinParking.get(resultado).getMatricula() + "'");
                        //guarda el parking del usuario elegido
                        int parkings = 0;
                        while (rs.next()) {
                            parkings = rs.getInt("numero");
                        }
                        stm1.close();
                        //confirma una vez mas antes de abrir la facturacion
                        int confirmarSacar = JOptionPane.showConfirmDialog(null, "Deseas liberar el aparcamiento " + parkings + " del coche " + usuarioSinParking.get(resultado).getMatricula());
                        if (confirmarSacar == JOptionPane.YES_OPTION) {
                            //datos para preparar poner la hora en la tabla
                            ventanaDinero frame = new ventanaDinero(usuarioSinParking.get(resultado), parkings, listaLineas, listaPaneles);
                            frame.setVisible(true);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IndexOutOfBoundsException tri) {

                    }
                }

                default -> {

                }
            }

        }
        );
        
        item1.addActionListener((ActionEvent e) -> {
            try {
                //comando que enviara
                String select = "select usuario.nombre as name, usuario.apellidos as apellido from usuario inner join coches on coches.matricula = usuario.matricula where coches.matricula = '9823POO'";
                Statement stm = c10.Conectar();
                ResultSet rs = stm.executeQuery(select);
                String texto = "Los usuarios que usan ibiza son:\n";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getString("name") + " " + rs.getString("apellido") + "\n";
                }
                JOptionPane.showMessageDialog(null, texto);
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item2.addActionListener((ActionEvent e) -> {
            try {
                //comando que enviara
                String select = "select coches.matricula as num, coches.modelo as modelo from coches inner join usuario on  usuario.matricula = coches.matricula where coches.matricula = '1234UIO'";
                Statement stm = c10.Conectar();
                ResultSet rs = stm.executeQuery(select);
                String texto = "Los usuarios que usan ibiza son:\n";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getString("num") + " " + rs.getString("modelo") + "\n";
                }
                JOptionPane.showMessageDialog(null, texto);
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item3.addActionListener((ActionEvent e) -> {
            try {
                //comando que enviara
                String select = "select count(TipoDePlaza) as libre from plazas_garaje where tipodeplaza = 'libre'";
                Statement stm = c10.Conectar();
                ResultSet rs = stm.executeQuery(select);
                String texto = "La cantidad de plazas libres hay es :";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getInt("libre");
                }
                JOptionPane.showMessageDialog(null, texto);
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item4.addActionListener((ActionEvent e) -> {
            try {

                //comando que enviara
                String select = "select sum(importe) as total from tiempoestancia;";
                Statement stm = c10.Conectar();
                ResultSet rs = stm.executeQuery(select);
                String texto = "El importe total conseguido es:";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getFloat("total");
                }
                JOptionPane.showMessageDialog(null, texto);
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item5.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                //comando que enviara
                String select = "select count(id) as total from tiempoestancia;";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(select);
                String texto = "La cantidad de personas que han pagado es: ";

                while (rs.next()) {//los coge y luego los imprime
                    texto += rs.getInt("total");
                }
                JOptionPane.showMessageDialog(null, texto);
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        item6.addActionListener((ActionEvent e) -> {
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                //comando que enviara
                String select = "select * from tiempoestancia inner join usuario on usuario.numero_usuario = tiempoestancia.numero_usuario where tiempoestancia.id = ";
                String numero = JOptionPane.showInputDialog(null, "Pon su numero de factura");
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(select + numero);
                String texto = "Factura\n";
                ArrayList<String> comprobar = new ArrayList<>();
                while (rs.next()) {//los coge y luego los imprime
                    comprobar.add(rs.getString("fecha_nacimiento"));
                    texto += "ID Factura: " + rs.getInt("ID") + "\nDNI: " + rs.getString("DNI") + "\nNombre completo: " + rs.getString("nombre") + " " + rs.getString("apellidos") + "\nMatricula: " + rs.getString("matricula") + "\nDia " + rs.getDate("dia") + "\nhora entrada: " + rs.getTime("horainicio") + "\nhora salida: " + rs.getTime("horafinal")+"\nTipo de Pago: "+rs.getString("numero_tarjeta") + "\nImporte: " + rs.getFloat("importe") + "\nAportado " + rs.getFloat("pagado") + "\nResto: " + rs.getFloat("resto");
                }
                if (!comprobar.isEmpty()) {
                    JOptionPane.showMessageDialog(null, texto);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe la factura con ID " + numero);
                }

            } catch (SQLSyntaxErrorException io) {
                JOptionPane.showMessageDialog(null, "Error no existe esa factura");
            } catch (SQLException ex) {
                Logger.getLogger(panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
/**
 * Metodo que cambia el color de los paneles
 * @param panel lista de paneles 
 * @param l label recoge el nombre
 * @param i recoge la posicion de array de paneles
 */
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
