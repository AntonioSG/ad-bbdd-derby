/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad.derbybbdd.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */

public class Parking {

    private  HashSet<Coche> listaTotalCoches;
    private  HashSet<Persona> listaTotalPersonas;

    public Parking() {
        listaTotalCoches = new HashSet<>();
        listaTotalPersonas = new HashSet<>();
    }
    
    public void setListaTotalCoches(HashSet<Coche> listaTotalCoches) {
        this.listaTotalCoches = listaTotalCoches;
    }
    public void setListaTotalPersonas(HashSet<Persona> listaTotalPersonas) {
        this.listaTotalPersonas = listaTotalPersonas;
    }
    

    public boolean addCoche(Persona p, Coche c) {
        if (listaTotalPersonas.contains(p) && !listaTotalCoches.contains(c)) {
            p = extraePersona(p);
            listaTotalCoches.add(c);
            p.addCoche(c);
            return true;
        }
        return false;
    }

    public boolean addPersona(Persona p) throws Exception {

        Iterator it = p.getMisCoches().iterator();

        while (it.hasNext()) {
            if(listaTotalCoches.contains((Coche) it.next())){
                throw new Exception("No se puede añadir Persona: "+p.getNif()+" alguno de sus vehiculos ya estan en el parking con otro propietario.");
            }
        }

        it = p.getMisCoches().iterator();

        while (it.hasNext()) {
            listaTotalCoches.add((Coche) it.next());
        }
        return listaTotalPersonas.add(p);
    }

    public boolean delCoche(Persona p, Coche c) {
        if (listaTotalPersonas.contains(p)) {
            p = extraePersona(p);
            p.delCoche(c);
            listaTotalCoches.remove(c);
            return true;
        }
        return false;
    }

    public boolean delPersona(Persona p) {
        if (listaTotalPersonas.contains(p)) {
            for (Coche c : p.getMisCoches()) {
                listaTotalCoches.remove(c);
            }
            return listaTotalPersonas.remove(p);
        } else {
            return false;
        }

    }

    public HashSet<Coche> getListaTotalCoches() {
        return listaTotalCoches;
    }

    public HashSet<Persona> getListaTotalPersonas() {
        return listaTotalPersonas;
    }

    @Override
    public String toString() {
        String mensaje = "";
        Iterator it = listaTotalPersonas.iterator();
        while (it.hasNext()) {
            mensaje += it.next().toString() + "\n";
        }
        return mensaje;
    }

    public Persona extraePersona(Persona p) {

        for (Persona x : listaTotalPersonas) {
            if (p.equals(x)) {
                return x;
            }

        }
        return null;
    }

    public Coche extraeCoche(Coche c) {

        for (Coche x : listaTotalCoches) {
            if (c.equals(x)) {
                return x;
            }

        }
        return null;
    }

    public void modPersona(Persona oldP, Persona newP) {

        newP.setMisCoches(oldP.getMisCoches());
        this.delPersona(oldP);
        try {
            this.addPersona(newP);
        } catch (Exception ex) {
            Logger.getLogger(Parking.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
