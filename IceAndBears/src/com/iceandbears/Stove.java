package com.iceandbears;

public class Stove {
	private double time;
	private final double max = 20;
	private final double logVal = 5;

	public Stove() {
		time = max;
	}

	public void decrease(int rate) {
		time -= rate;
	}

	public void addLog() {
		time += logVal;
	}

	public boolean isOut() {
		if (time <= 0) {
			return true;
		}
		//time is not out
		return false;
	}
}