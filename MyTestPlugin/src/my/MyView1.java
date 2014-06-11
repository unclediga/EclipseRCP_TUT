package my;


import java.util.ArrayList;
import java.util.Iterator;

import my.model.Word;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

public class MyView1 extends ViewPart {

	public static final String ID = "my.views.view1";

	private Label label;
	private MyInput input = new MyInput();
	private ListViewer viewer;
	
	private Action addItemAction;
	private Action deleteItemAction;
	private Action selectAllAction;

	private Action actionForMyActionPlugin;
	
	
	

	private IMemento memento;
	

	@Override
	public void createPartControl(Composite parent) {
		
		parent.setLayout(new GridLayout(1,true));
		label = new Label(parent, SWT.NONE);
		label.setText("List of choice:");
		label.setLayoutData(new GridData(GridData.FILL,GridData.CENTER,true,false));
		
		viewer = new ListViewer(parent, SWT.V_SCROLL|SWT.MULTI);
		viewer.getList().setLayoutData(new GridData(GridData.FILL,GridData.FILL,true,true));
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setInput(input.getInput());
		
		getSite().setSelectionProvider(viewer);
		
		// Create menu and toolbars.
        createActions();
        createMenu();
        createToolbar();
        createContextMenu();
        hookGlobalActions();
		
		restoreState();
		
		
		
	}

	private void restoreState() {
		if(memento == null) return;
		memento = memento.getChild("selection");
		if(memento != null){
			IMemento[] descs = memento.getChildren("descriptor");
			if(descs.length > 0){
				ArrayList<Word> array = new ArrayList<Word>(descs.length);
				for (int i = 0; i < descs.length; i++) {
					IMemento im = descs[i];
					String id = im.getID();
					Word w = input.find(id);
					if(w != null)
						array.add(w);
					
				}
				viewer.setSelection(new StructuredSelection(array));
				
			}
			
		}	
		memento = null;
	}

	private void hookGlobalActions() {
		IActionBars actionBars = getViewSite().getActionBars();
		actionBars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL, selectAllAction);
		actionBars.setGlobalActionHandler(IWorkbenchActionConstants.DELETE, deleteItemAction);
		viewer.getControl().addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.DEL && e.stateMask == 0
						&& deleteItemAction.isEnabled())
					deleteItemAction.run();
			}

		});
		
		// Тащим Action из плагина MyActionPlugin
		actionBars.setGlobalActionHandler("MyActionPlugin.as1.action1", actionForMyActionPlugin);
		
	}

	private void createContextMenu() {
		MenuManager manager = new MenuManager();
		manager.setRemoveAllWhenShown(true);
		manager.addMenuListener(new IMenuListener() {
			
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}
		});
		Menu menu = manager.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(manager, viewer);
		
	}

	protected void fillContextMenu(IMenuManager mng) {
		mng.add(addItemAction);
		mng.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mng.add(deleteItemAction);
		mng.add(new Separator());
		mng.add(selectAllAction);
		
		
	}

	private void createToolbar() {
		IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
		toolBarManager.add(addItemAction);
		toolBarManager.add(deleteItemAction);
		
	}

	private void createMenu() {
		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
		menuManager.add(selectAllAction);
	}

	private void createActions() {
		addItemAction = new Action("Add...") {

			@Override
			public void run() {
				addItem();
			}
			
		};
		deleteItemAction = new Action("Delete") {
			
			@Override
			public void run() {
				delItem();
			}
			
		};
		selectAllAction = new Action("Select All") {
			
			@Override
			public void run() {
				selectAll();
			}
			
		};
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateActionEnablement();
			}
		});
		
		
		// Создаём обработчик для Retargable Action из плагина MyActionPlugin
		actionForMyActionPlugin = new Action1(getSite().getWorkbenchWindow());
		actionForMyActionPlugin.setText("MyView Action1");
		
		
	}

	protected void selectAll() {
		System.out.println("selectAll");
		
	}

	protected void delItem() {
		System.out.println("del");
		
	}

	protected void addItem() {
		System.out.println("add");
		
	}

	protected void updateActionEnablement() {
		StructuredSelection selection = 
				(StructuredSelection) viewer.getSelection();
		deleteItemAction.setEnabled(selection.size() > 0);
	}

	@Override
	public void saveState(IMemento memento) {
		super.saveState(memento);
		StructuredSelection selection = (StructuredSelection) viewer.getSelection();
		if (selection.isEmpty()) return;
		memento = memento.createChild("selection");
		Iterator it = selection.iterator();
		while (it.hasNext()) {
			Word w = (Word) it.next();
			memento.createChild("descriptor",w.getName());
		}
		
	}

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		super.init(site);
		this.memento = memento;
	}

	@Override
	public void setFocus() {
		label.setFocus();
	}

	private class MyInput{
		
		private ArrayList<Word> array;
		
		public MyInput(){
			array = new ArrayList<Word>(10);
			array.add(new Word("Red"));
			array.add(new Word("Green"));
			array.add(new Word("Yellow"));
			array.add(new Word("Black"));
			array.add(new Word("Gray"));
			array.add(new Word("Blue"));
		}
		
		public Word find(String id) {
			Iterator<Word> it = array.iterator();
			while(it.hasNext()){
				Word w = it.next();
				if(id.equals(w.getName()))
						return w;
			}
			return null;
		};
		
		public ArrayList<Word> getInput() {
			return array;
		}
	};

	
	class Action1 extends Action {
		
		private IWorkbenchWindow window;
			
		public Action1(IWorkbenchWindow window) {
			super();
			this.window = window;
		}

		
		@Override
			public void run() {
			MessageDialog.openInformation(window.getShell(), "Action1", "Retargable to MyView handler");
		}
	}	
		
	
	
}
