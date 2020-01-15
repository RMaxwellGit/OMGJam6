package com.iceandbears;

public class Stove {
	private int time;
	private final int max = 9;
	private final int logVal = 5;

	public Stove() {
		time = max;
	}

	public void decrease() {
		time -= 1;
	}

	public void addLog() {
		time += logVal;
		if (time > max) time = max;
	}

	public int getState() {
		return time/3;
	}

	public boolean isOut() {
		if (time <= 0) {
			return true;
		}
		//time is not out
		return false;
	}
}