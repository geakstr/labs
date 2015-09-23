package ru.asu.edu;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public final class Wallet {
	private Bank bank;
	private MoneyLogger log;
	private Map<String, Integer> balance = new TreeMap<>();
	
	public Wallet(Map<String, Integer> balance) {
		this.balance = balance;
		this.log = new MoneyLogger();
		this.bank = new Bank();
	}
	
	public Wallet(Bank bank, MoneyLogger logger, Map<String, Integer> balance) {
		this.bank = bank;
		this.log = logger;
		this.balance = balance;
	}

	public void addMoney(String type, int money) {
		Integer volume = balance.get(type);
		balance.put(type, volume != null ? volume + money : money);
		log.log(String.format("Add: Currency: %s, Amount: %d, Balance: %d", type, money, getMoney(type)));
	}

	public void removeMoney(String currency, int money) {
		if (balance.containsKey(currency)) {
			int result = balance.get(currency) - money;
			if (result < 0) {
				String message = String.format("Insufficient funds. Has: %d, Required: %d", balance.get(currency), money);
				log.log(message);
				throw new IllegalArgumentException(message);
			}
			balance.put(currency, result);
			log.log(String.format("Withdrawal: Currency: %s, Amount: %d, Balance: %d", currency, money, getMoney(currency)));
		} else {
			String message = "You do not have this currency in your wallet";
			log.log(message);
			throw new IllegalArgumentException(message);
		}
	}
	
	public int getMoney(String type) {
		Integer money = balance.get(type);
		return money != null ? money : 0;
	}
	
	public int getCount() {
		int count = 0;
		for (Integer value : balance.values()) {
			if (value != 0) {
				count++;
			}
		}
		return count;
	}
	
	public int getTotalMoney(String type) {
		int sum = 0;
		for (Map.Entry<String, Integer> entry : balance.entrySet()) {
			sum += bank.convert(entry.getValue(), entry.getKey(), type);
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Wallet: Balance=" + balance;
	}

	public Map<String, Integer> getBalance() {
		return balance;
	}

	public void setBalance(Map<String, Integer> balance) {
		this.balance = balance;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public MoneyLogger getLogger() {
		return log;
	}

	public void setLogger(MoneyLogger logger) {
		this.log = logger;
	}
	
	public static void main(String[] args) {
		Wallet wallet = new Wallet(new HashMap<String, Integer>());
		
		wallet.addMoney("RUB", 100);
		wallet.addMoney("RUB", 100);
		wallet.addMoney("RUB", 100);
		
		wallet.addMoney("USD", 200);
		wallet.addMoney("USD", 200);
		wallet.addMoney("USD", 200);
		
		System.out.println(wallet.getTotalMoney("USD"));
	}
}