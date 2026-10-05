import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class BankManeger{
	Map<String, BankAccount> accounts = new HashMap<>(); 
	public double calculateTotalBalance(List<? extends BankAccount> accounts) {
		double total = 0.0;
		for (BankAccount acc : accounts) {
			total += acc.getBalance(); // Producer Extends
		}
		return total;
	}
	
	public void addAccount(BankAccount acc){
		accounts.put(acc.getAccountNumber(), acc);
	}
	
	public BankAccount findAccount(String accountNumber){
		return accounts.get(accountNumber);
	}
	
	public void transferMoney(String fromAcc, String toAcc, double amount)
		throws InsufficientBalanceException, InvalidAmountException{
		BankAccount from = findAccount(fromAcc);
		BankAccount to = findAccount(toAcc);
		from.withdraw(amount);
		to.deposit(amount);
	}
}