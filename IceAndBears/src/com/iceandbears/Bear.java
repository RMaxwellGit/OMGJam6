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

	public void setSide(String s) {
		if (s.equals("left") || s.equals("right")){
			side = s;
		}
	}

	public void setState(String s) {
		if (s.equals("standing")) {
			state = s;
		} else if (s.eqauls("digging")) {
			state = s;
		} else if (s.equals("fallen")) {
			state = s;
		} else {
			state = "";
		}
	}

	public void getState() {
		return state;
	}
}