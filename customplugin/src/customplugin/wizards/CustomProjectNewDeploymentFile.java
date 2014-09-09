package customplugin.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

public class CustomProjectNewDeploymentFile extends Wizard implements
		INewWizard {

	private static final String WIZARD_NAME = "New Deployment File";
	private IWorkbench _workbench;
	private IStructuredSelection _selection;
	private WizardNewFileCreationPage _page;

	public CustomProjectNewDeploymentFile() {
		setWindowTitle(WIZARD_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		_workbench = workbench;
		_selection = selection;

		_page = new WizardDeploymentNewFileCreationPage(_selection);
		addPage(_page);

	}

	@Override
	public boolean performFinish() {

		boolean result = false;
		IFile file = _page.createNewFile();
		result = file != null;
		
		try {
			IDE.openEditor(_workbench.getActiveWorkbenchWindow().getActivePage(), file);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
