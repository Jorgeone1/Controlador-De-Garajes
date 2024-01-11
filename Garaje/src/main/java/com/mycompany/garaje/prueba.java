/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.garaje;

import java.util.ArrayList;

/**
 *
 * @author Jorge Wang Wang
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Usuario> us = new ArrayList<>();
        ventanaDinero frame = new ventanaDinero(us,1);  
        frame.setVisible(true);
    }
    
}
