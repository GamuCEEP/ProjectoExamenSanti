package com.ceep.TruthCheck.presentation;

import com.ceep.TruthCheck.business.BusinesManager;

public class ConsoleView {

	private BusinesManager app;
	
	public ConsoleView() {
		
		app = new BusinesManager();
		
		//Solo hay que crear los metodos que podrá elegir el usuario
		//y crear un array de pares para pasarselo a ConsoleMenuUtil::menu
	}
	
	
}
