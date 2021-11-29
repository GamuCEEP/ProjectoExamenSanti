package com.ceep.TruthCheck.presentation;

import java.util.Scanner;

import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.domain.GameItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Clase para recoger datos del usuario por consola
 * @author Alumno Mañana
 */
public class DataGetter {

    protected final Scanner input;

    public DataGetter() {
        input = new Scanner(System.in);
    }
    /**
     * Recoge texto
     * @param prompt mensaje para el usuario
     * @return respuesta
     */
    public String getText(String prompt) {
        System.out.println(prompt);
        return input.nextLine();
    }
    /**
     * Recoge numeros enteros
     * @param prompt mensaje para el usuario
     * @return respuesta
     */
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
    /**
     * Recoge un numero con decimales
     * @param prompt mensaje para el usuario
     * @return respuesta
     */
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

    public List<GameItem> getItemList(String prompt, HashMap<String, GameItem> posibilities) {
        System.out.println(prompt);
        for (String itemName : posibilities.keySet()) {
            System.out.println(itemName);
        }

        List<GameItem> result = new ArrayList<>();
        while (true) {
            String opt = input.nextLine();
            GameItem item = posibilities.get(opt);
            if (item == null) {
                System.out.println("Objecto desconocido, comprueba que este bien escrito");
                continue;
            }
            result.add(item);
            if (getText("�Quieres elegir otro objeto? si/no").equalsIgnoreCase("si")) {
                continue;
            }
            break;
        }
        return result;
    }

    public GameObject getItem() {
        System.out.println("Bienvenido al creador de objetos");
        String nombre = getText("�Como se llama el objeto");
        String descripcion = getText("Describe el objeto");
        return new GameItem(nombre, descripcion);
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

    public String getStringFromList(List<String> list) {
        for (String s : list) {
            System.out.println(" - " + s);
        }
        return input.nextLine();
    }

    public GameObject getGameObjectFromList(List<GameObject> list, String prompt) {
        int i = 1;
        for (GameObject g : list) {
            System.out.println(i++ + " - " + g);
        }
        while (true) {
            try {
                return list.get(getInt(prompt) - 1);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Introduce un número entre 1 y " + list.size());
            }
        }
    }

    public int getField(String prompt) {
        System.out.println(prompt);
        System.out.println("1 - Nombre");
        System.out.println("2 - Descripcion");

        while (true) {
            int result = getInt("");
            if (result < 3 && result > 0) {
                return result;
            }
        }
    }
}
