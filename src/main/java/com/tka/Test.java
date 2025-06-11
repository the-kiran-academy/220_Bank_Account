package com.tka;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.tka.entity.BankAccount;
import com.tka.operation.Operation;

public class Test {
	public static void main(String[] args) {
		Operation operation = new Operation();
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean wantToContinue = true;
		do {
			System.out.println("1. Create Account");
			System.out.println("2. Fetch Account");
			System.out.println("3. Update Account");
			System.out.println("4. Delete Account");
			System.out.println("5. Get Accounts by account numbers ");
			System.out.println("6. Get All Accounts");
			System.out.println("7. Get All Accounts in ASC Order");
			System.out.println("8. Get First Five");
			System.out.println("9. Get Specific records starting from a particular row");
			System.out.println("10. Get All Accounts whose balance is more than something");
			System.out.println("11. Get All Accounts Whose Holder Name Start with ?");
			System.out.println("12. Get All Accounts As Per Type & Whose Balanace is More than ?");
			
			System.out.println("13. All Record Count");
			System.out.println("14. Get Min Balance");
			System.out.println("15. Get Max Balance Account");
			System.out.println("16. Get All Accounts Using HQL");
			System.out.println("17. Get Accounts By Holder Name Using HQL");
			System.out.println("18. Get All Acc Number & HOlder Name");
			System.out.println("0. Stop Application");
			System.out.println("Press any number from above to perform operation !!");
			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				String msg = operation.createAccount();
				System.out.println(msg);
				break;
			}
			case 2: {
				BankAccount account = operation.getAccount();
				System.out.println(account);
				break;
			}
			case 3: {
				String msg = operation.updateAccount();
				System.out.println(msg);
				break;
			}
			case 4: {
				String msg = operation.deleteAccount();
				System.out.println(msg);
				break;
			}

			case 5: {
				List<BankAccount> accounts = operation.getBankcAccountsByGivenIds();

				for (BankAccount bankAccount : accounts) {
					System.out.println(bankAccount);
				}
				break;
			}

			case 6: {
				List<BankAccount> allAccounts = operation.getAllBankAccounts();

				for (BankAccount bankAccount : allAccounts) {
					System.out.println(bankAccount);
				}

				break;
			}

			case 7: {
				List<BankAccount> list = operation.getAllBankAccountsInASC();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}

			case 8: {
				List<BankAccount> list = operation.getFirstFiveAccounts();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			case 9:{
				List<BankAccount> list = operation.getSpecificRecord();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			case 10:{
				List<BankAccount> list = operation.restrictiionsEx1();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			case 11:{
				List<BankAccount> list = operation.getAccountsByInitial();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			case 12:{
				List<BankAccount> list = operation.getAccountsWithBalanceIsMoreThan();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			
			case 13:{
				long count = operation.totalAccountCount();
				System.out.println(count);
			}
			
			case 14:{
				double minimumBalance = operation.minBalance();
				System.out.println(minimumBalance);
			}
			
			case 15:{
				List<BankAccount> list = operation.getMaxBalanceAcount();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			case 16:{
				List<BankAccount> list = operation.getAllAccountsUsingHQL();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			case 17:{
				List<BankAccount> list = operation.getAccountByNameUsingHQL();
				for (BankAccount bankAccount : list) {
					System.out.println(bankAccount);
				}
				break;
			}
			
			case 18:{
				List<Object[]> list = operation.getAll_AccNumber_HolderName_Projection();
				for (Object[] obj : list) {
					System.out.print(obj[0] +"\t");
					System.out.println(obj[1]);
				}
				break;
			}

			case 0: {
				wantToContinue = false;
				break;
			}

			default:
				wantToContinue = false;
				break;
			}

		} while (wantToContinue);
		System.out.println("App Terminated");
	}

}
