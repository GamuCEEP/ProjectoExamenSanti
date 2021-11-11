
package test;

import com.ceep.TruthCheck.presentation.ConsoleMenuUtil;
import com.ceep.TruthCheck.util.Pair;
import com.ceep.TruthCheck.util.Procedure;

public class test {

    public static void main(String[] args) {
    	
    	Pair<String, Procedure>[] options = new Pair[3];

    	options[0] = new Pair<>("Saludar", test::saludo);
    	options[1] = new Pair<>("Despedir", test::despido);
    	options[2] = new Pair<>("patata", test::patata);
    
    	ConsoleMenuUtil.menu(false, options);
        
    }
    
    public static void saludo() {
    	System.out.println("hola");
    }
    public static void despido() {
    	System.out.println("Adios");
    }
    public static void patata() {
    	System.out.println("patata");
    }
    
}
