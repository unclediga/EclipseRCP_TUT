package mytestapp;

import mytestapp.contr.RemoveFavoritesContributionItem;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "MyCommandApp.view";

	private TableViewer viewer;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			if (parent instanceof Object[]) {
				return (Object[]) parent;
			}
	        return new Object[0];
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new ViewContentProvider());
		viewer.setLabelProvider(new ViewLabelProvider());
		// Provide the input to the ContentProvider
		viewer.setInput(new String[] {"One", "Two", "Three","Four","Five"});
		
		
		// Без этого невозможно вставить пункт popup меню 
		// через точку расширения "menuContribute"
		MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
	    menuManager.addMenuListener(new IMenuListener() {
	         public void menuAboutToShow(IMenuManager m) {
	            View.this.fillContextMenu(m);
	         }
	      });

	    Menu menu = menuManager.createContextMenu(viewer.getTable());
	    
	    viewer.getTable().setMenu(menu);
	    getSite().registerContextMenu(menuManager, viewer);

	    // Без этого невозможно получить список выделенных элементов 
	    // в других вьюшках или обработчиках.  
	    getSite().setSelectionProvider(viewer);
		
	}
	
	
	

	protected void fillContextMenu(IMenuManager m) {
	      m.add(new Separator("edit"));
	      m.add(new RemoveFavoritesContributionItem(getViewSite(), null));
	      m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		
	}




	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
}