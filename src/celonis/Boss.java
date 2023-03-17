package celonis;

import java.util.*;

public class Boss {

	/** @info Boss Fields */
	private String username, password;
	private long phno;
	HashMap<Integer, Employee> team;

	/** @info Constructor */
	public Boss(String username, long phno, String password) {
		this.username = username;
		this.phno = phno;
		this.password = password;
		team = new HashMap<Integer, Employee>();
	}

	/** @info Basic Set/Get Methods */
	public void setUsername(String username) {
		this.username = username;

	}public void setPassword(String password) {
		this.password = password;

	}public void setPhoneNumber(long phno) {
		this.phno = phno;

	}public String getUsername() {
		return this.username;

	}public String getPassword() {
		return this.password;

	}public long getPhoneNumber() {
		return phno;

	}

	/** @info Boss Operations */

	/** @info Hire Employees */
	public void hireEmployee() {
		
	}


	/** @info Fire Employees */
	public void fireEmployee() {
		
	}


	/** @info Investigate an Employee */
	public void exploreEmployee() {
		
	}

	/** @info Update Employee Details */
	public void updateEmployee() {
		
	}


	/** @info Company's Salary-Slip */
	public void salarySlip() {
		
	}

}



