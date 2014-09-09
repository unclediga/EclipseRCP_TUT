package customplugin.wizards;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import customplugin.Activator;

public class CustomProjectNewDeploymentFile extends Wizard implements
		INewWizard {

	private IWorkbench _workbench;
	private IStructuredSelection _selection;
	private WizardNewFileCreationPage _page;

	public CustomProjectNewDeploymentFile() {
		setWindowTitle("New Deployment File");
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
		return result;
	}

}
