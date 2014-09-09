package customplugin.wizards;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import customplugin.Activator;

public class WizardDeploymentNewFileCreationPage extends
		WizardNewFileCreationPage {

	public WizardDeploymentNewFileCreationPage(IStructuredSelection selection) {
		super("Custom Plug-in Deployment File Wizard", selection);

		setTitle("Deployment File Wizard");
		setDescription("Create a Deployment File");
		setFileExtension("xml");

	}

	@Override
	protected InputStream getInitialContents() {

		InputStream inputStream = null;

		String templateFilePath = "templates/deployment-template.xml";
		try {
			inputStream = Activator.getDefault().getBundle()
					.getEntry(templateFilePath).openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return inputStream;
	}

}