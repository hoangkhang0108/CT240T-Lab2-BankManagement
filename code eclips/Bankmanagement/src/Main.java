import java.util.Scanner;

public class Main {
    private static BankManager bankManager = new BankManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = -1;
        do {
            showMenu();
            try {
                System.out.print("Chọn chức năng (1-7): ");
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println("--------------------------------------------------");
                switch (choice) {
                    case 1 -> handleAddAccount();
                    case 2 -> handleDeposit();
                    case 3 -> handleWithdraw();
                    case 4 -> handleTransfer();
                    case 5 -> handleFindAccount();
                    case 6 -> handleDisplayAllAndTotal();
                    case 0 -> System.out.println("Đã thoát hệ thống. Tạm biệt!");
                    default -> System.out.println("❌ Tùy chọn không hợp lệ, vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập số nguyên hợp lệ!");
            } catch (Exception e) {
                System.out.println("❌ Lỗi hệ thống: " + e.getMessage());
            }
            System.out.println();
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("================ BANCS MANAGEMENT SYSTEM ================");
        System.out.println("1. Thêm tài khoản mới");
        System.out.println("2. Nạp tiền vào tài khoản");
        System.out.println("3. Rút tiền từ tài khoản");
        System.out.println("4. Chuyển tiền giữa 2 tài khoản");
        System.out.println("5. Tìm kiếm tài khoản");
        System.out.println("6. Hiển thị danh sách tài khoản & Tính tổng số dư");
        System.out.println("0. Thoát");
        System.out.println("=========================================================");
    }

    private static void handleAddAccount() {
        try {
            System.out.println("--- THÊM TÀI KHOẢN MỚI ---");
            System.out.print("Loại tài khoản (1. Thường | 2. Tiết kiệm): ");
            int type = Integer.parseInt(scanner.nextLine());

            System.out.print("Nhập số tài khoản: ");
            String accNum = scanner.nextLine().trim();
            System.out.print("Nhập tên chủ tài khoản: ");
            String name = scanner.nextLine().trim();
            System.out.print("Nhập số dư ban đầu: ");
            double balance = Double.parseDouble(scanner.nextLine());

            if (type == 1) {
                BankAccount acc = new NormalAccount(accNum, name, balance);
                bankManager.addAccount(acc);
            } else if (type == 2) {
                System.out.print("Nhập lãi suất hàng năm (%): ");
                double rate = Double.parseDouble(scanner.nextLine());
                BankAccount acc = new SavingAccount(accNum, name, balance, rate);
                bankManager.addAccount(acc);
            } else {
                System.out.println("❌ Loại tài khoản không hợp lệ!");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Lỗi định dạng số!");
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("❌ Lỗi Tiền/Hậu điều kiện: " + e.getMessage());
        }
    }

    private static void handleDeposit() {
        try {
            System.out.print("Nhập số tài khoản cần nạp: ");
            String accNum = scanner.nextLine().trim();
            BankAccount acc = bankManager.findAccount(accNum);
            if (acc == null) {
                System.out.println("❌ Không tìm thấy tài khoản!");
                return;
            }
            System.out.print("Nhập số tiền nạp: ");
            double amount = Double.parseDouble(scanner.nextLine());
            acc.deposit(amount);
            System.out.printf("✅ Nạp tiền thành công! Số dư mới: %,.0f VNĐ%n", acc.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("❌ Số tiền nhập không đúng định dạng!");
        } catch (InvalidAmountException e) {
            System.out.println("❌ Lỗi giao dịch: " + e.getMessage());
        }
    }

    private static void handleWithdraw() {
        try {
            System.out.print("Nhập số tài khoản cần rút: ");
            String accNum = scanner.nextLine().trim();
            BankAccount acc = bankManager.findAccount(accNum);
            if (acc == null) {
                System.out.println("❌ Không tìm thấy tài khoản!");
                return;
            }
            System.out.print("Nhập số tiền rút: ");
            double amount = Double.parseDouble(scanner.nextLine());
            acc.withdraw(amount);
            System.out.printf("✅ Rút tiền thành công! Số dư mới: %,.0f VNĐ%n", acc.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("❌ Số tiền nhập không đúng định dạng!");
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("❌ Lỗi giao dịch: " + e.getMessage());
        }
    }

    private static void handleTransfer() {
        try {
            System.out.print("Nhập STK chuyển (nguồn): ");
            String fromAcc = scanner.nextLine().trim();
            System.out.print("Nhập STK nhận (đích): ");
            String toAcc = scanner.nextLine().trim();
            System.out.print("Nhập số tiền muốn chuyển: ");
            double amount = Double.parseDouble(scanner.nextLine());

            bankManager.transferMoney(fromAcc, toAcc, amount);
            System.out.println("✅ Chuyển tiền thành công!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Số tiền không hợp lệ!");
        } catch (InvalidAmountException | InsufficientBalanceException | IllegalArgumentException e) {
            System.out.println("❌ Lỗi chuyển tiền: " + e.getMessage());
        }
    }

    private static void handleFindAccount() {
        System.out.print("Nhập số tài khoản cần tìm: ");
        String accNum = scanner.nextLine().trim();
        BankAccount acc = bankManager.findAccount(accNum);
        if (acc != null) {
            System.out.println("✅ Thông tin tài khoản:");
            System.out.println(acc);
        } else {
            System.out.println("❌ Không tìm thấy tài khoản!");
        }
    }

    private static void handleDisplayAllAndTotal() {
        var allAccounts = bankManager.getAllAccounts();
        if (allAccounts.isEmpty()) {
            System.out.println("Hệ thống chưa có tài khoản nào.");
            return;
        }
        System.out.println("--- DANH SÁCH TÀI KHOẢN ---");
        for (BankAccount acc : allAccounts) {
            System.out.println(acc);
        }
        double totalBalance = bankManager.calculateTotalBalance(allAccounts);
        System.out.println("--------------------------------------------------");
        System.out.printf("💰 Tổng số dư toàn hệ thống: %,.0f VNĐ%n", totalBalance);
    }
}