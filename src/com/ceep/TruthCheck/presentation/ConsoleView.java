package com.ceep.TruthCheck.presentation;

import com.ceep.TruthCheck.business.BusinesManager;
import com.ceep.TruthCheck.data.DataAccess;
import com.ceep.TruthCheck.data.TXTDataBase;
import com.ceep.TruthCheck.util.Pair;
import com.ceep.TruthCheck.util.Procedure;

import java.util.Scanner;

public class ConsoleView {

    private final BusinesManager app;
    private final Scanner input;

    public ConsoleView() {
        input = new Scanner(System.in);
        app = new BusinesManager(new DataAccess(new TXTDataBase()));

        //Solo hay que crear los metodos que puede elegir el usuario
        //y crear un array de pares para pasarselo a ConsoleMenuUtil::menu
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
    	
    	Pair<String, Procedure>[] options = new Pair[3];

    	options[0] = new Pair<>("Crear base de datos", app::saludo);
    	options[1] = new Pair<>("Listar bases de datos", app::despido);
    	options[2] = new Pair<>("Borrar base de datos", app::patata);
    	options[3] = new Pair<>("Conectar a base de datos", app::patata);
    	
    }
    /*Menu de base de datos
    Estas en ${databaseName}
    ¿Que quieres hacer?
    
    1 - Añadir un elemento
    2 - Eliminar un elemento
    3 - Modificar un elemento
    4 - Ver un elemento
    */
    /*Menu seleccion de elemento
    ¿Que elemento quieres añadir?
    1 - ${GameObjectType[0]}
    2 - ${GameObjectType[1]}
    3 - ${GameObjectType[2]}
    .
    .
    .
    */
    

}
