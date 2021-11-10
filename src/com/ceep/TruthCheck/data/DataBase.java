
package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.domain.GameObject;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.exceptions.*;
import java.util.List;


public interface DataBase {
    /**
     * Crea un recurso para guardar datos
     * @param storage_name nombre del recurso de guardado
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void createStorage(String storage_name) 
    		throws WriteException;
    /**
     * Borra un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @throws com.ceep.TruthCheck.exceptions.AccessException
     */
    public void deleteStorage(String storage_name) 
    		throws AccessException;
    /**
     * Escribe datos en un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @param data datos para guardar
     * @param datatype tipo de dato que se va a escribir
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void writeData(String storage_name, String data, GameObjectType datatype) 
    		throws WriteException;
    /**
     * Quita datos de un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @param data datos para guardar 
     * @param datatype tipo de dato que se va a borrar
     * @throws com.ceep.TruthCheck.exceptions.WriteException 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public void removeData(String storage_name, String data, GameObjectType datatype) 
    		throws WriteException, ReadException;
    /**
     * Modifica datos en un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @param data dato que modificar
     * @param newData dato modificado 
     * @param datatype tipo de dato que se va a modificar
     * @throws com.ceep.TruthCheck.exceptions.WriteException 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public void modifyData(String storage_name, String data, String newData, GameObjectType datatype) 
    		throws WriteException, ReadException;
    /**
     * Devuelve todos los datos de un recurso
     * @param storage_name nombre del recurso de guardado
     * @param datatype tipo de dato que se va a leer
     * @return un ArrayList&lt;String&gt; con todos los datos 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public List<String> readData(String storage_name, GameObjectType datatype) 
    		throws ReadException;
    /**
     * Busca y devuelve el dato que mas se ajuste al texto de busqueda
     * @param storage_name nombre del recurso de guardado
     * @param search texto de busqueda
     * @param datatype tipo de dato que se va a buscar
     * @return el dato que mas se ajuste al texto de busqueda 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public List<String> searchData(String storage_name, String search, GameObjectType datatype) 
    		throws ReadException;
    /**
     * Recive un objeto en forma de texto y lo convierte al objeto del tipo que sea
     * @param stringifiedObject el objeto en forma de texto
     * @param datatype tipo de objeto que se va a crear
     * @return el objeto del texto pasado
     * @throws com.ceep.TruthCheck.exceptions.ObjectCreationException
     */
    public GameObject parseObject(String stringifiedObject, GameObjectType datatype) 
    		throws ObjectCreationException;
    /**
     * Recibe una lista de objetos en forma de texto y lo convierte a una lista de los objetos
     * @param stringifiedObjects lista con objetos en forma de texto
     * @param datatype tipo de objeto que se va a crear
     * @return la lista con los textos convertidos a objeto
     * @throws ObjectCreationException 
     */
    public List<GameObject> parseObjects(List<String> stringifiedObjects, GameObjectType datatype) 
    		throws ObjectCreationException;
}
