package com.ceep.TruthCheck.exceptions;

public class ReadException extends Exception{

    public ReadException() {
        super("Error while reading data");
    }
    
    public ReadException(String error){
        super(error);
    }
}
