package ru.asu.edu.stubs;

import ru.asu.edu.MoneyLogger;

public class MoneyLoggerStub extends MoneyLogger {
	public String message;

	@Override
	public void log(String message) {
		this.message = message;
	}
}
