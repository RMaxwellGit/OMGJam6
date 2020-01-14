package com.iceandbears;

import java.util.ArrayList;

public class IceAndBears {

	private Bear leftBear = new Bear("left");
	private Bear rightBear = new Bear("right");
	private Stove stove = new Stove();
	private ArrayList<Ice> iceBlocks = new ArrayList<Ice>();

	public IceAndBears() {
		iceBlocks.add(new Ice("base"));
		iceBlocks.add(new Ice("left"));
		iceBlocks.add(new Ice("right"));
	}

	public boolean bearDigs(String side) {
		if (side.equals("up")) {
			if (iceBlocks.get(0).canDig("left") && (iceBlocks.get(0).canDig("right"))) {
		
				

				if (iceBlocks.get(0).getType().equals("log")) {
					stove.addLog();
					System.out.println("New Log!");
				}

				iceBlocks.remove(0);
				iceBlocks.add(new Ice());

				return true;
			}

			return false;			
		} else {
			if (iceBlocks.get(0).canDig(side)) {
		
				iceBlocks.remove(0);
				iceBlocks.add(new Ice());

				return true;
			}

			return false;			
		}
	}

	public ArrayList<String> getIceTypes() {
		ArrayList<String> temp = new ArrayList<String>();

		for (Ice i: iceBlocks) {
			temp.add(i.getType());
		}

		return temp;
	}
}