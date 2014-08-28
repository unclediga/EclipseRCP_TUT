package com.eclipse_tips.commandstate;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;

public class AlignHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		if (HandlerUtil.matchesRadioState(event))
			return null; // we are already in the updated state - do nothing
		String currentState = event.getParameter(RadioState.PARAMETER_ID);
		// perform task for current state
		if (currentState.equals("left")) {
			// perform left alignment
		} else if (currentState.equals("center")) {
			// perform center alignment
		}
		// and so on ...

		// and finally update the current state
		HandlerUtil.updateRadioState(event.getCommand(), currentState);
		System.out.println("Radio state:" + currentState);
		return null;
	}
}
