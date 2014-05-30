package my;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class TestAction2 extends Action implements IViewActionDelegate{

	private IViewPart view;


	@Override
	public void run(IAction action) {
		System.out.println("test Action #2 from MyView2 is running");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		System.out.println("selCha:" + view.getViewSite().getId());
		System.out.println("Action:" + action.getId() + " sel:" + selection);
	}

	@Override
	public void init(IViewPart view) {
		this.view = view;
	}
	
}
