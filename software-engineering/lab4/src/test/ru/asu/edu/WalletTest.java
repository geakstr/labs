package test.ru.asu.edu;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import ru.asu.edu.Wallet;
import ru.asu.edu.stubs.BankStub;
import ru.asu.edu.stubs.MoneyLoggerStub;

public class WalletTest {
	private Wallet wallet;
	private MoneyLoggerStub loggerStub;
	private BankStub bankStub;
	
	@Before
	public void init() {
		this.wallet = new Wallet(new HashMap<String, Integer>());
		this.loggerStub = new MoneyLoggerStub();
		this.bankStub = new BankStub();
		
		wallet.setLogger(loggerStub);
		wallet.setBank(bankStub);
	}

	@Test
	public void addMoney() {
		wallet.addMoney("RUB", 300);
		assertEquals(300, wallet.getMoney("RUB"));
		assertEquals("Add: Currency: RUB, Amount: 300, Balance: 300", loggerStub.message);
	}

	@Test
	public void removeMoney() {
		wallet.addMoney("RUB", 300);
		assertEquals("Add: Currency: RUB, Amount: 300, Balance: 300", loggerStub.message);
		
		wallet.removeMoney("RUB", 100);
		assertEquals("Withdrawal: Currency: RUB, Amount: 100, Balance: 200", loggerStub.message);
		
		assertEquals(200, wallet.getMoney("RUB"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void removeException() {
		wallet.addMoney("RUB", 300);
		wallet.removeMoney("RUB", 400);
	}

	@Test
	public void getMoney() {
		assertEquals(0, wallet.getMoney("RUB"));
	}

	@Test
	public void getCount() {
		wallet.addMoney("RUB", 300);
		wallet.addMoney("USD", 300);
		wallet.addMoney("EUR", 0);
		assertEquals(2, wallet.getCount());
	}

	@Test
	public void toStringTest() {
		wallet.addMoney("RUB", 100);
		wallet.addMoney("RUB", 100);
		wallet.addMoney("RUB", 100);

		wallet.addMoney("USD", 200);
		wallet.addMoney("USD", 200);
		wallet.addMoney("USD", 200);

		assertEquals("Wallet: Balance={RUB=300, USD=600}", wallet.toString());
	}

	@Test
	public void getTotalMoneyTest() {
		wallet.addMoney("RUB", 100);
		wallet.addMoney("USD", 200);
		
		assertEquals(300, wallet.getTotalMoney("RUB"));
	}
}