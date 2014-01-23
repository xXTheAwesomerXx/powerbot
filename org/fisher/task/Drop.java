package org.fisher.task;

import org.fisher.BarbarianFisher;
import org.powerbot.script.wrappers.Item;

public class Drop extends Task {

	private int baitOffcuts = 11334;

	public Drop(BarbarianFisher script) {
		super(script);
	}

	public boolean backpackIsFull() {
		return ctx.backpack.select().size() == 28;
	}

	public boolean backpackContains(int ID) {
		return !ctx.backpack.select().id(ID).isEmpty();
	}

	@SuppressWarnings("unused")
	public boolean backpackContains(int[] IDs) {
		for (int i = 0; i < IDs.length; i++) {
			return backpackContains(IDs[i]);
		}
		return false;
	}

	public boolean playerIsResting() {
		return ctx.players.local().getAnimation() == -1;
	}

	@Override
	public boolean activate() {
		if (backpackIsFull()) {
			return (backpackContains(baitOffcuts));
		}
		return false;
	}

	@Override
	public void execute() {
//		System.out.println("Execute DROP");
		for (Item i : ctx.backpack.id(baitOffcuts)) {
			i.interact("Drop");
			sleep(1000);
		}
	}
}
