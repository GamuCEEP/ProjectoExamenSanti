
package com.ceep.TruthCheck.data;

import com.ceep.TruthCheck.exceptions.AccessException;
import com.ceep.TruthCheck.exceptions.ReadException;
import com.ceep.TruthCheck.exceptions.WriteException;
import java.util.ArrayList;
import java.util.List;


public interface DataAccess {
    /**
     * Crea un recurso para guardar datos
     * @param storage_name nombre del recurso de guardado
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void createStorage(String storage_name) throws WriteException;
    /**
     * Borra un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @throws com.ceep.TruthCheck.exceptions.AccessException
     */
    public void deleteStorage(String storage_name) throws AccessException;
    /**
     * Escribe datos en un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @param data datos para guardar
     * @throws com.ceep.TruthCheck.exceptions.WriteException
     */
    public void writeData(String storage_name, String data) throws WriteException;
    /**
     * Quita datos de un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @param data datos para guardar 
     * @throws com.ceep.TruthCheck.exceptions.WriteException 
     */
    public void removeData(String storage_name, String data) throws WriteException;
    /**
     * Modifica datos en un recurso de guardado de datos
     * @param storage_name nombre del recurso de guardado
     * @param data dato que modificar
     * @param newData dato modificado 
     * @throws com.ceep.TruthCheck.exceptions.WriteException 
     */
    public void modifyData(String storage_name, String data, String newData) throws WriteException;
    /**
     * Devuelve todos los datos de un recurso
     * @param storage_name nombre del recurso de guardado
     * @return un ArrayList&lt;String&gt; con todos los datos 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public List<String> readData(String storage_name) throws ReadException;
    /**
     * Busca y devuelve el dato que mas se ajuste al texto de busqueda
     * @param storage_name nombre del recurso de guardado
     * @param search texto de busqueda
     * @return el dato que mas se ajuste al texto de busqueda 
     * @throws com.ceep.TruthCheck.exceptions.ReadException 
     */
    public String searchData(String storage_name, String search) throws ReadException;
        
}
