
package test;

import com.ceep.TruthCheck.data.*;
import com.ceep.TruthCheck.domain.*;
import com.ceep.TruthCheck.exceptions.*;

import java.util.ArrayList;
import java.util.List;

import com.ceep.TruthCheck.business.*;
import com.ceep.TruthCheck.exceptions.TXT.*;
import com.ceep.TruthCheck.presentation.*;
import com.ceep.TruthCheck.util.*;

public class test {

	public static void main(String[] args) {

		verdura a = new patata();
                
                printer.print(a);
	}
}
class printer{
    public static void print(verdura v){
     
    }
    private static void _print(patata v){
        System.out.println("Patataaaaa");
    }
    private static void _print(pepino v){
        System.out.println("Pepinooooo");
    }
    
}
class patata extends verdura{
    
}
class pepino extends verdura{
    
}
class verdura{
    
}
