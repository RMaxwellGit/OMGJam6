package com.iceandbears;

public class Bear {
	private String side;
	private String state;

	public Bear(String s) {
		side = s;
		state = "base";
	}

	public String dig() {
		return side;
	}

	public void setSide(String newSide) {
		if ((newSide.equals("left")) || (newSide.equals("right"))){
			side = newSide;
		}
	}

	public void setState(String s) {
		if (s.equals("standing")) {
			state = s;
		} else if (s.equals("digging")) {
			state = s;
		} else if (s.equals("fallen")) {
			state = s;
		} else if (s.equals("throwing")) {
			state = s;
		} else {
			state = "";
		}
	}

	public String getState() {
		return state;
	}
}