package ru.asu.edu.stubs;

import ru.asu.edu.Bank;

public class BankStub extends Bank {
	@Override
	public int convert(int volume, String from, String to) {
		return volume;
	}
}
