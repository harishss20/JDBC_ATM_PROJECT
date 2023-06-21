package atm1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UserDetail {
	String pin;
	String password;

	Scanner sc = new Scanner(System.in);
	dataBasecon dbcon = new dataBasecon();

	public UserDetail() {
		System.out.println("Enter your Pin Number:");
		pin = sc.nextLine();
		System.out.println("Enter your Password :");
		password = sc.nextLine();
	}

	public void atm_works() {
		System.out.println("Enter 1 to checkBalance ");
		System.out.println("Enter 2 to deposit amount ");
		System.out.println("Enter 3 to  withdraw amount ");
		System.out.println("Enter 4 to exit!!!");
	}

	public void checkBalance() throws SQLException {

		String query = "SELECT balance FROM user_detail WHERE account_no  ='" + pin + "'";
		Connection con = dbcon.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			System.out.println("balance is :" + rs.getInt(1));
		}

	}

	public boolean passCheck() throws SQLException {

		String query = "SELECT password FROM user_detail WHERE account_no  ='" + pin + "'";
		Connection con = dbcon.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String passDb = rs.getString(1);
			if (password.equals(passDb)) {
				return true;
			} else {
				return false;
			}

		}
		return false;

	}

	public void deposit() throws SQLException {
		double amount;
		System.out.println("Enter your deposit amount :");
		amount = sc.nextDouble();
		String selectquery = "SELECT balance FROM user_detail WHERE account_no  ='" + pin + "'";
		String updateQuery = "UPDATE user_detail SET balance = balance + " + amount + " WHERE account_no = '" + pin
				+ "'"; // balance is deposit form the balance
		Connection con = dbcon.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectquery);
		double balance = 0;
		if (rs.next()) {
			balance = rs.getDouble("balance");
		} else {
			System.out.println("Account is not found");
		}

		int rowsAffected = stmt.executeUpdate(updateQuery);
		if (rowsAffected > 0) {
			System.out.println("Deposit successful");
			balance += amount;
			System.out.println("Updated balance: " + balance);
		} else {
			System.out.println("Deposit failed");
		}
	}

	public void withdraw() throws SQLException {
		double amount;
		System.out.println("Enter amount to withdraw:");
		amount = sc.nextDouble();
		String selectquery = "SELECT balance FROM user_detail WHERE account_no  ='" + pin + "'";
		String updateQuery = "UPDATE user_detail SET balance = balance - " + amount + " WHERE account_no = '" + pin
				+ "'"; // balance is withdraw form the balance
		Connection con = dbcon.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(selectquery);
		double balance = 0;
		if (rs.next()) {
			balance = rs.getDouble("balance");
		} else {
			System.out.println("Account is not found");
		}

		int rowsAffected = stmt.executeUpdate(updateQuery);
		if (rowsAffected > 0) {
			if (amount < balance) {
				System.out.println("withdraw is  successful");
				balance -= amount;
				System.out.println("Updated balance: " + balance);
			} else {
				System.out.println("amount is insufficient ,Please try again... ");
			}
		} else {
			System.out.println("withdraw failed");
		}

	}
}
