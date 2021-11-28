package com.ceep.TruthCheck.data.txtDatabase;
/**
 * Una interfaz para marcar los objetos que se pueden guardar en txt
 * y guardar los separadores que se usan en el proceso
 * @author GamuD
 *
 */
public interface Storable {
	/**
	 * Separa campos de objetos
	 * id;nombre;
	 */
    public static final String FIELD_SEPARATOR = ";";
    /**
     * Separa elementos en listas
     * elem1,elem2,
     */
    public static final String LIST_SEPARATOR = ",";
    /**
     * Separa pares clave valor
     * perro·ladra
     */
    public static final String PAIR_SEPARATOR = "·";
}
