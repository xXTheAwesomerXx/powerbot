package org.fisher.task;

import org.fisher.BarbarianFisher;
import org.powerbot.script.methods.MethodProvider;

public abstract class Task extends MethodProvider {

	@SuppressWarnings("deprecation")
	public Task(BarbarianFisher script) {
		super(script.getContext());
	}

	public abstract boolean activate();

	public abstract void execute();

}
