
package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.exceptions.*;

import java.util.List;


public interface DataBase {
    /**
     * Crea un recurso para guardar datos
     * @param table_name nombre del recurso de guardado
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void createDatabase(String database_name) 
    		throws WriteException;
    /**
     * Crea una tabla en la base de datos
     * @param table_name
     * @throws WriteException
     */
    public void createTable(String database_name,String table_name)
    		throws WriteException;
    /**
     * Borra un recurso de guardado de datos
     * @param table_name nombre del recurso de guardado
     * @throws com.ceep.TruthCheck.exceptions.AccessException
     */
    public void deleteDatabase(String database_name) 
    		throws AccessException;
    /**
     * Borra una tabla de la base de datos
     * @param table_name
     * @throws WriteException
     */
    public void deleteTable(String database_name,String table_name)
    		throws AccessException;
    /**
     * Escribe datos en un recurso de guardado de datos
     * @param table_name nombre del recurso de guardado
     * @param data datos para guardar
     * @param datatype tipo de dato que se va a escribir
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void writeData(String database_name, String table_name, String data) 
    		throws WriteException;
    /**
     * Quita datos de un recurso de guardado de datos
     * @param table_name nombre del recurso de guardado
     * @param data datos para guardar 
     * @param datatype tipo de dato que se va a borrar
     * @throws com.ceep.TruthCheck.exceptions.WriteException 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public void removeData(String database_name, String table_name, String data) 
    		throws WriteException, ReadException;
    /**
     * Modifica datos en un recurso de guardado de datos
     * @param table_name nombre del recurso de guardado
     * @param data dato que modificar
     * @param newData dato modificado 
     * @param datatype tipo de dato que se va a modificar
     * @throws com.ceep.TruthCheck.exceptions.WriteException 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public void modifyData(String database_name, String table_name, String data, String newData) 
    		throws WriteException, ReadException;
    /**
     * Devuelve todos los datos de un recurso
     * @param table_name nombre del recurso de guardado
     * @param datatype tipo de dato que se va a leer
     * @return un ArrayList&lt;String&gt; con todos los datos 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public List<String> readData(String database_name, String table_name) 
    		throws ReadException;
    /**
     * Busca y devuelve el dato que mas se ajuste al texto de busquedas
     * @param table_name nombre del recurso de guardado
     * @param search texto de busqueda
     * @param datatype tipo de dato que se va a buscar
     * @return el dato que mas se ajuste al texto de busqueda 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public List<String> searchData(String database_name, String table_name, String search) 
    		throws ReadException;
   
}
