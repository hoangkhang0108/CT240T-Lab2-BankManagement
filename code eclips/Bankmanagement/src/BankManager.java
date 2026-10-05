import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankManager {
    private Map<String, BankAccount> accounts;

    public BankManager() {
        this.accounts = new HashMap<>();
    }

    public void addAccount(BankAccount account) {
        if (account == null) {
            System.out.println("❌ Error: Tài khoản không hợp lệ!");
            return;
        }
        if (accounts.containsKey(account.getAccountNumber())) {
            System.out.println("❌ Error: Số tài khoản [" + account.getAccountNumber() + "] đã tồn tại!");
            return;
        }
        accounts.put(account.getAccountNumber(), account);
        System.out.println("✅ Thêm tài khoản thành công!");
    }

    public BankAccount findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void transferMoney(String fromAccNum, String toAccNum, double amount)
            throws InsufficientBalanceException, InvalidAmountException, IllegalArgumentException {
        BankAccount fromAcc = findAccount(fromAccNum);
        BankAccount toAcc = findAccount(toAccNum);

        if (fromAcc == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản nguồn: " + fromAccNum);
        }
        if (toAcc == null) {
            throw new IllegalArgumentException("Không tìm thấy tài khoản đích: " + toAccNum);
        }
        if (fromAccNum.equalsIgnoreCase(toAccNum)) {
            throw new IllegalArgumentException("Tài khoản nguồn và tài khoản đích không được trùng nhau.");
        }

        fromAcc.withdraw(amount);
        try {
            toAcc.deposit(amount);
        } catch (InvalidAmountException e) {
            fromAcc.deposit(amount); 
            throw e;
        }
    }

    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    
    public double calculateTotalBalance(List<? extends BankAccount> accounts) {
        double total = 0.0;
        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }
}