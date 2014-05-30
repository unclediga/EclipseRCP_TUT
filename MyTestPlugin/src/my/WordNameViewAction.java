package my;

import my.model.Word;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class WordNameViewAction extends Action implements IViewActionDelegate{

	private Word oldWord;
	IViewPart view; 
	@Override
	public void run(IAction action) {
//		if (view.getViewSite().getId() == "my.views.view1") {
//			if (oldWord != null)
//				System.out.println("Word Name = " + oldWord.getName());
//		}
		System.out.println("WordNameViewAction running");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
//		if(view.getViewSite().getId() == "my.views.view1"){
//			StructuredSelection s = (StructuredSelection) selection;
//			oldWord = (Word) s.getFirstElement();
//		}
		System.out.println("select chang ID:"+view.getViewSite().getId());
	}

	@Override
	public void init(IViewPart view) {
		
		this.view = view;
	}

}
