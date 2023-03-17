package banking;

import java.util.*;


public interface Bank<B> {

	static <B> void createAccount(HashMap<String, B> accList) {

	}static <B> B signInAccount(HashMap<String, B> accList) {
		return accList.get("bank");
	}

//	default public void deleteAccount() {}
	void deleteAccount(HashMap<String, B> asd);

	void signOutAccount();

	void loanInterest();

	void amountBalance();
	
	void amountDeposit();

	void amountWithdraw();

}