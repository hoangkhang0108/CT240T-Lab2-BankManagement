
public class SavingAccount extends BankAccount {
    public static final double MIN_BALANCE = 50000.0;
    private double interestRate;

    public SavingAccount(String accountNumber, String holderName, double balance, double interestRate)
            throws InvalidAmountException, InsufficientBalanceException {
        super(accountNumber, holderName, balance);
        if (balance < MIN_BALANCE) {
            throw new InsufficientBalanceException("Vi phạm bất biến: Số dư khởi tạo của TK tiết kiệm phải >= 50,000 VNĐ.");
        }
        if (interestRate < 0) {
            throw new InvalidAmountException("Lãi suất không được nhỏ hơn 0.");
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Tiền điều kiện vi phạm: Số tiền rút phải > 0.");
        }
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException(String.format(
                "Vi phạm bất biến: Số dư còn lại sau khi rút không được nhỏ hơn %,.0f VNĐ. (Số dư hiện tại: %,.0f VNĐ)",
                MIN_BALANCE, getBalance()
            ));
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Lãi suất: %.2f%% [TK Tiết Kiệm]", interestRate);
    }
}