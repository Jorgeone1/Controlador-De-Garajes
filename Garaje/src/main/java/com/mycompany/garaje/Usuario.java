/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.garaje;

/**
 *
 * @author jww11
 */
public class Usuario {
    private int numero_usuario;
 private String nombre;
 private String matricula;

    public Usuario(int numero_usuario, String nombre, String matricula) {
        this.numero_usuario = numero_usuario;
        this.nombre = nombre;
        this.matricula = matricula;
    }

    public int getNumero_usuario() {
        return numero_usuario;
    }

    public void setNumero_usuario(int numero_usuario) {
        this.numero_usuario = numero_usuario;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
 @Override
    public String toString(){
        return numero_usuario+" "+ nombre + " "+ matricula;
    }
}
