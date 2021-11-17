

package com.ceep.TruthCheck.exceptions.TXT;


public class TypeSafetyExtension extends Exception{

    public TypeSafetyExtension() {
        super("Type violation");
    }
    public TypeSafetyExtension(String error){
        super(error);
    }
    
}
