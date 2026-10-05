import java.util.Scanner;
public class Main{
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		BankManeger maneger = new BankManeger();
		while(true){
		System.out.println("1. Them tai khoan");
		System.out.println("2. Tim tai khoan");
		System.out.println("3. Nap tien");
		System.out.println("4. Rut tien");
		System.out.println("5. Cuyen tien");
		System.out.println("0. Thoat");
		System.out.print("Nhap lua chon: ");
		int choice = scanner.nextInt();
		switch(choice){
			case 1:{
			System.out.print("Nhap so tai khoan: ");
			String accountNumber = scanner.next();
			scanner.nextLine();
			
			System.out.print("Nhap ten chu so huu: ");
			String holderName = scanner.next();
			scanner.nextLine();
			
			System.out.print("So du ban dau: ");
			Double balance = scanner.nextDouble();
			
			System.out.print("Nhap lai suat: ");
			Double interestRate = scanner.nextDouble();
			
			try {
				SavingAccount acc = new SavingAccount(accountNumber, holderName, balance, interestRate);
				maneger.addAccount(acc);
				System.out.println("Them tai khoan thanh cong");
				}
			catch (Exception e) {System.out.println("Loi");}
			}
			break;
			
			case 2:{
			System.out.print("Nhap so tai khoan can tim ");
			String accountNumber = scanner.next();
			scanner.nextLine();
			BankAccount found = maneger.findAccount(accountNumber);
			if (found != null){
				System.out.println("So tai khoan: " + found.getAccountNumber());
				System.out.println("Chu tai khoan: " + found.getHolderName());
				System.out.println("So du: " + found.getBalance());
			} else System.out.print("Khong tim thay tai khoan!");
			}
			break;
			
			case 3:
                        System.out.print("Nhap so tai khoan: ");
                        String depAccNum = scanner.nextLine();
                        BankAccount depAcc = maneger.findAccount(depAccNum);
                        if (depAcc == null) {
                            System.out.println("Khong tim thay tai khoan!");
                            break;
                        }
                        System.out.print("Nhap so tien can nap: ");
                        double depAmount = Double.parseDouble(scanner.nextLine());
                        depAcc.deposit(depAmount);
                        System.out.println(">> Nap thanh cong! " + depAcc);
            break;

            case 4:
                        System.out.print("Nhap so tai khoan: ");
                        String withAccNum = scanner.nextLine();
                        BankAccount withAcc = maneger.findAccount(withAccNum);
                        if (withAcc == null) {
                            System.out.println("Khong tim thay tai khoan!");
                            break;
                        }
                        System.out.print("Nhap so tien can rut: ");
                        double withAmount = Double.parseDouble(scanner.nextLine());
						try {
                        withAcc.withdraw(withAmount);
                        System.out.println(">> Rut thanh cong! " + withAcc);}
						catch (InvalidAmountException e) {
							System.out.println("Loi so tien khong hop le!");
						} 
						catch (InsufficientBalanceException e) {
							System.out.println("Loi du khong hop le!");
						}
            break;

            case 5:
                        System.out.print("Nhap so tai khoan nguon: ");
                        String fromAcc = scanner.nextLine();
                        System.out.print("Nhap so tai khoan dich: ");
                        String toAcc = scanner.nextLine();
                        System.out.print("Nhap so tien chuyen: ");
                        double transferAmount = Double.parseDouble(scanner.nextLine());
						try {
                        maneger.transferMoney(fromAcc, toAcc, transferAmount);
                        System.out.println(">> Chuyen thanh cong!");}
						catch (InvalidAmountException e) {
							System.out.println("Loi so tien khong hop le!");
						} 
						catch (InsufficientBalanceException e) {
							System.out.println("Loi du khong hop le!");
						}
            break;
			case 0:
                        System.out.println("Cam on ban vi da su dung dich vu!");
                        return;

            default:
                        System.out.println("Lua chon khong hop le, xin thu lai!");


		}
		}
	}
}