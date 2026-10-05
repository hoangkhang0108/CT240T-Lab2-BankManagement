public class SavingAccount extends BankAccount{
	private double interestRate;
	public SavingAccount(){
		super();
	}
	public SavingAccount(String accountNumber, String holderName, double balance, double interestRate){
		super(accountNumber, holderName, balance);
		if (balance < 50000) {
			throw new IllegalArgumentException("So du toi thieu phai lon hon 50,000 VND");
		}
		this.interestRate = interestRate;
	}
	public double getInterestRate(){
		return interestRate;
	}
	public void setInterestRate(double interestRate){
		this.interestRate = interestRate;
	}
	
	public void withdraw(double amount) 
		throws InsufficientBalanceException, InvalidAmountException{
			if (amount<=0) {
				throw new InvalidAmountException("So tien rut phai lon hon 0");
			}
			if (getBalance()-amount < 50000) {
				throw new InsufficientBalanceException("Tai khoan khong du de rut");
			}
			
			setBalance(getBalance() - amount);
		}
}