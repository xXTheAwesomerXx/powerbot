package org.fisher.task;

import org.fisher.BarbarianFisher;
import org.powerbot.script.wrappers.Item;

public class Gut extends Task {

	private int baitOffcuts = 11334, baitRoe = 11324, baitCaviar = 11326;
	private int fishTrout = 11328, fishSalmon = 11330, fishSturgeon = 11332;
	private int[] fishes = { fishTrout, fishSalmon, fishSturgeon };

	public Gut(BarbarianFisher script) {
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
			return (backpackContains(fishTrout) || backpackContains(fishSalmon) || backpackContains(fishSturgeon));
		} else {
			return !(backpackContains(baitOffcuts) || backpackContains(baitRoe) || backpackContains(baitCaviar));
		}
	}

	@Override
	public void execute() {
//		System.out.println("Execute GUT");
		do {
			if (!playerIsResting()) {
				sleep(500);
			} else {
				if (backpackContains(fishTrout) || backpackContains(fishSalmon)
						|| backpackContains(fishSturgeon)) {
					if (ctx.widgets.get(1251, 0).isVisible()) {
						sleep(500);
					} else {
						if (ctx.widgets.get(1370, 38).isVisible()) {
							ctx.widgets.get(1370, 38).click();
							sleep(2500);
						} else {
							for (Item i : ctx.backpack.select().id(fishes).first()) {
								if (i.getId() == fishTrout
										|| i.getId() == fishSalmon
										|| i.getId() == fishSturgeon) {
									i.interact("Gut");
									sleep(1500);
								}
							}
						}
					}
				} else {
					System.out.println("No fish to gut, somoething went wrong...");
				}
			}
		}
		while (backpackContains(fishes));
	}
}
