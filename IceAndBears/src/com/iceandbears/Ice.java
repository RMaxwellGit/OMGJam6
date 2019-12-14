package com.iceandbears;

import java.util.Random;

public class Ice {
	private String type;

	public Ice () {
		Random r = new Random();
		int t = r.nextInt(3);
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
		}
	}

	public String getType() {
		return type;
	}

	public boolean canDig(String side) {
		if (type.equals("base") || side.equals(type)) {
			return true;
		}
		return false;
	}
}