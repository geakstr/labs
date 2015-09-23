package ru.asu.edu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MoneyLogger {
	public void log(String message) {
		try (PrintWriter pw = new PrintWriter(new FileWriter("log.txt", true))) {
			pw.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}