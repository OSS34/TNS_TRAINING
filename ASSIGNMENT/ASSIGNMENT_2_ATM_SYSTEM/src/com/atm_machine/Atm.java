package com.atm_machine;
import java.util.Scanner;

public class Atm {
	 long balance;

	public void depositMoney(long amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount. Must be greater than zero.\n");
			return;
		}
		balance += amount;
		System.out.println(amount + " Money Deposited !!!\n");
	}

	public void withdrawMoney(long amount) {
		if (amount <= 0) {
			System.out.println("Invalid amount. Must be greater than zero.\n");
			return;
		}
		if (amount > balance) {
			System.out.println("Not Sufficient Balance !!!\n");
		} else {
			balance -= amount;
			System.out.println(amount + " Money Withdrawn\n");
		}
	}

	public void showBalance() {
		System.out.println("Available Money : " + balance + "\n");
	}

	private static void showMenu() {
		System.out.println("Welcome to ATM System");
		System.out.println("1. Add Money");
		System.out.println("2. Withdraw Money");
		System.out.println("3. Show Balance");
		System.out.println("4. Exit");
		System.out.print("Select option (e.g., 1): ");
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Atm atm = new Atm();
		boolean running = true;
		long amt;

		while (running) {
			showMenu();
			int choice = input.nextInt();
			System.out.println();

			switch (choice) {
				case 1:
					System.out.print("Enter money to add to account: ");
					amt = input.nextLong();
					atm.depositMoney(amt);
					break;
				case 2:
					System.out.print("Enter money to withdraw from account: ");
					amt = input.nextLong();
					atm.withdrawMoney(amt);
					break;
				case 3:
					atm.showBalance();
					break;
				case 4:
					System.out.println("Thank you for using the ATM!");
					running = false;
					break;
				default:
					System.out.println("Invalid Option !!!\n");
			}
			System.out.println("------------------------------------");
		}
		input.close();
	}
}

