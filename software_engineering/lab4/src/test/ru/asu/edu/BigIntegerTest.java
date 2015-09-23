package test.ru.asu.edu;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class BigIntegerTest {
	BigInteger a, b;
	
	@Before
	public void inti() {
		this.a = new BigInteger("100");
		this.b = new BigInteger("100");
	}
	
	@Test
	public void add() {
		assertEquals(new BigInteger("200"), a.add(b));
	}
	
	@Test
	public void subsctract(){
		assertEquals(new BigInteger("0"), a.subtract(b));
	}
	
	@Test
	public void multiply() {
		assertEquals(new BigInteger("10000"), a.multiply(b));
	}
	
	@Test
	public void divide(){
		assertEquals(new BigInteger("1"), a.divide(b));
	}
	
	@Test(expected = ArithmeticException.class) 
	public void divideByZero(){
		this.b = new BigInteger("0");
		a.divide(b);
	}
	
	@Test
	public void max(){
		this.b = new BigInteger("200");
		assertEquals(new BigInteger("200"), a.max(b));
	}
	
	@Test
	public void min(){
		this.b = new BigInteger("0");
		assertEquals(new BigInteger("0"), a.min(b));
	}
	
	@Test
	public void compareTo(){
		assertEquals(0, a.compareTo(b));
	}
	
	@Test
	public void gcd() {
		assertEquals(new BigInteger("100"), a.gcd(b));
	}
	
	@Test
	public void toStringTest(){
		assertEquals("100", a.toString());
	}
}
