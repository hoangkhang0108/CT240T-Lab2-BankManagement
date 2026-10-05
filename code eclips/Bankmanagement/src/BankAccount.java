
public abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException("Số dư khởi tạo không được nhỏ hơn 0.");
        }
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

 
    
    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Tiền điều kiện vi phạm: Số tiền nạp phải > 0.");
        }
        this.balance += amount;
    }

   
    public abstract void withdraw(double amount)
            throws InsufficientBalanceException, InvalidAmountException;

  
    public String toString() {
        return String.format("STK: %-10s | Chủ TK: %-18s | Số dư: %,12.0f VNĐ", accountNumber, holderName, balance);
    }
}