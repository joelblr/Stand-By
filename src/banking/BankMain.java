package banking;

import java.util.List;
import banking.axisBank.*;
import banking.barodaBank.*;
import design.*;


public class BankMain {


	public static void showMenu() {

		List<String> stdin = 
				Design.printBox(0,
					new DLabel("", "<top>", ""),
					new DLabel("Banks of India", "<tag>", "Y"),
					new DLabel("", "<joint>", ""),
					new DLabel(" *1*  Axis Bank", "", "C"),
					new DLabel(" *2*  Baroda Bank", "", "C"),
					new DLabel(" *0*  Close Banking", "", "C"),
					new DLabel("", "<joint>", ""),
					new DLabel("Enter Choice:  ", "<input>", "P"),
					new DLabel("", "<base>", "")
				);
		Cache.IntegerChoice = Integer.parseInt(stdin.get(0).trim());
	}


	public static void run() {
		BankRun();
	}


	public static void BankRun() {

		while (true) {

			try {

				Cmd.clearScreen();
				if (Cache.errorFlag)
					Cmd.showErrorMessage();

				showMenu();
				Cmd.loadingProcess(1500);

				switch (Cache.IntegerChoice) {

					case 1 :
						AxisMain.run();
						break;
	
					case 2 :
						BarodaMain.run();
						break;

					case 0 :
						return;

					default :
						throw new NumberFormatException("<INVALID CHOICE!>\nChoices are: 0/1/2");

				}

			}catch (NumberFormatException e) {
				Cmd.loadingProcess(600);
				if (!e.getMessage().startsWith("<"))
					Cache.errorMsg = "<INVALID INPUT!>\nChoice must be 0/1/2";
				else
					Cache.errorMsg = e.getMessage();
				Cache.errorFlag = true;

			}catch(Exception e) {
				Cache.errorMsg = "<HEY!>\nOops.";	Cache.errorFlag = true;
				e.printStackTrace();
				Cache.scan.nextLine();

			}
		}
	}



	/**
	 * @Info Test Runs */
	public static void main(String[] args) {
		BankMain.run();
	}
}

