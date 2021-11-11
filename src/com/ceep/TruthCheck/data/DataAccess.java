package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {

    private final DataBase database;

    public DataAccess(DataBase database) {
        this.database = database;
    }

    /**
     * Crea e inicializa una base de datos
     *
     * @param storage_name nombre de la base de datos
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void createStorage(String storage_name) throws WriteException {
        database.createStorage(storage_name);
    }

    /**
     * Borra una base de datos
     *
     * @param storage_name nombre de la base de datos
     * @throws com.ceep.TruthCheck.exceptions.AccessException
     */
    public void deleteStorage(String storage_name) throws AccessException {
        database.deleteStorage(storage_name);
    }

    /**
     * Escribe datos en la base de datos
     *
     * @param databaseName nombre de la base de datos
     * @param data datos para guardar
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void writeData(String databaseName, GameObject data) throws WriteException {
        database.writeData(databaseName, data.toDBString(), GameObjectType.valueOf(data.getClass().getSimpleName()));
    }

    /**
     * Elimina la entrada de la base de datos
     *
     * @param storage_name nombre de la base de datos
     * @param data datos que eliminar
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     * @throws com.ceep.TruthCheck.exceptions.ReadException
     */
    public void removeData(String storage_name, GameObject data) throws WriteException, ReadException {
        database.removeData(storage_name, data.toDBString(), GameObjectType.valueOf(data.getClass().getSimpleName()));
    }

    /**
     * Modifica datos en la base de datos.<br><br>
     * Si el tipo de <b>data</b> es distinto de <b>newData</b> lanza un warning
     * pero el programa sigue, los tipos deber�an ser iguales para evitar
     * problemas
     *
     * @param storage_name nombre de la base de datos
     * @param data dato que modificar
     * @param newData dato modificado
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     * @throws com.ceep.TruthCheck.exceptions.ReadException
     */
    public void modifyData(String storage_name, GameObject data, GameObject newData)
            throws WriteException, ReadException {

        if (!data.getClass().getSimpleName().equals(newData.getClass().getSimpleName())) {
            System.err.println("Misuse of datatypes, " + data.getClass().getSimpleName() + " and "
                    + newData.getClass().getSimpleName() + " should be equal, this may cause an error later");
        }

        database.modifyData(storage_name, data.toDBString(), newData.toDBString(),
                GameObjectType.valueOf(data.getClass().getSimpleName()));
    }

    /**
     * Devuelve todos las entradas de un mismo tipo de una base de datos
     *
     * @param storage_name nombre de la base de datos
     * @param datatype tipo de dato de las entradas
     * @return un ArrayList&lt;GameObject&gt; con todas las entradas en forma de
     * GameObject
     * @throws com.ceep.TruthCheck.exceptions.ReadException
     * @throws com.ceep.TruthCheck.exceptions.ObjectCreationException
     */
    public List<GameObject> readData(String storage_name, GameObjectType datatype)
            throws ReadException, ObjectCreationException {
        return parseObjects(database.readData(storage_name, datatype), datatype);
    }

    /**
     * Busca y devuelve el dato que mas se ajuste al texto de busqueda
     *
     * @param storage_name nombre de la base de datos
     * @param search texto de busqueda
     * @param datatype tipo de dato que se va a buscar
     * @return el dato en forma de <b>GameObject</b> que mas se ajuste al texto
     * de busqueda
     * @throws com.ceep.TruthCheck.exceptions.ReadException
     * @throws com.ceep.TruthCheck.exceptions.ObjectCreationException
     */
    public List<GameObject> searchData(String storage_name, String search, GameObjectType datatype)
            throws ReadException, ObjectCreationException {
        return parseObjects(database.searchData(storage_name, search, datatype), datatype);
    }

    /**
     * Recive un objeto en forma de texto y lo convierte al objeto del tipo que
     * sea
     *
     * @param stringifiedObject el objeto en forma de texto
     * @param type el tipo al que se convierte
     * @return el objeto del texto pasado
     * @throws ObjectCreationException
     */
    public GameObject parseObject(String stringifiedObject, GameObjectType type) 
            throws ObjectCreationException {
        GameObject parsedObj = null;
        switch (type) {
            case Character:
                parsedObj = new com.ceep.TruthCheck.domain.Character(stringifiedObject);
                break;
            case Item:
                parsedObj = new Item(stringifiedObject);
                break;
            default:
                throw new ObjectCreationException(type.name() + " type object cannot be created");
        }
        return parsedObj;
    }

    /**
     * Recibe una lista de objetos en forma de texto y lo convierte a una lista
     * de los objetos
     *
     * @param stringifiedObjects lista con objetos en forma de texto
     * @param datatype tipo de objeto que se va a crear
     * @return la lista con los textos convertidos a objeto
     * @throws ObjectCreationException
     */
    public List<GameObject> parseObjects(List<String> stringifiedObjects, GameObjectType datatype) 
            throws ObjectCreationException {
        List<GameObject> parsedObjs = new ArrayList<>();
        for (String stringifiedObject : stringifiedObjects) {
            parsedObjs.add(parseObject(stringifiedObject, datatype));
        }
        return parsedObjs;
    }
}
