package com.eclipse_tips.rcp.app;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class SessionSourceProvider extends AbstractSourceProvider {

	public final static String SESSION_STATE = "com.eclipse-tips.rcp.app.sessionState";

	private final static String LOGGED_IN = "loggedIn";
	private final static String LOGGED_OUT = "loggedOut";

	boolean loggedIn;

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] { SESSION_STATE };
	}

	@Override
	public Map<String, String> getCurrentState() {
		Map<String, String> currentState = new HashMap<String, String>(1);
		String curState = loggedIn ? LOGGED_IN : LOGGED_OUT;
		currentState.put(SESSION_STATE, curState);
		return currentState;
	}

	@Override
	public void dispose() {

	}

	public void setLoggedIn(boolean loggedIn) {
		if (this.loggedIn == loggedIn)
			return; // no change
		this.loggedIn = loggedIn;
		String currentState = loggedIn ? LOGGED_IN : LOGGED_OUT;
		fireSourceChanged(ISources.WORKBENCH, SESSION_STATE, currentState);
	}
}
