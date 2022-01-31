package mybank.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import mybank.data.Account;
import mybank.data.Transaction;
import mybank.user.Depositor;
import mybank.user.Employee;

public class EmployeeReader {

	
	public ArrayList<Employee> readEmployeeFile(String file) {
		String str = "";
		try {
			Scanner s = new Scanner(new FileInputStream(file));
			while (s.hasNext()) {
				str += s.next();
			}
			s.close();
			Scanner strScanner = new Scanner(str);
			strScanner.useDelimiter("\\r?\\n?[#]");
			ArrayList<Employee> emps = new ArrayList<Employee>();
			while (strScanner.hasNext()) {
				Employee e = processEmployee(strScanner.next());
				if (e != null) {
					emps.add(e);
				}
				else {
					continue;
				}
			}
			strScanner.close();
			return emps;
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file");
		}
		
	}
	
	private Employee processEmployee(String line) {
		Scanner employeeScanner = new Scanner(line);
		String first = "";
		String last = "";
		String email = "";
		String password = "";
		String id = "";
		String birthday = "";
		employeeScanner.useDelimiter(",");
		while (employeeScanner.hasNext() ) {
			try {
				first = employeeScanner.next().trim();
				last = employeeScanner.next().trim();
				email = employeeScanner.next().trim();
				password = employeeScanner.next().trim();
				id = employeeScanner.next().trim();
				birthday = employeeScanner.next().trim();
			} catch (NoSuchElementException e) {
				employeeScanner.close();
				return null;
			}
		}
		employeeScanner.close();
		Scanner depoScanner = new Scanner(line);
		depoScanner.useDelimiter("\\r?\\n?[*]");
		ArrayList<Depositor> arrlist = new ArrayList<Depositor>();
		while (depoScanner.hasNext()) {
			Depositor d = processDepositor(depoScanner.next());
			if (d != null) {
				arrlist.add(d);
			}
			else {
				continue;
			}
		}
		depoScanner.close();
		Depositor[] deps = new Depositor[arrlist.size()]; 
		for (int i = 0; i < arrlist.size(); i++) {
			deps[i] = arrlist.get(i);
		}
		try {
			Employee e = new Employee(first, last, email, password, id, birthday, deps);
			return e;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	private Depositor processDepositor(String line) {
		Scanner depoScanner = new Scanner(line);
		depoScanner.useDelimiter(",");
		String first = "";
		String last = "";
		String email = "";
		String password = "";
		String id = "";
		String birthday = "";
		while (depoScanner.hasNext()) {
			try {
				first = depoScanner.next();
				last = depoScanner.next();
				email = depoScanner.next();
				password = depoScanner.next();
				id = depoScanner.next();
				birthday = depoScanner.next();
			} catch (NoSuchElementException e) {
				depoScanner.close();
				return null;
			}
		}
		depoScanner.close();
		Scanner accScanner = new Scanner(line);
		ArrayList<Account> tempAccs = new ArrayList<Account>();
		accScanner.useDelimiter("\\r?\\n?[-]");
		while (accScanner.hasNext()) {
			Account a = processAccount(accScanner.next());
			if (a != null) {
				tempAccs.add(a);
			}
			else {
				continue;
			}
		}
		Account[] accs = new Account[tempAccs.size()];
		for (int i = 0; i < tempAccs.size(); i++) {
			accs[i] = tempAccs.get(i);
		}
		
		accScanner.close();
		try {
			Depositor d = new Depositor(first, last, email, password, id, birthday, accs);
			return d;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	private Account processAccount(String line) {
		Scanner accScanner = new Scanner(line);
		accScanner.useDelimiter(",");
		String name = "";
		String reference = "";
		String balanceStr = "";
		String checkingStr = "";
		String savingsStr = "";
		double balance = 0;
		boolean checking;
		boolean savings;
		try {
			while (accScanner.hasNext()) {
				name = accScanner.next();
				reference = accScanner.next();
				balanceStr = accScanner.next();
				checkingStr = accScanner.next();
				savingsStr = accScanner.next();
				balance = Double.parseDouble(balanceStr);
			}
		} catch (NoSuchElementException | NumberFormatException e) {
			accScanner.close();
			return null;
		}
	
		accScanner.close();
		if (checkingStr.equals("true")) {
			checking = true;
		}
		else if (checkingStr.equals("false")) {
			checking = false;
		}
		else {
			return null;
		}
		if (savingsStr.equals("true")) {
			savings = true;
		}
		else if (savingsStr.equals("false")) {
			savings = false;
		}
		else {
			return null;
		}
		Scanner transScan = new Scanner(line);
		transScan.useDelimiter("\\r?\\n?[$]");
		ArrayList<Transaction> tempTrans = new ArrayList<Transaction>();
		while (transScan.hasNext()) {
			Transaction t = processTransaction(transScan.next());
			if (t != null) {
				tempTrans.add(t);
			}
			else {
				continue;
			}
		}
		transScan.close();
		Transaction[] trans = new Transaction[tempTrans.size()];
		for (int i = 0; i < tempTrans.size(); i++) {
			trans[i] = tempTrans.get(i);
		}
		
		try {
			Account a = new Account(name, reference, balance, checking, savings, trans);
			return a;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	private Transaction processTransaction(String line) {
		Scanner tranScanner = new Scanner(line);
		String amountStr = "";
		String description = "";
		String date = "";
		double amount = 0;
		tranScanner.useDelimiter(",");
		while (tranScanner.hasNext()) {
			try {
				amountStr = tranScanner.next();
				description = tranScanner.next();
				date = tranScanner.next();
				amount = Double.parseDouble(amountStr);
			} catch (NoSuchElementException | NumberFormatException e) {
				tranScanner.close();
				return null;
			}
		}
		tranScanner.close();
		try {
			Transaction t = new Transaction(amount, description, date);
			return t;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
