package com.iceandbears;

import java.util.Random;

public class Ice {
	private String type;

	public Ice () {
		Random r = new Random();
		int t = r.nextInt(10);
		type = setType(t);
	}

	public Ice (String t) {
		type = t;
	}

	private String setType(int t) {
		switch (t) {
			case 0:
			case 1:
			case 2:
				return "base";//no rock
			case 3:
			case 4:
			case 5:
				return "left";//rock on left
			case 6:
			case 7:
			case 8:
				return "right";//rock on right
			case 9:
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