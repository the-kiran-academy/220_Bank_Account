package com.tka.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;

import com.tka.config.HibernateConfig;
import com.tka.entity.BankAccount;

public class Operation {
	SessionFactory sf = HibernateConfig.getSessionFactory();
	Scanner scanner = new Scanner(System.in);

	public String createAccount() {
		System.out.println("Enter Account Number : ");
		String accountNumber = scanner.nextLine();

		System.out.println("Enter Account Holder Name : ");
		String accountHolderName = scanner.nextLine();

		System.out.println("Enter Account Balance : ");
		double balance = scanner.nextDouble();

		System.out.println("Enter Account Type : ");
		String accountType = scanner.next();

		System.out.println("Enter Account Currency : ");
		String currency = scanner.next();

		BankAccount account = new BankAccount(accountNumber, accountHolderName, balance, accountType, currency);
		Session session = sf.openSession();
		session.save(account);
		session.beginTransaction().commit();
		session.close();
		return "Account created successfully with Acc Number : " + account.getAccountNumber();
	}

	public String deleteAccount() {

		System.out.println("Enter Account Number to delete : ");
		String accountNumber = scanner.next();

		Session session = sf.openSession();
		BankAccount account = session.get(BankAccount.class, accountNumber);
		session.delete(account);
		session.beginTransaction().commit();
		session.close();
		return "Account deleted successfully with Acc Number : " + account.getAccountNumber();
	}

	public String updateAccount() {

		System.out.println("Enter Account Number : ");
		String accountNumber = scanner.nextLine();

		System.out.println("Enter Account Holder Name : ");
		String accountHolderName = scanner.nextLine();

		System.out.println("Enter Account Balance : ");
		double balance = scanner.nextDouble();

		System.out.println("Enter Account Type : ");
		String accountType = scanner.next();

		System.out.println("Enter Account Currency : ");
		String currency = scanner.next();

		BankAccount account = new BankAccount(accountNumber, accountHolderName, balance, accountType, currency);

		Session session = sf.openSession();

		session.update(account);
		session.beginTransaction().commit();
		session.close();
		return "Account updated successfully with Acc Number : " + account.getAccountNumber();
	}

	public BankAccount getAccount() {

		System.out.println("Enter Account Number to fetch : ");
		String accountNumber = scanner.nextLine();

		Session session = sf.openSession();
		BankAccount bankAccount = session.get(BankAccount.class, accountNumber);
		session.close();
		return bankAccount;
	}

	public List<BankAccount> getBankcAccountsByGivenIds() {

		List<String> accNumbers = new ArrayList<String>();

		System.out.println("How many ?");
		int count = scanner.nextInt();

		for (int i = 1; i <= count; i++) {
			System.out.println("Enter Acc Number");
			String accNumber = scanner.next();
			accNumbers.add(accNumber);
		}
		Session session = sf.openSession();

		List<BankAccount> accounts = session.byMultipleIds(BankAccount.class).multiLoad(accNumbers);
		session.close();
		return accounts;

	}

	// user want to retrieve all accounts
	public List<BankAccount> getAllBankAccounts() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// user want to retrieve all accounts in ascending order as per accHolderName
	public List<BankAccount> getAllBankAccountsInASC() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);

		criteria.addOrder(Order.asc("accountHolderName"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	// user want to retrieve first 5 accounts
	public List<BankAccount> getFirstFiveAccounts() {

		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setMaxResults(5);
		criteria.addOrder(Order.asc("accountHolderName"));
		List<BankAccount> list = criteria.list();
		return list;
	}

	// user want to retrieve a specific number of records starting from a particular
	// row

	public List<BankAccount> getSpecificRecord() {

		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setFirstResult(10);
		criteria.setMaxResults(5);
		List<BankAccount> list = criteria.list();
		return list;
	}

	// user want to get such a data whose balance is more than 1500

	public List<BankAccount> restrictiionsEx1() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		// criteria.add(Restrictions.between("balance", 1500d,3000d));
		criteria.add(Restrictions.eq("accountHolderName", "xyz"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	public List<BankAccount> getAccountsByInitial() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		System.out.println("Enter Initial");
		String initial = scanner.next();
		criteria.add(Restrictions.like("accountHolderName", initial + "%"));
		List<BankAccount> list = criteria.list();
		session.close();
		return list;
	}

	public List<BankAccount> getAccountsWithBalanceIsMoreThan() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		System.out.println("Enter Acc Type");
		String accountType = scanner.next();
		System.out.println("Enter Amount");
		double amount = scanner.nextDouble();
		SimpleExpression accType = Restrictions.eq("accountType", accountType);
		SimpleExpression balance = Restrictions.gt("balance", amount);
		criteria.add(Restrictions.and(accType, balance));
		List<BankAccount> list = criteria.list();

		session.close();
		return list;
	}

	public long totalAccountCount() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.rowCount());
		List list = criteria.list();
		long count = (long) list.get(0);
		return count;
	}

	public double minBalance() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		criteria.setProjection(Projections.min("balance"));
		List<Double> list = criteria.list();
		double minimumBalance = list.get(0);
		return minimumBalance;
	}

	public List<BankAccount> getMaxBalanceAcount() {
		Session session = sf.openSession();
		Criteria criteria1 = session.createCriteria(BankAccount.class);
		criteria1.setProjection(Projections.max("balance"));
		double maxBalance = (double) criteria1.list().get(0);
		Criteria criteria2 = session.createCriteria(BankAccount.class);
		criteria2.add(Restrictions.eq("balance", maxBalance));
		List<BankAccount> list = criteria2.list();
		return list;
	}

	public List<BankAccount> getAllAccountsUsingHQL() {
		Session session = sf.openSession();
		Query<BankAccount> query = session.createQuery("from BankAccount");
		List<BankAccount> list = query.list();
		return list;
	}

	public List<BankAccount> getAccountByNameUsingHQL() {
		Session session = sf.openSession();
		Query<BankAccount> query = session.createQuery("from BankAccount where accountHolderName =:name");
		query.setParameter("name", "xyz");

		List<BankAccount> list = query.list();
		return list;
	}

	// user want to retrieve only account number & its holder name for all accounts

	public List<Object[]> getAll_AccNumber_HolderName() {
		Session session = sf.openSession();
		Query<Object[]> query = session.createQuery("SELECT accountNumber,accountHolderName FROM BankAccount");
		List<Object[]> list = query.list();
		return list;
	}
	
	public List<Object[]> getAll_AccNumber_HolderName_Projection() {
		Session session = sf.openSession();
		Criteria criteria = session.createCriteria(BankAccount.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property("accountNumber"));
		projectionList.add(Projections.property("accountHolderName"));
		criteria.setProjection(projectionList);
		List<Object[]> list = criteria.list();
		return list;
	}	

}
