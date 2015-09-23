package test.ru.asu.edu;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.asu.edu.Bank;

public class BankTest {
	Bank bank;
	
	@Before
	public void init() {
		this.bank = new Bank();
	}
	
	@Test
	public void convert() {
		assertEquals(120, bank.convert(2, "EUR", "RUB"));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void convertEcxeption(){
		bank.convert(100, "RUB", "AZAZA");
	}
}