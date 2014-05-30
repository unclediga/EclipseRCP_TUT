package my;

import my.model.Word;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class WordNameObjectAction extends Action implements IObjectActionDelegate{
	
	private Word oldWord;

	@Override
	public void run(IAction action) {
		if (oldWord != null)
			System.out.println("Word Name = "+oldWord.getName());
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		StructuredSelection s = (StructuredSelection) selection;
		oldWord = (Word) s.getFirstElement();
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		System.out.println("setActivePart:"+targetPart.getTitle());
		
	}
	
}