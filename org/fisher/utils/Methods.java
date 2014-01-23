package org.fisher.utils;

import org.powerbot.script.methods.MethodContext;

public class Methods {

	public static MethodContext ctx;

	public static boolean backpackIsFull() {
		return ctx.backpack.select().size() == 28;
	}

	public static boolean backpackContains(int ID) {
		return !ctx.backpack.select().id(ID).isEmpty();
	}

	@SuppressWarnings("unused")
	public static boolean backpackContains(int[] IDs) {
		for (int i = 0; i < IDs.length; i++) {
			return backpackContains(IDs[i]);
		}
		return false;
	}

	public boolean playerIsResting() {
		return ctx.players.local().getAnimation() == -1;
	}

}
