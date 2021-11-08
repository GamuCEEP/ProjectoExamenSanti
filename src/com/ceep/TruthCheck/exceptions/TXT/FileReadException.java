
package com.ceep.TruthCheck.exceptions.TXT;

import com.ceep.TruthCheck.exceptions.ReadException;

public class FileReadException extends ReadException{

    public FileReadException() {
        super("Error while reading a file");
    }
    public FileReadException(String error){
        super(error);
    }
    
}
