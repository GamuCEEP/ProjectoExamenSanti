package com.ceep.TruthCheck.data.txtDatabase;

import com.ceep.TruthCheck.data.DataBase;
import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    private final DataBase database;
    private String selectedDatabase;

    public DataAccess(DataBase database) {
        this.database = database;
        try {
            database.createTable("", "config");
        } catch (WriteException e) {
            System.out.println("config file could not e created");
        }
    }

    public String getSelectedDatabase() {
        return selectedDatabase;
    }

    /**
     * Devuelve una lista con todas las bases de datos cargadas
     *
     * @return
     * @throws ReadException
     */
    public List<String> listDatabases() throws ReadException {

        List<String> databases = database.readData("", "config");
        List<String> databaseNames = new ArrayList<>();
        for (String stringifiedDatabase : databases) {
            databaseNames.add(stringifiedDatabase.split(Storable.DATABASE_DEFINITION)[0]);
        }

        return databaseNames;
    }

    /**
     * Selecciona una base de datos para su uso continuo
     *
     * @param databaseName
     * @throws ReadException
     * @throws DatabaseNotFoundException
     */
    public void selectDatabase(String databaseName) throws ReadException, DatabaseNotFoundException {
        boolean exists = false;
        for (String line : database.readData("", "config")) {
            exists |= line.contains(databaseName);
        }
        
        if (!exists) {
            throw new DatabaseNotFoundException("Could not find database " + databaseName);
        }
        selectedDatabase = databaseName;
    }

    /**
     * A�ade la base de datos dada con sus tablas al registro de bases de datos
     *
     * @param databaseName base de datos que se va a a�adir
     * @param tables
     * @throws WriteException
     * @throws DatabaseNotFoundException
     */
    public void addDatabase(String databaseName, Table... tables)
            throws WriteException, DatabaseNotFoundException {
        StringBuilder databaseInfo = new StringBuilder();

        databaseInfo.append(databaseName).append(Storable.DATABASE_DEFINITION);
        for (Table table : tables) {
            try {
                database.readData(databaseName, table.getTableName());
                databaseInfo.append(table.toDBString()).append(Storable.TABLE_SEPARATOR);

            } catch (ReadException e) {
                throw new DatabaseNotFoundException("Could not find table " + table.getTableName());
            }
        }
        database.writeData("", "config", databaseInfo.toString());
    }

    /**
     * Crea e inicializa una base de datos
     *
     * @param databaseName nombre de la base de datos
     * @param tables un array de tablas de la base de datos
     * @throws WriteException
     */
    public void createDatabase(String databaseName, Table... tables) throws WriteException {
        StringBuilder databaseInfo = new StringBuilder();

        database.createDatabase(databaseName);
        databaseInfo.append(databaseName).append(Storable.DATABASE_DEFINITION);
        for (Table table : tables) {
            database.createTable(databaseName, table.getTableName());
            databaseInfo.append(table.getTableName()).append(Storable.TABLE_SEPARATOR);
        }
        database.writeData("", "config", databaseInfo.toString());

    }

    /**
     * Borra una base de datos
     *
     * @param databaseName nombre de la base de datos
     * @throws AccessException
     */
    public void deleteDatabase(String databaseName) throws AccessException {
        database.deleteDatabase(databaseName);
    }

    /**
     * Borra la base de datos seleccionada
     *
     * @throws AccessException
     */
    public void deleteDatabase() throws AccessException {
        database.deleteDatabase(selectedDatabase);
    }

    /**
     * Escribe datos en la base de datos
     *
     * @param databaseName nombre de la base de datos
     * @param data datos para guardar
     * @throws WriteException
     */
    public void writeData(String databaseName, GameObject data) throws WriteException {
        database.writeData(databaseName, data.getClass().getSimpleName(), DBConversor.toDBString(data));
    }

    /**
     * Escribe en la base de datos seleccionada
     *
     * @param data datos para guardar
     * @throws WriteException
     */
    public void writeData(GameObject data) throws WriteException {
        database.writeData(selectedDatabase, data.getClass().getSimpleName(), DBConversor.toDBString(data));
    }

    /**
     * Elimina la entrada de la base de datos
     *
     * @param databaseName nombre de la base de datos
     * @param data datos que eliminar
     * @throws WriteException
     * @throws ReadException
     */
    public void removeData(String databaseName, GameObject data) throws WriteException, ReadException {
        database.removeData(databaseName, data.getClass().getSimpleName(), DBConversor.toDBString(data));
    }

    /**
     * Borra datos de la base de datos seleccionada
     *
     * @param data datos que eliminar
     * @throws WriteException
     * @throws ReadException
     */
    public void removeData(GameObject data) throws WriteException, ReadException {
        database.removeData(selectedDatabase, data.getClass().getSimpleName(), DBConversor.toDBString(data));
    }

    /**
     * Modifica datos en la base de datos.<br>
     * <br>
     * Si el tipo de <b>data</b> es distinto de <b>newData</b> lanza un warning
     * pero el programa sigue, los tipos deber�an ser iguales para evitar
     * problemas
     *
     * @param databaseName nombre de la base de datos
     * @param data dato que modificar
     * @param newData dato modificado
     * @throws WriteException
     * @throws ReadException
     */
    public void modifyData(String databaseName, GameObject data, GameObject newData)
            throws WriteException, ReadException {
        database.modifyData(databaseName, data.getClass().getSimpleName(),
                DBConversor.toDBString(data), DBConversor.toDBString(data));
    }

    /**
     * Modifica datos en la base de datos seleccionada
     *
     * @param data dato que modificar
     * @param newData dato modificado
     * @throws WriteException
     * @throws ReadException
     */
    public void modifyData(GameObject data, GameObject newData) throws WriteException, ReadException {
        database.modifyData(selectedDatabase, data.getClass().getSimpleName(),
                DBConversor.toDBString(data), DBConversor.toDBString(data));
    }

    /**
     * Devuelve todos las entradas de un mismo tipo de una base de datos
     *
     * @param databaseName nombre de la base de datos
     * @param datatype tipo de dato de las entradas
     * @return un ArrayList&lt;GameObject&gt; con todas las entradas en forma de
     * GameObject
     * @throws ReadException
     * @throws ObjectCreationException
     */
    public List<GameObject> readData(String databaseName, GameObjectType datatype)
            throws ReadException, ObjectCreationException {
        List<String> stringifiedGameObjects = database.readData(databaseName, datatype.name());
        return DBConversor.gameObjectsFromDBString(stringifiedGameObjects, datatype);
    }

    /**
     * Devuelve todos las entradas de un mismo tipo de la base de datos
     * seleccionada
     *
     * @param datatype tipo de dato de las entradas
     * @return un ArrayList&lt;GameObject&gt; con todas las entradas en forma de
     * GameObject
     * @throws ReadException
     * @throws ObjectCreationException
     */
    public List<GameObject> readData(GameObjectType datatype) throws ReadException, ObjectCreationException {
        List<String> stringifiedGameObjects = database.readData(selectedDatabase, datatype.name());
        return DBConversor.gameObjectsFromDBString(stringifiedGameObjects, datatype);
    }

    /**
     * Busca y devuelve el dato que mas se ajuste al texto de busqueda
     *
     * @param databaseName nombre de la base de datos
     * @param search texto de busqueda
     * @param datatype tipo de dato que se va a buscar
     * @return el dato en forma de <b>GameObject</b> que mas se ajuste al texto
     * de busqueda
     * @throws ReadException
     * @throws ObjectCreationException
     */
    public List<GameObject> searchData(String databaseName, String search, GameObjectType datatype)
            throws ReadException, ObjectCreationException {
        List<String> stringifiedGameObjects = database.searchData(databaseName, datatype.name(), search);
        return DBConversor.gameObjectsFromDBString(stringifiedGameObjects, datatype);
    }

    /**
     * Busca y devuelve el dato que mas se ajuste al texto de busqueda en la
     * base de datos seleccionada
     *
     * @param search texto de busqueda
     * @param datatype tipo de dato que se va a buscar
     * @return el dato en forma de <b>GameObject</b> que mas se ajuste al texto
     * de busqueda
     * @throws ReadException
     * @throws ObjectCreationException
     */
    public List<GameObject> searchData(String search, GameObjectType datatype)
            throws ReadException, ObjectCreationException {
        List<String> stringifiedGameObjects = database.searchData(selectedDatabase, datatype.name(), search);
        return DBConversor.gameObjectsFromDBString(stringifiedGameObjects, datatype);
    }
}
