package com.iceandbears;

import java.util.Random;

public class Ice {
	private String type;

	public Ice () {
		Random r = new Random();
		int t = r.nextInt(4);
		type = setType(t);
	}

	public Ice (String t) {
		type = t;
	}

	private String setType(int t) {
		switch (t) {
			case 0:
				return "base";//no rock
			case 1:
				return "left";//rock on left
			case 2:
				return "right";//rock on right
			case 3:
				return "log";//log in middle
			default:
				return null;
		}
	}

	public String getType() {
		return type;
	}

	public boolean canDig(String side) {
		if ((side.equals("base")) || (side.equals("log"))) return true;
		
		if (side.equals(type)) return false;

		return true;
	}
}