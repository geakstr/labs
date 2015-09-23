package ru.asu.edu;

import java.util.Map;
import java.util.TreeMap;

public class Bank {
	@SuppressWarnings("serial")
    private static final Map<ExchangeRate, Double> exchangeRates = new TreeMap<ExchangeRate, Double>() {{
		put(new ExchangeRate("RUB", "USD"), 0.02);
		put(new ExchangeRate("RUB", "EUR"), 0.017);
		put(new ExchangeRate("USD", "RUB"), 50.0);
		put(new ExchangeRate("EUR", "RUB"), 60.0);
	}};
	
	public int convert(int volume, String from, String to) {
		if (from.equals(to)) {
			return volume;
		}
		
		Double exchangeRate = exchangeRates.get(new ExchangeRate(from, to));
		if (exchangeRate == null) {
			throw new IllegalArgumentException(String.format("The exchange rate is not found. From: %s, To: %s", from, to));
		}
		
		return (int) Math.round(volume * exchangeRate);
	}
	
	public static class ExchangeRate implements Comparable<ExchangeRate> {
		private String from, to;
		
		public ExchangeRate(String from, String to) {
			this.from = from;
			this.to = to;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		@Override
        public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((from == null) ? 0 : from.hashCode());
	        result = prime * result + ((to == null) ? 0 : to.hashCode());
	        return result;
        }

		@Override
        public boolean equals(Object obj) {
	        if (this == obj)
		        return true;
	        if (obj == null)
		        return false;
	        if (getClass() != obj.getClass())
		        return false;
	        ExchangeRate other = (ExchangeRate) obj;
	        if (from == null) {
		        if (other.from != null)
			        return false;
	        } else if (!from.equals(other.from))
		        return false;
	        if (to == null) {
		        if (other.to != null)
			        return false;
	        } else if (!to.equals(other.to))
		        return false;
	        return true;
        }

		@Override
        public int compareTo(ExchangeRate b) {
			if (from.compareTo(b.from) == 0) {
				return to.compareTo(b.to);
			}
	        return from.compareTo(b.from);
        }
	}
}