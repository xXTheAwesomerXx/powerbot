package org.fisher;

import java.util.*;

import org.fisher.task.*;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;

@Manifest(authors = "xXTheAwesomerXx", name = "BarbarianFisher", description = "Fishes at Otto's Grotto")
public class BarbarianFisher extends PollingScript {

	private List<Task> taskList = new ArrayList<Task>();
	private int startExp;

	@Override
	public void start() {
		taskList.add(new Fish(this));
		taskList.add(new Gut(this));
		taskList.add(new Drop(this));
		startExp = ctx.skills.getExperience(Skills.FISHING);
	}
	
	@Override
	public void stop() {
		System.out.println("Start Exp: " + startExp + ", Gained Exp: " + (ctx.skills.getExperience(Skills.FISHING) - startExp));
	}

	@Override
	public int poll() {
		for (Task task : taskList) {
			if (task.activate()) {
				task.execute();
				return 300;
			}
		}
		return 300;
	}

}
