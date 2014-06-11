package my.actionplugin.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;


//public class Action1 implements IWorkbenchWindowActionDelegate {
public class Action1 extends Action {
	
	private IWorkbenchWindow window;
		
	public Action1(IWorkbenchWindow window) {
		super();
		this.window = window;
	}

	
	@Override
		public void run() {
		MessageDialog.openInformation(window.getShell(), "Action1", "Message Action1");
	}
	
	

}
