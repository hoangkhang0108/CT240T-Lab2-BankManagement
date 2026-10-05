abstract class BankAccount{
	private String accountNumber;
	private String holderName;
	private double balance;
	public BankAccount(){
		super();
	}
	public BankAccount(String accountNumber, String holderName, double balance){
		super();
		this.accountNumber = accountNumber;
		this.holderName = holderName;
		this.balance = balance;
	}
	
	public String getAccountNumber(){
		return accountNumber;
	}
	public String getHolderName(){
		return holderName;
	}
	public double getBalance(){
		return balance;
	}
	
	public void setAccountNumber(String accountNumber){
		this.accountNumber = accountNumber;
	}
	public void setHolderName(String holderName){
		this.holderName = holderName;
	}
	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public abstract void withdraw(double amount)
		throws InsufficientBalanceException, InvalidAmountException;
	public void deposit(double amount) 
		throws InvalidAmountException{
			if (amount<=0) {
				throw new InvalidAmountException("So tien nap phai lon hon 0");
			}
			balance+=amount;
		}
}
