package test;

import com.ceep.TruthCheck.business.BusinesManager;
import com.ceep.TruthCheck.data.txtDatabase.TXTDataAccess;
import com.ceep.TruthCheck.data.Table;
import com.ceep.TruthCheck.data.txtDatabase.DataType;
import com.ceep.TruthCheck.data.txtDatabase.TXTDataBase;
import com.ceep.TruthCheck.domain.GameCharacter;
import com.ceep.TruthCheck.domain.GameItem;
import com.ceep.TruthCheck.domain.GameObjectType;
import com.ceep.TruthCheck.exceptions.DatabaseNotFoundException;
import com.ceep.TruthCheck.exceptions.ObjectCreationException;
import com.ceep.TruthCheck.exceptions.ReadException;
import com.ceep.TruthCheck.exceptions.TXT.FileReadException;
import com.ceep.TruthCheck.exceptions.TXT.NoDatabaseSelectedException;
import com.ceep.TruthCheck.exceptions.TXT.TypeException;
import com.ceep.TruthCheck.presentation.ConsoleView;
import com.ceep.TruthCheck.exceptions.WriteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class test {

	public static void main(String[] args) throws WriteException, ReadException, DatabaseNotFoundException,
			NoDatabaseSelectedException, TypeException, ObjectCreationException {
		ConsoleView a = new ConsoleView();
		
		a.mainMenu();
	}

}
