package com.ceep.TruthCheck.presentation;

import com.ceep.TruthCheck.business.BusinesManager;
import com.ceep.TruthCheck.data.DataAccess;
import com.ceep.TruthCheck.data.TXTDataBase;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.exceptions.AccessException;
import com.ceep.TruthCheck.exceptions.DatabaseNotFoundException;
import com.ceep.TruthCheck.exceptions.ReadException;
import com.ceep.TruthCheck.exceptions.WriteException;
import com.ceep.TruthCheck.util.ConsoleMenuUtil;
import com.ceep.TruthCheck.util.Option;
import com.ceep.TruthCheck.util.Procedure;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final BusinesManager app;
    private final DataGetter input;
    

    public ConsoleView() {
        input = new DataGetter();
        app = new BusinesManager(new DataAccess(new TXTDataBase()));
    }
    
    /*Menu principal
    Bienvenido al gestor de personajes
    ¿Que queiere hacer?
    
    1 - Crear base de datos
    2 - Listar bases de datos
    3 - Borrar base de datos
    4 - Conectar a base de datos
    */
    public void mainMenu() {
    	String prompt = "Bienvenido al gestor de personajes\n¿Que quieres hacer?";
    	
    	List<Option> options = new ArrayList<Option>();

    	options.add(new Option("Crear base de datos", this::createDatabase));
    	options.add(new Option("Listar bases de datos", this::listDatabases));
    	options.add(new Option("Borrar base de datos", this::deleteDatabase));
    	options.add(new Option("Conectar a base de datos", this::conectToDatabase));
    	
    	ConsoleMenuUtil.menu(true, options, prompt);
    	
    }
    
    public void createDatabase() {
    	while(true) {
    		String database = input.getText("¿Como se va a llamar la nueva base de datos?");
        	try {
    			app.createDatabase(database);
    			return;
    		} catch (WriteException e) {
    			System.out.println("No se puede crear la base de datos llamada "+database+", por favor prueba otro nombre");
    		}
    	}
    }
    public void listDatabases() {
    	try {
    		int i = 1;
			for(String database : app.listDatabases()) {
				System.out.println(i++ + " - "+database);
			}
		} catch (ReadException e) {
			System.out.println("El archivo \"config\" no se ha encontrado o esta dañado");
		}
    	
    }
    public void deleteDatabase() {
    	while(true) {
    		String database = input.getText("¿Que base de datos quieres borrar?");
    		listDatabases();
        	try {
        		app.deleteDatabase(database);
    			return;
    		} catch (AccessException e) {
				System.out.println("No se pudo borrar la base de datos");
			}
    	}
    }
    public void conectToDatabase() {
    	String database = input.getText("¿A que base de datos te quieres conectar?");
    	listDatabases();
    	try {
			app.conectToDatabase(database);
		} catch (ReadException e) {
			System.out.println("El archivo \"config\" no se ha encontrado o esta dañado");
		} catch (DatabaseNotFoundException e) {
			System.out.println("No se encuentra la base de datos, intentalo de nuevo");
		}
    }
    /*Menu de base de datos
    Estas en ${databaseName}
    ¿Que quieres hacer?
    
    1 - Añadir un elemento
    2 - Eliminar un elemento
    3 - Modificar un elemento
    4 - Ver un elemento
    */
    public void databaseMenu() {
    	String prompt = "Estas en "+app.getConectedDatabase() + "\n¿Que quieres hacer?";
    	
    	List<Option> options = new ArrayList<Option>();

    	options.add(new Option("Añadir una entrada", this::addEntry));
    	options.add(new Option("Eliminar una entrada", this::deleteEntry));
    	options.add(new Option("Modificar una entrada", this::modifyEntry));
    	options.add(new Option("Ver una entrada", this::showEntry));
    	
    	ConsoleMenuUtil.menu(true, options, prompt);
    }
    public void addEntry() {
    	GameObjectType type = getElementType("Añadir");
    	app.addEntry(input.getGameObject(type));
    }
    public void deleteEntry() {
    	
    }
    public void modifyEntry() {
    	
    }
    public void showEntry() {
		
	}
    /*Menu seleccion de elemento
    ¿Que elemento quieres añadir?
    1 - ${GameObjectType[0]}
    2 - ${GameObjectType[1]}
    3 - ${GameObjectType[2]}
    .
    .
    .
    */
    public GameObjectType getElementType(String action) {
        	int i = 1;
        	input.listEnum(GameObjectType.values());
        	return input.getGameObjectType("¿Que elemento quieres "+action+"?");
    }
    

}
