package my.actionplugin.views;


import java.util.ArrayList;

import my.actionplugin.actions.Action1;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ActionView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "my.actionplugin.views.ActionView";

	private TableViewer viewer;
	private IAction action1;
	private Action action2;
	private Action doubleClickAction;

	private Action action3;
	private Action select_all;
	
	private MyIntType[] inputElements = new MyIntType[] { 
			new MyIntType(1, "One"),
			new MyIntType(2, "Two"), 
			new MyIntType(3, "Three"),
			new MyIntType(4, "Four"),
			new MyIntType(5, "Five")
	};

	private Action action4;;
	

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	 
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
//			return new String[] { "One", "Two", "Three","Four","Five" };
			return inputElements;
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
//			return getText(obj);
			return ((MyIntType)obj).name;
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
		
	}

	/**
	 * The constructor.
	 */
	public ActionView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.setInput(getViewSite());
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ActionView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		//getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(new Separator("additions1"));
		manager.add(action3);
		manager.add(new Separator("additions2"));
		manager.add(action4);
		manager.add(select_all);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action1(getSite().getWorkbenchWindow());
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
		
		
		action3 = new Action(){

			@Override
			public void run() {
				IStructuredContentProvider contentProvider = (IStructuredContentProvider) viewer.getContentProvider();
				Object[] elements = contentProvider.getElements(null);
				ArrayList<MyIntType> elements2 = new ArrayList<MyIntType>();				
				for (int i = 0; i < elements.length; i++) {
					MyIntType object = (MyIntType) elements[i];
					if(object.isEven())
						elements2.add(object);
				}
				viewer.setSelection(new StructuredSelection(elements2),true); 
			}
			
			
		};
		action3.setText("My acton3");
		
		action4 = new Action(){

			@Override
			public void run() {
				System.out.println("SELECT ALL: action id"+ActionFactory.SELECT_ALL.getId()+" comm id "+ActionFactory.SELECT_ALL.getCommandId());
				System.out.println("COPY: action id"+ActionFactory.COPY.getId()+" comm id "+ActionFactory.COPY.getCommandId());
				System.out.println("PASTE: action id"+ActionFactory.PASTE.getId()+" comm id "+ActionFactory.PASTE.getCommandId());

			}
			
		};
		action4.setText("Show glob actions ID");
		
		
		IActionBars bar = getViewSite().getActionBars();
		
		bar.setGlobalActionHandler("MyActionPlugin.as1.action1", action1);
				
//		bar.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), action3);
		bar.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, action3);
		
		select_all = (Action) ActionFactory.SELECT_ALL.create(getSite().getWorkbenchWindow());
//		bar.setGlobalActionHandler(IWorkbenchCommandConstants.EDIT_SELECT_ALL, select_all);
//		bar.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), select_all);

		
		
		bar.updateActionBars();
		
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}
	
	
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Action View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	
	class MyIntType{
		public int number;
		public String name;
		
		public MyIntType(int number, String name) {
			this.number = number;
			this.name = name;
		};
		
		public String toString(){
			return "["+number+"]"+name;
		}
		
		public boolean isEven(){
			return !(number % 2 > 0); 
		}
		
	}
	
}