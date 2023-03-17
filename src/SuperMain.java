
import design.*;
import java.util.*;

import calci.CalciMain;
//import celonis.EmpMain;
//import banking.BankMain;
//import bescom.BescomMain;


public class SuperMain {


	public static void showMenu() {

		List<String> stdin = 
			Design.printBox(0,
				new DLabel("", "<top>", ""),
				new DLabel("Program Simulator", "<tag>", "Y"),
				new DLabel("", "<joint>", ""),
				new DLabel(" *1*  Casio fx-707", "", "C"),
				new DLabel(" *2*  Celonis", "", "C"),
				new DLabel(" *3*  BESCOM", "", "C"),
				new DLabel(" *4*  Cash Ware", "", "C"),
				new DLabel(" *0*  Close Program Simulator", "", "C"),
				new DLabel("", "<joint>", ""),
				new DLabel("Enter Choice:  ", "<input>", "P"),
				new DLabel("", "<base>", "")
			);

		Cache.IntegerChoice = Integer.parseInt(stdin.get(0).trim());
	}


	public static void run() {
		return;
	}


	public final static void main(String[] args) {

		while (true) {

			Cmd.clearScreen();
			if (Cache.errorFlag)
				Cmd.showErrorMessage();

			try {

				showMenu();
				Cmd.loadingProcess(1500);

				switch (Cache.IntegerChoice) {

					case 1 :
						CalciMain.run();
						break;

//					case 2 :
//						EmpMain.run();
//						break;
//
//					case 3 :
//						BescomMain.run();
//						break;
//
//					case 4 :
//						BankMain.run();
//						break;

					case 0 :
						Cache.scan.close();
						Cmd.clearScreen();
						System.exit(0);

					default :
						throw new NumberFormatException("<INVALID CHOICE!>\nChoices are: 0/1/2/3/4");
				}

			}catch (NumberFormatException e) {
				Cmd.loadingProcess(600);
				if (!e.getMessage().startsWith("<"))
					Cache.errorMsg = "<INVALID INPUT!>\nChoice must be 0/1/2/3/4";
				else
					Cache.errorMsg = e.getMessage();
				Cache.errorFlag = true;

			}catch (Exception e) {
				e.printStackTrace();
				Cache.errorMsg = "<HEY!>\n[B0$$!";
				Cache.errorFlag = true;
			}
		}
	}

}



