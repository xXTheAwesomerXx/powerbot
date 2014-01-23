package org.fisher.task;

import org.fisher.BarbarianFisher;
import org.powerbot.script.wrappers.Npc;

public class Fish extends Task {

	private int baitOffcuts = 11334, baitRoe = 11324, baitCaviar = 11326;

	public Fish(BarbarianFisher script) {
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
			if (backpackContains(baitOffcuts)) {
				return false;
			} else {
				
				return (backpackContains(baitRoe) || backpackContains(baitCaviar));
			}
		} else {
			return (backpackContains(baitOffcuts) || backpackContains(baitRoe) || backpackContains(baitCaviar));
		}
	}

	@Override
	public void execute() {
//		System.out.println("Execute FISH");
		if (playerIsResting()) {
			if (!ctx.npcs.select().id(2722).isEmpty()) {
				Npc fishingNPC = ctx.npcs.nearest().poll();
				if (fishingNPC.isOnScreen()) {
					fishingNPC.interact("Use-rod");
					sleep(500);
				} else {
					ctx.movement.findPath(fishingNPC).traverse();
					ctx.camera.turnTo(fishingNPC);
				}
			}
		}
	}
}
