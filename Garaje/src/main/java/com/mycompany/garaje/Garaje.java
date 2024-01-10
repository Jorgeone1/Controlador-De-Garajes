/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.garaje;

import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;


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

    public Ventana(){

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

