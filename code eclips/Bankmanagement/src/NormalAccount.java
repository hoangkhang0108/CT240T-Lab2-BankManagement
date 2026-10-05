/**
 * Lớp đại diện cho Tài khoản Thường (Thanh toán).
 */
public class NormalAccount extends BankAccount {

    public NormalAccount(String accountNumber, String holderName, double balance) throws InvalidAmountException {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Tiền điều kiện vi phạm: Số tiền rút phải > 0.");
        }
        if (getBalance() < amount) {
            throw new InsufficientBalanceException(String.format(
                "Tiền điều kiện vi phạm: Số dư không đủ để rút %,.0f VNĐ. (Số dư hiện tại: %,.0f VNĐ)",
                amount, getBalance()
            ));
        }
        setBalance(getBalance() - amount);
    }

    @Override
    public String toString() {
        return super.toString() + " | [TK Thường]";
    }
}