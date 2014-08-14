package org.eclipse.ui.examples.navigator.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

public class PropertyActionProvider extends CommonActionProvider {

	@Override
	public void fillContextMenu(IMenuManager menu) {
		// TODO Auto-generated method stub
		super.fillContextMenu(menu);
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		// TODO Auto-generated method stub
		super.fillActionBars(actionBars);
	}

	@Override
	public void init(ICommonActionExtensionSite aSite) {
		ICommonViewerSite viewSite = aSite.getViewSite();
		if(viewSite instanceof ICommonViewerWorkbenchSite){
			ICommonViewerWorkbenchSite workbenchSite = (ICommonViewerWorkbenchSite) viewSite;
			//openAction = new OpenPropertyAction();
		}
		
	}

}
