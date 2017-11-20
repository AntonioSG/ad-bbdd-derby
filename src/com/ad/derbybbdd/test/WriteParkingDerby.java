/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad.derbybbdd.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class WriteParkingDerby {

    private static Connection conn = null;
    private static Statement stmt = null;
    private static String dbURL = "jdbc:derby:parking;create=true";

    public static void main(String[] args) {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            Logger.getLogger(WriteParkingDerby.class.getName()).log(Level.SEVERE, null, ex);
        }

        Calendar cal = Calendar.getInstance();

        cal.set(
                2005, 2, 28);
        Coche c1 = new Coche("1234cfd", "Coche", "uno", cal.getTime(), "Azul");

        cal.set(
                1980, 10, 15);
        Persona p1 = new Persona("98675872K", "Persona", "Uno", "López", cal.getTime());

        store(p1);
        store(p1, c1);

        Persona p2 = new Persona("11111111K", "Persona", "dos", "11111", cal.getTime());
        store(p2);
        Coche c2 = new Coche("11111ckk", "coche", "dos", cal.getTime(), "1111");
        Coche c3 = new Coche("11112ckk", "coche", "repetido", cal.getTime(), "1111");
        store(p2, c2);
        store(p2, c3);

        Persona p3 = new Persona("22222222K", "Persona", "tres", "11111", cal.getTime());
        store(p3);
        Coche c4 = new Coche("44444ckk", "coche", "cuatro", cal.getTime(), "1111");
        Coche c5 = new Coche("55555ckk", "coche", "cinco", cal.getTime(), "1111");
        Coche c6 = new Coche("66666ckk", "coche", "seis", cal.getTime(), "1111");
        store(p3, c4);
        store(p3, c5);
        store(p3, c6);

        System.out.println(
                "Listado Personas: \n");
        selectPersonas();
        System.out.println(
                "Listado coches: \n");

        System.out.println(
                "---------------------------------------------------------------------");
        shutdown();

    }

    private static void store(Persona p1) {
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO APP.PERSONAS (NIF, NOMBRE, APELLIDO1, APELLIDO2, FECHANACIMIENTO) "
                    + "	VALUES ('" + p1.getNif() + "', '" + p1.getNombre() + "', '" + p1.getApellido1() + "', '" + p1.getApellido2() + "', '" + p1.getFechaString() + "')");

            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void store(Persona p1, Coche c1) {
        try {
            stmt = conn.createStatement();
            stmt.execute("INSERT INTO APP.COCHES (MATRICULA, MARCA, MODELO, COLOR, PROPIETARIO, FECHAMATRICULA) "
                    + "	VALUES ('" + c1.getMatricula() + "', '" + c1.getMarca() + "', '" + c1.getModelo() + "', '" + c1.getColor() + "', '" + p1.getNif() + "', '" + c1.getFechaString() + "')");

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    private static void selectPersonas() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from personas");
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                //print Column Names
                System.out.print(rsmd.getColumnLabel(i) + "\t\t");
            }

            System.out.println("\n-------------------------------------------------");

            while (results.next()) {
                String nif = results.getString(1);
                String nombre = results.getString(2);
                String apellido1 = results.getString(3);
                String apellido2 = results.getString(4);
                Date fechaNacimiento = results.getDate(5);
                System.out.println(nif + "\t" + nombre + "\t" + apellido1 + "\t" + apellido2 + "\t" + fechaNacimiento);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        
        
        
        
        
    }

    private static void shutdown() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        } catch (SQLException sqlExcept) {

        }

    }
}
