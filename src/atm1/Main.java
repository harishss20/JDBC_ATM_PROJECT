package atm1;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		UserDetail UD = new UserDetail();
		boolean isPasswordCorrect = false;
		try {
			isPasswordCorrect = UD.passCheck();

			if (isPasswordCorrect) {
				while (choice != 4) {
					UD.atm_works();
					choice = sc.nextInt();

					// Handle different cases based on the selected choice
					switch (choice) {
					case 1:
						UD.checkBalance();
						break;
					case 2:
						UD.deposit();
						break;
					case 3:
						UD.withdraw();
						break;
					case 4:
						System.out.println("Thank you for your visit. ");
						break;
					default:
						System.out.println("Invalid choice");
						break;
					}
				}

			} else {
				System.out.println("Incorrect Password Please try again....");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
