package celonis;

import design.*;
import java.util.*;


public class CelonisMain {


	public static void showMenu() {

		List<String> stdin = 
				Design.printBox(0,
					new DLabel("", "<top>", ""),
					new DLabel("Celonis Ltd.", "<tag>", "Y"),
					new DLabel("", "<joint>", ""),
					new DLabel(" *1*  Hire Employee", "", "C"),
					new DLabel(" *2*  Fire Employee", "", "C"),
					new DLabel(" *3*  Search Employee", "", "C"),
					new DLabel(" *4*  Update Employee", "", "C"),
					new DLabel(" *5*  Display Salary Slip", "", "C"),
//					new DLabel(" *6*  Display Employee", "", "C"),
					new DLabel(" *0*  Close Celonis", "", "C"),
					new DLabel("", "<joint>", ""),
					new DLabel("Enter Choice:  ", "<input>", "P"),
					new DLabel("", "<base>", "")
				);
		Cache.IntegerChoice = Integer.parseInt(stdin.get(0).trim());
	}


	public static void run() {
		CelonisRun();
	}


	public static void CelonisRun() {

		while (true) {

			try {

				Cmd.clearScreen();
				if (Cache.errorFlag)
					Cmd.showErrorMessage();

				showMenu();
				Cmd.loadingProcess(1500);

				switch (Cache.IntegerChoice) {

				case 1 :
//					AxisMain.run();
					break;

				case 2 :
//					BarodaMain.run();
					break;

				case 3 :
//					AxisMain.run();
					break;

				case 4 :
//					BarodaMain.run();
					break;

				case 5 :
//					AxisMain.run();
					break;

				case 6 :
//					BarodaMain.run();
					break;

				case 0 :
					return;

				default :
					throw new NumberFormatException("<INVALID CHOICE!>\nChoices are: 0/1/2/3/4/5/6");

				}

			}catch (NumberFormatException e) {
				Cmd.loadingProcess(600);
				if (!e.getMessage().startsWith("<"))
					Cache.errorMsg = "<INVALID INPUT!>\nChoice must be 0/1/2/3/4/5/6";
				else
					Cache.errorMsg = e.getMessage();
				Cache.errorFlag = true;

			}catch(Exception e) {
				Cache.errorMsg = "<HEY!>\n[B0$$.";	Cache.errorFlag = true;
				e.printStackTrace();
				Cache.scan.nextLine();

			}
		}
	}



	/**
	 * @Info Test Runs */
	public static void main(String[] args) {
		CelonisMain.run();
	}
}

