package com.eclipse_tips.rcp.app.handlers;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;
import org.eclipse.ui.services.ISourceProviderService;

import com.eclipse_tips.rcp.app.SessionSourceProvider;

public class LoginHandler extends AbstractHandler implements IElementUpdater{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		ISourceProviderService service = (ISourceProviderService) window.getService(ISourceProviderService.class);
		SessionSourceProvider sessionSourceProvider = (SessionSourceProvider) service.getSourceProvider(SessionSourceProvider.SESSION_STATE);
		sessionSourceProvider.setLoggedIn(true);
		
		ICommandService commandService = (ICommandService)window.getService(ICommandService.class); 
		commandService.refreshElements("com.eclipse-tips.rcp.app.sessionCommand", null);
		return null;
	}

	@Override
	public void updateElement(UIElement element, Map parameters) {
		element.setText("Login");
	}

	
	
}
