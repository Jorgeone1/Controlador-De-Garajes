/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.garaje;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author Alumno
 */
class BotonCircular extends JButton {

    public BotonCircular(String label) {
        super(label);
        setContentAreaFilled(false); // Hace que el fondo del botón sea transparente
        setFocusPainted(false); // Quita el resaltado del botón cuando se enfoca
        setPreferredSize(new Dimension(100, 100)); // Establece el tamaño del botón circular
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.LIGHT_GRAY); // Color de fondo cuando el botón está presionado
        } else {
            g.setColor(getBackground()); // Color de fondo normal
        }
        g.fillOval(0, 0, getWidth() - 1, getHeight() - 1); // Dibuja un círculo

        super.paintComponent(g); // Llama al método de la clase base para dibujar el contenido del botón
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground()); // Color del borde
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1); // Dibuja el borde del círculo
    }
}
