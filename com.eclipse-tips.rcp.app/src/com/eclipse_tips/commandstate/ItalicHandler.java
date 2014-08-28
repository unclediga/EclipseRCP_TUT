package com.eclipse_tips.commandstate;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.handlers.HandlerUtil;

public class ItalicHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Command command = event.getCommand();
		boolean oldVal = HandlerUtil.toggleCommandState(command);

		try {
			System.out.println("Old value of command "+command.getName()+"="+oldVal);
		} catch (NotDefinedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
