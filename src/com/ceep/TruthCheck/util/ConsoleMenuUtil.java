package com.ceep.TruthCheck.util;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenuUtil {

	public static void menu(boolean canExit, List<Option> options, String prompt) {

		Scanner input = new Scanner(System.in);

		while (true) {
			showMenu(canExit, options, prompt);

			int selectedOption = getOption(options.size(), canExit, input);

			if (selectedOption == -1)
				return;

			options.get(selectedOption).getValue().apply();
		}

	}

	public static void showMenu(boolean canExit, List<Option> options, String prompt) {
		System.out.println(prompt);
		int i = 1;
		for (Option option : options) {
			System.out.println(i++ + " - " + option.getKey());
		}
		if (canExit)
			System.out.println("0 - Salir");
	}

	public static int getOption(int size, boolean canExit, Scanner input) {
		int lowerLimit = canExit ? 0 : 1;
		int option = 0;
		while (true) {
			try {
				option = Integer.parseInt(input.nextLine());
				if (option < lowerLimit || option > size)
					throw new IndexOutOfBoundsException();
				break;

			} catch (IndexOutOfBoundsException e) {
				System.out.println("Por favor introduce un numero del " + lowerLimit + " al " + size);
			} catch (NumberFormatException e) {
				System.out.println("Por favor introduce un numero");
			}
		}
		return option - 1;

	}
}
