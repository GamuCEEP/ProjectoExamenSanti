package com.ceep.TruthCheck.presentation;

import com.ceep.TruthCheck.business.BusinesManager;
import com.ceep.TruthCheck.data.DataAccess;
import com.ceep.TruthCheck.data.TXTDataBase;
import java.util.Scanner;

public class ConsoleView {

    private BusinesManager app;
    private Scanner input;

    public ConsoleView() {
        input = new Scanner(System.in);
        app = new BusinesManager(new DataAccess(new TXTDataBase()));

        //Solo hay que crear los metodos que podr� elegir el usuario
        //y crear un array de pares para pasarselo a ConsoleMenuUtil::menu
    }
    /*Menu principal
    Bienvenido al gestor de personajes
    ¿Qué queiere hacer?
    
    1 - Crear base de datos
    2 - Listar bases de datos
    3 - Borrar base de datos
    4 - Conectar a base de datos
    */
    /*Menu de base de datos
    Estas en ${databaseName}
    ¿Qué quieres hacer?
    
    1 - Añadir un elemento
    2 - Eliminar un elemento
    3 - Modificar un elemento
    4 - Ver un elemento    
    */
    /*Menu seleccion de elemento
    ¿Qué elemento quieres añadir?
    */

}
