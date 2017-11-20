/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad.derbybbdd.test;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author asillero
 */

public class Persona implements Serializable {

    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;
    private HashSet<Coche> misCoches;

    public Persona(String nif, String nombre, String apellido1, String apellido2, Date fechaNacimiento) {
        if (nif == null) {
            throw new IllegalStateException("NIF no puede ser nulo");
        }
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.misCoches = new HashSet<>();

    }

    public Persona() {
    }

    public boolean addCoche(Coche c) {
        return misCoches.add(c);
    }

    public boolean delCoche(Coche c) {
        return misCoches.remove(c);
    }

 
    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        if (nif == null) {
            throw new IllegalStateException("NIF no puede ser null");
        }
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(fechaNacimiento);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public HashSet<Coche> getMisCoches() {
        return misCoches;
    }

    public void setMisCoches(HashSet<Coche> misCoches) {
        this.misCoches = misCoches;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.nif);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return Objects.equals(this.nif, other.nif);
    }

    @Override
    public String toString() {
        String listadoCoches = "";
        Iterator it = misCoches.iterator();
        while (it.hasNext()) {
            listadoCoches += it.next().toString() + "\n";
        }
        return nif + " " + nombre + " " + apellido1 + " " + apellido2 + " " + fechaNacimiento + " Coches:\n" + listadoCoches;
    }

}
