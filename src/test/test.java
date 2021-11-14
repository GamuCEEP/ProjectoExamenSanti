
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

		DataGetter a = new DataGetter();
		
		System.out.println(a.getGameObjectType("¿Que tipo de objeto?"));
	}
}
