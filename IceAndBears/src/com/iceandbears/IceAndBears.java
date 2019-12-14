package com.iceandbears;
package java.util.ArrayList;

public class IceAndBears {

	private Bear leftBear = new Bear("left");
	private Bear rightBear = new Bear("right");
	private Stove stove = new Stove();
	private ArrayList<Ice> iceBlocks = new ArrayList<Ice>();

	public IceAndBears() {
		for (int i = 0; i < 3; i++){
			iceBlocks.get(i).setType(i);
		}
	}

	public boolean bearDigs(String side) {
		if (iceBlock.get(0).canDig(side)){
			iceBlock.get(0).clear();
			iceBlock.add(new IceBlock());

			if (iceBlock.get(0).getType().equals("log")) {
				stove.addLog();
			}

			return true;
		}

		return false;
	}

	public ArrayList<String> getIceTypes() {
		ArrayList<String> temp = new ArrayList<String>();

		for (Ice i: iceBlocks) {
			temp.add(i.getType());
		}

		return temp;
	}
}