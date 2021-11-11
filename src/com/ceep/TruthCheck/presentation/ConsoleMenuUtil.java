package com.ceep.TruthCheck.presentation;

import java.util.Scanner;

import com.ceep.TruthCheck.util.Pair;
import com.ceep.TruthCheck.util.Procedure;

public class ConsoleMenuUtil {

	public static void menu(boolean canExit,Pair<String, Procedure>[] options) {
		
		showMenu(canExit, options);
		
		int selectedOption = getOption(options.length, canExit);
		
		if (selectedOption == -1) return;
		
		options[selectedOption].getValue().apply();
	}
	
	public static void showMenu(boolean canExit, Pair<String, Procedure>[] options) {
		int i = 1;
		for(Pair<String, Procedure> option : options) {
			System.out.println(i++ + " - " + option.getKey());
		}
		if (canExit) System.out.println("0 - Salir");
	}
	
	public static int getOption(int size, boolean canExit) {
		Scanner input = new Scanner(System.in);
		int lowerLimit = canExit ? 0 : 1;
		int option = 0;
		while(true) {
			try {
				option = Integer.parseInt(input.nextLine());
				if (option < lowerLimit || option > size) throw new IndexOutOfBoundsException();
				break;
				
			}catch(IndexOutOfBoundsException e) {
				System.out.println("Por favor introduce un número del "+ lowerLimit +" al "+size);}
			catch (NumberFormatException e) {
				System.out.println("Por favor introduce un número");}
		}
		input.close();
		return option-1;
		
	}
}
