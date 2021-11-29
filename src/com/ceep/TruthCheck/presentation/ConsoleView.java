package com.ceep.TruthCheck.presentation;

import com.ceep.TruthCheck.business.*;
import com.ceep.TruthCheck.data.*;
import com.ceep.TruthCheck.data.txtDatabase.*;
import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.*;
import com.ceep.TruthCheck.exceptions.TXT.*;
import com.ceep.TruthCheck.util.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsoleView {

    private final BusinesManager app;
    private final DatabaseManager data;
    private final DataGetter input;

    private List<GameObject> found;

    private String saveMode = "TXT";

    public ConsoleView() {
        input = new DataGetter();
        DataAccess da = new TXTDataAccess();
        app = new BusinesManager(da);
        data = new DatabaseManager(da);
    }

    public static void main(String[] args) {
        ConsoleView a = new ConsoleView();

        a.mainMenu();
    }
    /**
     * Lanza el menú principal
     */
    public void mainMenu() {
        String prompt = "Bienvenido al gestor de Truth Check\n";

        List<Option> options = new ArrayList<>();

        options.add(new Option("Guardar datos", this::saveData));
        options.add(new Option("Ver datos guardados", this::seeData));
        options.add(new Option("Opciones", this::options));

        ConsoleMenuUtil.menu(true, options, prompt);

    }
    /**
     * Menu de guardado de datos
     */
    public void saveData() {
        String file = input.getText("Introduce el nombre del archivo de guardado");
        if (!data.exists(file)) {
            String crearNuevo = input.getText("El archivo no existe, �Quieres crearlo?");

            if (crearNuevo.toLowerCase().contains("s")) {
                try {
                    data.createDatabase(file);
                } catch (AccessException e) {
                    System.out.println("No se pudo crear");
                    return;
                }
            }
        }
        try {
            data.conectToDatabase(file);
        } catch (ReadException | DatabaseNotFoundException e) {
        }

        String prompt = "Menu de guardado de datos:\n";

        List<Option> options = new ArrayList<>();

        options.add(new Option("Crear personaje", this::createChar));
        options.add(new Option("Crear item", this::createItem));

        ConsoleMenuUtil.menu(true, options, prompt);

    }
    /**
     * Menu de creación de presonajes
     */
    public void createChar() {
        String name = input.getText("Nombre del Personaje: ");
        String description = input.getText("Descripcion de " + name + ": ");

        HashMap<String, GameItem> items;

        try {
            items = data.getItems();
        } catch (ReadException e) {
            System.out.println("No se puede acceder a los objetos");
            return;
        }
        List<GameItem> inventory = input.getItemList("�Que objetos tiene en el inventario?", items);
        List<GameItem> equipment = input.getItemList("�Que objetos tiene equpados?", items);

        try {
            data.addEntry(new GameCharacter(name, description, inventory, equipment));
        } catch (TypeException | NoDatabaseSelectedException | AccessException e) {
            System.out.println("No se pudo crear");
        }

    }
    /**
     * Menu de creaciñon de items
     */
    public void createItem() {
        String name = input.getText("Nombre del Item: ");
        String description = input.getText("Descripcion de " + name + ": ");

        try {
            data.addEntry(new GameItem(name, description));
        } catch (AccessException | TypeException | NoDatabaseSelectedException e) {
            System.out.println("No se pudo crear");
        }
    }
    /**
     * Menú de acceso a datos
     */
    public void seeData() {

        System.out.println("Los archivos de guardado registrados son:");
        String file;
        while (true) {
            try {
                file = input.getStringFromList(data.listDatabases());
            } catch (ReadException e) {
                System.out.println("No se encontraron los archivos");
                return;
            }
            try {
                data.conectToDatabase(file);

                String prompt = "Estas en " + file + ":\n";

                List<Option> options = new ArrayList<>();

                options.add(new Option("Listar todo", this::listAll));
                options.add(new Option("Listar con filtro", this::search));
                options.add(new Option("Listar por campo", this::listByField));

                ConsoleMenuUtil.menu(true, options, prompt);

                return;
            } catch (ReadException | DatabaseNotFoundException e) {
            }
        }

    }
    /**
     * Menú de edición ed datos
     */
    public void menuEdicion() {
        String prompt = "Edición de datos:\n";
        List<Option> options = new ArrayList<>();

        options.add(new Option("Borrar entrada", this::deleteEntry));
        options.add(new Option("Modificar entrada", this::modifyEntry));

        ConsoleMenuUtil.menu(true, options, prompt);
    }
    /**
     * Lista todos los datos
     */
    public void listAll() {
        try {
            found = app.searchEntry("");
            found.forEach(e -> System.out.println(e));
        } catch (ReadException | ObjectCreationException e) {
            System.out.println("Los datos estan dañados");
        }

        menuEdicion();

    }
    /**
     * Lista con filtro inexacto
     */
    public void search() {

        String filter = input.getText("Que filtro quieres usar");
        try {
            found = app.searchEntry(filter);
            found.forEach(e -> System.out.println(e));
        } catch (ReadException | ObjectCreationException e) {
            System.out.println("Los datos estan dañados");
        }
        menuEdicion();
    }
    /**
     * Lista por campo exacto
     */
    public void listByField() {
        GameObjectType type = input.getGameObjectType("¿Que quieres buscar?");
        String filter = input.getText("¿Que filtro quieres?");
        int field = input.getField("¿Que campo quieres?");

        try {
            found = app.searchByField(filter, type, field);
            found.forEach(e -> System.out.println(e));
        } catch (ReadException | ObjectCreationException e) {
            System.out.println("Los datos estan dañados");
        }
        menuEdicion();
    }
    /**
     * Borra un registro, y elimina todas sus referencias
     */
    public void deleteEntry() {
        GameObject borrar = new GameItem("", "");
        if (found.isEmpty()) {
            System.out.println("No se encontró ningún elemento");
            return;
        }
        if (found.size() == 1) {
            borrar = found.get(0);
        }
        if (found.size() > 1) {
            borrar = input.getGameObjectFromList(found, "¿Que elemento quieres borar?");
        }
        String resp = input.getText("¿Quiere borrar \n" + borrar);
        if (resp.toLowerCase().contains(resp.toLowerCase())) {
            try {
                data.deleteEntry(found.get(0));
            } catch (WriteException | ReadException ex) {
                System.out.println("No se pudo borrar");
                ex.printStackTrace();
            }
        }

    }
    /**
     * Modifica un registro y sus referencias
     */
    public void modifyEntry() {
        if (found.isEmpty()) {
            System.out.println("No se encontró ningún elemento");
            return;
        }

    }
    /**
     * Menu de opciones
     */
    public void options() {
        String prompt = "Menu de opciones:\n";

        List<Option> options = new ArrayList<>();

        options.add(new Option("Modo de guardado", this::saveMode));

        ConsoleMenuUtil.menu(true, options, prompt);
    }
    /**
     * Cambio de modo [SQL , TXT]
     */
    public void saveMode() {
        System.out.println("Actualmente el modo de gaurdado es " + saveMode);
        String inp = input.getText("Quieres cambiarlo");
        if (inp.toLowerCase().contains("s")) {
            saveMode = "TXT";
            try {
                app.changeMode();
            } catch (SQLException e) {
                System.out.println("No se pudo cambiar");
            }
            try {
                data.changeMode();
            } catch (SQLException e) {
                System.out.println("No se pudo cambiar");
            }
        }
    }

}
