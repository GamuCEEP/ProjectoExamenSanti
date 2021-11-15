package com.ceep.TruthCheck.presentation;

import java.util.Scanner;

import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.domain.Item;
import java.util.List;

public class DataGetter {

    protected final Scanner input;

    public DataGetter() {
        input = new Scanner(System.in);
    }

    public String getText(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }

    public int getInt(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                int result = Integer.parseInt(input.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un numero entero");
            }
        }
    }

    public float getFloat(String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                float result = Float.parseFloat(input.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Por favor introduce un numero valido, usa \".\" para separar los decimales");
            }
        }
    }

    public GameObject getCharacter(List<Item> items) {
        System.out.println("Bienvenido al creador de personajes");
        String nombre = getText("�Como se llama el personaje?");
        String descripcion = getText("Describe al personaje");
        List<Item> inventory = getItems("¿Que lleva en el inventario?", items);
        List<Item> equipment = getItems("¿Que lleva equipado?", items);
        
        return new com.ceep.TruthCheck.domain.Character(nombre, descripcion, inventory, equipment);
    }

    public List<Item> getItems(String prompt, List<Item>) {
        
    }

    public GameObject getItem() {
        System.out.println("Bienvenido al creador de objetos");
        String nombre = getText("�Como se llama el objeto");
        String descripcion = getText("Describe el objeto");
        return new Item(nombre, descripcion);
    }

    public GameObjectType getGameObjectType(String prompt) {
        while (true) {
            System.out.println(prompt);
            listEnum(GameObjectType.values());
            try {
                GameObjectType result = GameObjectType.valueOf(input.nextLine());
                return result;
            } catch (IllegalArgumentException e) {
                System.out.println("Por favor introduce tipo valido");
            }
        }
    }

    public void listEnum(Enum<?>[] enumeration) {
        for (Enum<?> a : enumeration) {
            System.out.println(" - " + a);
        }
    }
}
