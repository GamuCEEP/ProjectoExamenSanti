/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Alumno Mañana
 */
public class test {

    public static void main(String[] args) {
        try {
            String textofecha = "21/04/2021";
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha = df.parse(textofecha);
            
            System.out.println(fecha);
        } catch (ParseException ex) {
        }

    }
}
