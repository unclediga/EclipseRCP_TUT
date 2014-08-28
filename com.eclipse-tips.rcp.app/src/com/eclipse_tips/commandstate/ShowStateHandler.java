package com.eclipse_tips.commandstate;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.State;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.RadioState;
import org.eclipse.ui.handlers.RegistryToggleState;

public class ShowStateHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ICommandService service = (ICommandService) HandlerUtil.getActiveWorkbenchWindow(event).getService(ICommandService.class);
		Command command = service.getCommand("com.eclipse-tips.commandState.alignCommand");
		State state = command.getState(RadioState.STATE_ID);
		System.out.println("State Radio is "+state.getValue());
		command = service.getCommand("com.eclipse-tips.commandState.boldCommand");
		state = command.getState(RegistryToggleState.STATE_ID);
		System.out.println("State Bold is "+state.getValue());
		return null;
	}

}
