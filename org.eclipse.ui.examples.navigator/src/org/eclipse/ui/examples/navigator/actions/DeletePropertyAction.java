package org.eclipse.ui.examples.navigator.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IActionDelegate;

public class DeletePropertyAction implements IActionDelegate {

	@Override
	public void run(IAction action) {
		System.out.println("Hello from Delegate");

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
