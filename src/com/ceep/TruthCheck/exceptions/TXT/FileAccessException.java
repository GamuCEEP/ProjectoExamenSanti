
package com.ceep.TruthCheck.exceptions.TXT;

import com.ceep.TruthCheck.exceptions.AccessException;

public class FileAccessException extends AccessException{

    public FileAccessException() {
        super("Error while accessing a file");
    }
    public FileAccessException(String error){
        super(error);
    }
    
}
