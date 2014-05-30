package my;

import my.model.Word;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

public class MyView2 extends ViewPart implements ISelectionListener {

	public static final String ID = "my.views.view2";

	private Label label;
	private Action testAction1;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		label = new Label(parent, SWT.NONE);
		label.setText("View ID:"+getViewSite().getId()+":"+getViewSite().getSecondaryId());
		label.setBackground(getViewSite().getWorkbenchWindow().getShell().getDisplay().getSystemColor(SWT.COLOR_DARK_CYAN));
		
		createActions();
		createPopupMenu();
		getViewSite().getPage().addSelectionListener(this);
	}

	private void createActions() {
		testAction1 = new Action("test Action 1"){

			@Override
			public void run() {
				System.out.println("test Action #1 from MyView2 is running");
			}
			
		};
		
	}

	private void createPopupMenu() {
		MenuManager manager = new MenuManager();
		//manager.setRemoveAllWhenShown(true);
		Menu menu = manager.createContextMenu(label);
		label.setMenu(menu);

		manager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		manager.add(testAction1);
		
		getSite().registerContextMenu("my.views.view2.popup",manager, null);
		
	}

	@Override
	public void setFocus() {
		label.setFocus();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if(selection instanceof IStructuredSelection){
			StructuredSelection s = (StructuredSelection) selection;
			Object ss = s.getFirstElement();
			if(ss instanceof Word){
				Word w = (Word) ss;
				label.setText(w.getName());
			}
		}
		
	}

}
