
package com.ceep.TruthCheck.exceptions.TXT;

import com.ceep.TruthCheck.exceptions.WriteException;


public class FileWriteException extends WriteException{
    public FileWriteException(){
        super("Error while writing to a file");
    }
    public FileWriteException(String error){
        super(error);
    }
    
}
