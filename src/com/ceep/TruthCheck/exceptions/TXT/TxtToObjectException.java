
package com.ceep.TruthCheck.exceptions.TXT;

import com.ceep.TruthCheck.exceptions.ObjectCreationException;

public class TxtToObjectException extends ObjectCreationException {

    public TxtToObjectException() {
        super("Error while trying to convert from text to object");
    }
    public TxtToObjectException(String error){
        super(error);
    }
    
}
