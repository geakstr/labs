package test.ru.asu.edu;

import org.junit.Test;
import static org.junit.Assert.*;

import ru.asu.edu.MoneyLogger;

public class MoneyLoggerTest {
	@Test
	public void log() {
		MoneyLogger loggerStub = new MoneyLogger() {
			@Override
			public void log(String message) {
				assertEquals("test message", message);
			}
		};
		loggerStub.log("test message");
	}
}