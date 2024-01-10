/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.garaje;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class Conectar {
    String url = "jdbc:mysql://localhost:3306/Garaje";
        String username = "root";
        String password = "";
        public Statement Conectar()
        {
             Statement stm = null;
        try {
             Connection c1= DriverManager.getConnection(url,username,password);
             stm = c1.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stm;
        }
}
