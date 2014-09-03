package customplugin.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

public class CustomProjectNewWizard extends Wizard implements INewWizard {

    private static final String PAGE_NAME = "Custom Plug-in Project Wizard"; //$NON-NLS-1$
    private WizardNewProjectCreationPage _pageOne;
	
	public CustomProjectNewWizard() {
		setWindowTitle(PAGE_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void addPages() {
		super.addPages();
		_pageOne = new WizardNewProjectCreationPage(PAGE_NAME);
		_pageOne.setTitle(NewWizardMessages.CustomProjectNewWizard_Custom_Plugin_Project);
		_pageOne.setDescription(NewWizardMessages.CustomProjectNewWizard_Test);
		_pageOne.setDescription(NewWizardMessages.CustomProjectNewWizard_Create_something_custom);
		
		addPage(_pageOne);
	}
}


